package net.potionstudios.biomeswevegone.world.item.boat;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.potionstudios.biomeswevegone.world.entity.boats.BWGBoatEntity;
import net.potionstudios.biomeswevegone.world.entity.boats.BWGChestBoatEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class BWGBoatItem extends Item {

    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final BWGBoatEntity.Type type;
    private final boolean hasChest;

    public BWGBoatItem(boolean hasChest, BWGBoatEntity.Type type, Item.Properties properties) {
        super(properties);
        this.hasChest = hasChest;
        this.type = type;
    }

    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResult.PASS;
        } else {
            Vec3 vec3 = player.getViewVector(1.0F);
            //double d = 5.0;
            List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(vec3.scale(5.0)).inflate(1.0), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 vec32 = player.getEyePosition();

                for (Entity entity : list) {
                    if (entity.getBoundingBox().inflate(entity.getPickRadius()).contains(vec32))
                        return InteractionResult.PASS;
                }
            }

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                Boat boat = this.getBoat(level, hitResult);
                if(boat instanceof BWGChestBoatEntity chestBoat)
                    chestBoat.setVariant(this.type);
                else if(boat instanceof BWGBoatEntity)
                    ((BWGBoatEntity)boat).setVariant(this.type);
                boat.setYRot(player.getYRot());
                if (!level.noCollision(boat, boat.getBoundingBox())) {
                    return InteractionResult.FAIL;
                } else {
                    if (!level.isClientSide) {
                        level.addFreshEntity(boat);
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                        if (!player.getAbilities().instabuild)
                            itemStack.shrink(1);
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResult.SUCCESS;
                }
            } else return InteractionResult.PASS;
        }
    }

    private Boat getBoat(Level level, HitResult hitResult) {
        return this.hasChest ? new BWGChestBoatEntity(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z) : new BWGBoatEntity(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
    }

    public boolean hasChest() {
        return this.hasChest;
    }
}
