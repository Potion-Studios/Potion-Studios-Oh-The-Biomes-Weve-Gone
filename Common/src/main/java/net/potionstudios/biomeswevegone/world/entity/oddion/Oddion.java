package net.potionstudios.biomeswevegone.world.entity.oddion;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.potionstudios.biomeswevegone.config.configs.BWGMobSpawnConfig;
import net.potionstudios.biomeswevegone.sounds.BWGSounds;
import net.potionstudios.biomeswevegone.world.entity.BWGEntities;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.IntFunction;

/**
 * The Oddion Entity
 *
 * @author YaBoiChips
 * @see PathfinderMob
 * @see GeoEntity
 */
public class Oddion extends PathfinderMob implements GeoEntity, VariantHolder<Oddion.Variant> {

    private final AnimatableInstanceCache animatableInstanceCache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation WALKING = RawAnimation.begin().thenPlay("walking");
    private static final RawAnimation POP_UP = RawAnimation.begin().thenPlay("pop_up");
    private static final RawAnimation DANCE = RawAnimation.begin().thenPlay("dance");
    private static final RawAnimation GROUND = RawAnimation.begin().thenPlay("ground");
    private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("idle");
    private static final RawAnimation PET = RawAnimation.begin().thenLoop("pet");


    private static final EntityDataAccessor<Integer> RISING_TIMER = SynchedEntityData.defineId(Oddion.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> GROUNDING_TIMER = SynchedEntityData.defineId(Oddion.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PET_TIMER = SynchedEntityData.defineId(Oddion.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(Oddion.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> PARTYING = SynchedEntityData.defineId(Oddion.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> GROUNDED = SynchedEntityData.defineId(Oddion.class, EntityDataSerializers.BOOLEAN);

    public int onionTime;

    public Goal movementGoal = new WaterAvoidingRandomStrollGoal(this, 1.0D);


    @Nullable
    private BlockPos jukebox;

    public Oddion(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.onionTime = 6000;
    }

    public Oddion(Level level) {
        this(BWGEntities.ODDION.get(), level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PARTYING, false);
        this.entityData.define(GROUNDED, true);
        this.entityData.define(RISING_TIMER, 0);
        this.entityData.define(GROUNDING_TIMER, 0);
        this.entityData.define(DATA_VARIANT, 0);
        this.entityData.define(PET_TIMER, 0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 2.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.BONE_MEAL), false));
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant().getId());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(Variant.byId(compound.getInt("Variant")));
    }

    public static boolean checkOddionSpawnRules(EntityType<? extends Oddion> entity, LevelAccessor world, MobSpawnType spawnType, BlockPos pos, RandomSource rand) {
        return BWGMobSpawnConfig.INSTANCE.oddion && world.getBlockState(pos.below()).is(BlockTags.DIRT);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        this.setVariant(Variant.getSpawnVariant(level.getRandom()));
        return super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.4D);
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return BWGSounds.ODDION_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return BWGSounds.ODDION_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return BWGSounds.ODDION_AMBIENT.get();
    }

//    private void playDiggingSound() {
//        if (this.level().isClientSide() && getRisingTime() > 600) {
//            this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.SNIFFER_DIGGING, this.getSoundSource(), 0.6F, 2.0F, true);
//        }
//
//    }
//
//    public void tick() {
//            this.playDiggingSound();
//
//        super.tick();
//    }

    @Override
    public void aiStep() {
        if (!this.level().isClientSide()) {
            if (--this.onionTime <= 0) {
                this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.spawnAtLocation(BWGItems.ODDION_BULB.get());
                this.gameEvent(GameEvent.ENTITY_PLACE);
                this.onionTime = 6000;
            }
            if (!isGrounded()) {
                if (this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 3.46) || !this.level().getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
                    this.setPartying(false);
                    this.jukebox = null;
                }
                checkGoals();
                if (getRisingTime() > 0)
                    setRisingTimer(getRisingTime() - 1);
                if (this.level().getEntitiesOfClass(Player.class, new AABB(this.blockPosition()).inflate(8)).isEmpty())
                    setGroundingTimer(getGroundingTime() + 1);
            } else if (!this.level().getEntitiesOfClass(Player.class, new AABB(this.blockPosition()).inflate(3)).isEmpty()) {
                this.setGrounded(false);
                setRisingTimer(20);
            }
            if (getGroundingTime() > 600) {
                setGrounded(true);
                this.goalSelector.removeGoal(movementGoal);
                setGroundingTimer(0);
            }
            if (isBeingPet()) {
                this.goalSelector.removeGoal(movementGoal);
                setPetTime(getPetTime() - 1);
            }
        }

        super.aiStep();
    }

