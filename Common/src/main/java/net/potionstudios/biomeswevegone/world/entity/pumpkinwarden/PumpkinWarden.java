package net.potionstudios.biomeswevegone.world.entity.pumpkinwarden;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.potionstudios.biomeswevegone.world.entity.BWGEntities;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.IntFunction;

/**
 * The Pumpkin Warden Entity
 * @see PathfinderMob
 * @see GeoEntity
 * @author YaBoiChips
 */
public class PumpkinWarden extends PathfinderMob implements GeoEntity, VariantHolder<PumpkinWarden.Variant> {

    private final AnimatableInstanceCache animatableInstanceCache = GeckoLibUtil.createInstanceCache(this);
    private BlockPos jukebox;
    private boolean party;
    private static final EntityDataAccessor<Boolean> HIDING = SynchedEntityData.defineId(PumpkinWarden.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> TIMER = SynchedEntityData.defineId(PumpkinWarden.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockState> DATA_CARRY_STATE = SynchedEntityData.defineId(PumpkinWarden.class, EntityDataSerializers.BLOCK_STATE);
    private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(PumpkinWarden.class, EntityDataSerializers.INT);

    public Goal moveGoal = new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.7F);
    public Goal runGoal = new AvoidEntityGoal<>(this, Zombie.class, 8.0F, 1.0D, 1.0D);
    public Goal lookGoal = new LookAtPlayerGoal(this, Player.class, 2.0F);
    public Goal randLookGoal = new RandomLookAroundGoal(this);

    public PumpkinWarden(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_CARRY_STATE, Blocks.AIR.defaultBlockState());
        builder.define(HIDING, false);
        builder.define(TIMER, 0);
        builder.define(DATA_VARIANT, 0);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(Variant.byId(compound.getInt("Variant")));
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant().getId());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.4D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(9, new FloatGoal(this));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, Ingredient.of(Items.PUMPKIN_PIE), false));
        this.goalSelector.addGoal(2, new ThrowItemAtCarvedPumpkinGoal(this, 1));
        this.goalSelector.addGoal(1, new DestroyNearestPumpkinGoal(this, 1));
        this.goalSelector.addGoal(5, new StayByBellGoal(this, 1, 5000));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public void checkDespawn() {}

    @Override
    protected @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        if (itemInHand.is(BWGBlocks.ROSE.getBlock().asItem())){
            if (player.level().isClientSide()) {
                level().addParticle(ParticleTypes.HEART, this.getX(), this.getY() + 1, this.getZ(), 1, 1, 1);
                level().playSound(player, player.blockPosition(), SoundEvents.VILLAGER_AMBIENT, SoundSource.NEUTRAL, 1, getVoicePitch());
            }
            itemInHand.shrink(1);
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animatableInstanceCache;
    }

    private static final RawAnimation HIDE_START = RawAnimation.begin().thenPlay("animation.pumpkinwarden.hidestart");
    private static final RawAnimation HIDE = RawAnimation.begin().thenPlay("animation.pumpkinwarden.hide");
    private static final RawAnimation HIDE_END = RawAnimation.begin().thenPlay("animation.pumpkinwarden.hideend");
    private static final RawAnimation HOLDING_WALKING = RawAnimation.begin().thenPlay("animation.pumpkinwarden.holding_walking");
    private static final RawAnimation HOLDING_IDLE = RawAnimation.begin().thenPlay("animation.pumpkinwarden.holding_idle");
    private static final RawAnimation WALKING = RawAnimation.begin().thenPlay("animation.pumpkinwarden.walking");
    private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("animation.pumpkinwarden.idle");

    private static final RawAnimation WAVE = RawAnimation.begin().thenPlay("animation.pumpkinwarden.wave");


    private <E extends GeoAnimatable> PlayState predicate(@NotNull AnimationState<E> event) {
        AnimationController<E> controller = event.getController();
        controller.transitionLength(0);
        if (this.isHiding()) {
            if (this.getTimer() < 10) {
                controller.setAnimation(HIDE_START);
                return PlayState.CONTINUE;
            } else if ((this.getTimer() > 10 && this.getTimer() < 180) || !this.level().isDay() && this.getTimer() > 10) {
                controller.setAnimation(HIDE);
                return PlayState.CONTINUE;
            } else if (this.getTimer() > 180) {
                if (this.level().getBrightness(LightLayer.SKY, this.getOnPos()) > 2) {
                    controller.setAnimation(HIDE_END);
                } else {
                    controller.setAnimation(HIDE);
                }
                return PlayState.CONTINUE;
            }
        }
        if (this.getCarriedBlock() != null) {
            if (event.isMoving()) {
                controller.setAnimation(HOLDING_WALKING);
            } else {
                controller.setAnimation(HOLDING_IDLE);
            }
            return PlayState.CONTINUE;
        } else if (event.isMoving() && this.getCarriedBlock() == null) {
            controller.setAnimation(WALKING);
            return PlayState.CONTINUE;
        } else if (this.party) {
            controller.setAnimation(WAVE);
            return PlayState.CONTINUE;
        } else {
            controller.setAnimation(IDLE);
            return PlayState.CONTINUE;
        }
    }

    private void checkGoals() {
        if (this.goalSelector.getAvailableGoals().stream().noneMatch(goal -> goal.getGoal().getClass() == WaterAvoidingRandomStrollGoal.class)) {
            this.goalSelector.addGoal(1, moveGoal);
        }
        if (this.goalSelector.getAvailableGoals().stream().noneMatch(goal -> goal.getGoal().getClass() == AvoidEntityGoal.class)) {
            this.goalSelector.addGoal(2, runGoal);
        }
        if (this.goalSelector.getAvailableGoals().stream().noneMatch(goal -> goal.getGoal().getClass() == LookAtPlayerGoal.class)) {
            this.goalSelector.addGoal(7, lookGoal);
        }
        if (this.goalSelector.getAvailableGoals().stream().noneMatch(goal -> goal.getGoal().getClass() == RandomLookAroundGoal.class)) {
            this.goalSelector.addGoal(3, randLookGoal);
        }
    }

    @Override
    public void setRecordPlayingNearby(@NotNull BlockPos pPos, boolean pIsPartying) {
        this.jukebox = pPos;
        this.party = pIsPartying;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 10D) || !this.level().getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
            this.party = false;
            this.jukebox = null;
        }
        if (!this.level().isClientSide()) {
            if (!this.level().isDay()) {
                this.setTimer(this.getTimer() + 1);
                this.setHiding(true);
            } else if (this.getTimer() > 0 && this.getLastHurtByMob() == null) {
                this.setTimer(0);
                this.setHiding(false);
            }
            if (this.getLastHurtByMob() != null) {
                if (this.getTimer() < 200) {
                    this.setTimer(this.getTimer() + 1);
                    this.setHiding(true);
                } else {
                    this.setTimer(0);
                    this.setHiding(false);
                }
            }
        }
        if (this.isHiding()) {
            this.goalSelector.removeGoal(moveGoal);
            this.goalSelector.removeGoal(runGoal);
            this.goalSelector.removeGoal(lookGoal);
            this.goalSelector.removeGoal(randLookGoal);
            if (this.getCarriedBlock() != null) {
                BehaviorUtils.throwItem(this, this.getCarriedBlock().getBlock().asItem().getDefaultInstance(), new Vec3(this.getX() + 2, this.getY(), this.getZ()));
                this.setCarriedBlock(null);
            }
        } else {
            checkGoals();
        }
        if (this.getCarriedBlock() != null) {
            this.setItemInHand(this.getUsedItemHand(), this.getCarriedBlock().getBlock().asItem().getDefaultInstance());
        } else {
            this.setItemInHand(this.getUsedItemHand(), ItemStack.EMPTY);
        }
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        this.setVariant(Variant.getSpawnVariant(level.getRandom()));
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }


    @Override
    public boolean canBeLeashed() {
        return true;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return SoundEvents.VILLAGER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VILLAGER_DEATH;
    }

    @Override
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F;
    }


    public boolean isHiding() {
        return entityData.get(HIDING);
    }

    public void setHiding(boolean flag) {
        entityData.set(HIDING, flag);
    }

    public int getTimer() {
        return entityData.get(TIMER);
    }

    public void setTimer(int flag) {
        entityData.set(TIMER, flag);
    }

    public void setCarriedBlock(BlockState pState) {
        this.entityData.set(DATA_CARRY_STATE, pState == null ? Blocks.AIR.defaultBlockState() : pState);
    }

    public BlockState getCarriedBlock() {
        BlockState blockState = this.entityData.get(DATA_CARRY_STATE);
        return blockState.isAir() ? null : blockState;
    }

    @Override
    public void setVariant(@NotNull Variant variant) {
        this.entityData.set(DATA_VARIANT, variant.getId());
    }

    @Override
    public @NotNull Variant getVariant() {
        return Variant.byId(this.entityData.get(DATA_VARIANT));
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
        if (this.isHiding()) return this.getType().getDimensions().scale(1F, 0.5F);
        else return super.getDimensions(pose);
    }

    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> dataAccessor) {
        super.onSyncedDataUpdated(dataAccessor);
        if (HIDING.equals(dataAccessor))
            refreshDimensions();
    }

    private static class DestroyNearestPumpkinGoal extends MoveToBlockGoal {
        private final PumpkinWarden warden;

        private DestroyNearestPumpkinGoal(PumpkinWarden mob, double speed) {
            super(mob, speed, 32);
            this.warden = mob;
        }

        @Override
        protected boolean isValidTarget(@NotNull LevelReader level, @NotNull BlockPos pos) {
            BlockState state = level.getBlockState(pos);
            return state.is(Blocks.PUMPKIN) || state.is(Blocks.MELON) || state.is(BWGBlocks.PALE_PUMPKIN.get());
        }

        @Override
        public boolean canUse() {
            return findNearestBlock() && this.warden.getCarriedBlock() == null;
        }

        @Override
        public void tick() {
            super.tick();
            if (isReachedTarget()) {
                Level level = mob.level();
                if (isValidTarget(level, blockPos)) {
                    BlockState blockstate = level.getBlockState(this.blockPos);
                    level.destroyBlock(blockPos, false, mob);
                    level.gameEvent(GameEvent.BLOCK_DESTROY, this.blockPos, GameEvent.Context.of(this.warden, blockstate));
                    this.warden.setCarriedBlock(blockstate.getBlock().defaultBlockState());
                }
            }
        }
    }


    private static class ThrowItemAtCarvedPumpkinGoal extends MoveToBlockGoal {
        private final PumpkinWarden warden;

        private ThrowItemAtCarvedPumpkinGoal(PumpkinWarden mob, double speed) {
            super(mob, speed, 32);
            this.warden = mob;
        }

        @Override
        protected boolean isValidTarget(@NotNull LevelReader level, @NotNull BlockPos pos) {
            BlockState state = level.getBlockState(pos);
            return state.is(Blocks.CARVED_PUMPKIN) || state.is(BWGBlocks.CARVED_PALE_PUMPKIN.get());
        }

        @Override
        public boolean canUse() {
            return findNearestBlock() && this.warden.getCarriedBlock() != null;
        }

        @Override
        public void tick() {
            super.tick();
            if (isReachedTarget()) {
                Level level = mob.level();
                BlockState state = level.getBlockState(blockPos);
                if (state.is(Blocks.CARVED_PUMPKIN)) {
                    Direction facing = state.getValue(CarvedPumpkinBlock.FACING);
                    BlockPos frontPos = blockPos.relative(facing);
                    if (mob.blockPosition().closerThan(frontPos, 2.5)) {
                        if (this.warden.getCarriedBlock() != null) {
                            BehaviorUtils.throwItem(this.warden, this.warden.getCarriedBlock().getBlock().asItem().getDefaultInstance(), new Vec3(this.blockPos.getX(), this.blockPos.getY(), this.blockPos.getZ()));
                            this.warden.setCarriedBlock(null);
                        }
                    }
                }
            }
        }
    }

    private static class StayByBellGoal extends MoveToBlockGoal {
        public PumpkinWarden warden;

        private StayByBellGoal(PumpkinWarden pumpkinWarden, double speedModifier, int searchRange) {
            super(pumpkinWarden, speedModifier, searchRange);
            this.warden = pumpkinWarden;
        }

        @Override
        protected boolean isValidTarget(@NotNull LevelReader level, @NotNull BlockPos pos) {
            List<BlockState> blockStates = level.getBlockStates(new AABB(warden.blockPosition()).inflate(30)).toList();
            return !blockStates.get(warden.random.nextInt(blockStates.size())).isAir();
        }
    }

    public enum Variant implements StringRepresentable {
        DEFAULT(0, "default"),
        PALE(1, "pale");

        private static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
        private static final IntFunction<Variant> BY_ID = ByIdMap.continuous(Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
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

        public static Variant byId(int id) {
            return BY_ID.apply(id);
        }

        private static Variant getSpawnVariant(@NotNull RandomSource random) {
            return random.nextFloat() < 0.05 ? Variant.PALE : Variant.DEFAULT;
        }
    }

    public static boolean villagerToPumpkinWarden(Entity entity, ItemStack stack, Level level) {
        if (entity instanceof Villager villager && villager.isBaby() && villager.hasEffect(MobEffects.WEAKNESS)) {
            if (stack.is(Items.CARVED_PUMPKIN) || stack.is(BWGBlocks.CARVED_PALE_PUMPKIN.get().asItem())) {
                if (level instanceof ServerLevel serverLevel) {
                    PumpkinWarden warden = BWGEntities.PUMPKIN_WARDEN.get().create(serverLevel);
                    warden.setPos(villager.position());
                    if (stack.is(BWGBlocks.CARVED_PALE_PUMPKIN.get().asItem()))
                        warden.setVariant(PumpkinWarden.Variant.PALE);
                    serverLevel.addFreshEntity(warden);
                    serverLevel.playSound(null, villager.blockPosition(), SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.NEUTRAL, 1, 1);
                    villager.remove(Entity.RemovalReason.DISCARDED);
                    stack.shrink(1);
                    return true;
                }
            }
        }
        return false;
    }
}