    @Override
    protected @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if (!isGrounded()) {
            petOddion();
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    private void petOddion() {
        if (!level().isClientSide) {
            setPetTime(20);
            level().playSound(null, getX(), getY(), getZ(), BWGSounds.ODDION_HAPPY.get(), SoundSource.PLAYERS, 1, 1);
        }

        float expansion = 0.5F;
        for (int heartCount = 0; heartCount < level().random.nextInt(5, 10); heartCount++) {
            double xSize = getBoundingBox().getXsize();
            double ySize = getBoundingBox().getYsize();
            double zSize = getBoundingBox().getZsize();

            float randX = Mth.randomBetween(level().random, (float) -xSize - expansion, (float) xSize + expansion) / 2F;
            float randY = Mth.randomBetween(level().random, (float) 0, (float) ySize + expansion);
            float randZ = Mth.randomBetween(level().random, (float) -zSize - expansion, (float) zSize + expansion) / 2F;

            level().addParticle(ParticleTypes.HEART, getX() + randX, getY() + randY, getZ() + randZ, 0.05, 0.1, 0.05);
        }
    }

    public void checkGoals() {
        if (this.goalSelector.getAvailableGoals().stream().noneMatch(goal -> goal.getGoal().getClass() == WaterAvoidingRandomStrollGoal.class))
            this.goalSelector.addGoal(1, movementGoal);
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if (isGrounded()) {
            return event.setAndContinue(GROUND);
        }

        if (isRising()) {
            return event.setAndContinue(POP_UP);
        }

        if (isBeingPet()) {
            return event.setAndContinue(PET);
        }

        if (isPartying()) {
            return event.setAndContinue(DANCE);
        }

        if (event.isMoving()) {
            return event.setAndContinue(WALKING);
        }

        return event.setAndContinue(IDLE); // Always default to idle
    }

    private boolean isRising() {
        return getRisingTime() > 0;
    }

    @Override
    public void setRecordPlayingNearby(@NotNull BlockPos jukebox, boolean partying) {
        this.jukebox = jukebox;
        this.setPartying(partying);
        this.goalSelector.removeGoal(movementGoal);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animatableInstanceCache;
    }

    public boolean isPartying() {
        return entityData.get(PARTYING);
    }

    public void setPartying(boolean flag) {
        entityData.set(PARTYING, flag);
    }

    public boolean isGrounded() {
        return entityData.get(GROUNDED);
    }

    public void setGrounded(boolean flag) {
        entityData.set(GROUNDED, flag);
    }

    public int getRisingTime() {
        return entityData.get(RISING_TIMER);
    }

    public void setRisingTimer(int flag) {
        entityData.set(RISING_TIMER, flag);
    }

    public int getGroundingTime() {
        return entityData.get(GROUNDING_TIMER);
    }

    public void setGroundingTimer(int flag) {
        entityData.set(GROUNDING_TIMER, flag);
    }

    public int getPetTime() {
        return entityData.get(PET_TIMER);
    }

    public void setPetTime(int time) {
        entityData.set(PET_TIMER, time);
    }

    public boolean isBeingPet() {
        return getPetTime() > 0;
    }

    @Override
    public void setVariant(Variant variant) {
        this.entityData.set(DATA_VARIANT, variant.getId());
    }

    @Override
    public @NotNull Variant getVariant() {
        return Variant.byId(this.entityData.get(DATA_VARIANT));
    }

    public enum Variant implements StringRepresentable {
        STANDARD(0, "standard"),
        PINK(1, "pink"),
        ALBINO(2, "albino");

        public static final Codec<Oddion.Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
        private static final IntFunction<Oddion.Variant> BY_ID = ByIdMap.continuous(Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        private final String name;
        private final int id;

        Variant(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }

        public static Oddion.Variant byId(int id) {
            return BY_ID.apply(id);
        }

        private static Oddion.Variant getSpawnVariant(RandomSource random) {
            int i = random.nextInt(100);
            return i < 47 ? STANDARD : i < 94 ? PINK : ALBINO;
        }
    }
}
