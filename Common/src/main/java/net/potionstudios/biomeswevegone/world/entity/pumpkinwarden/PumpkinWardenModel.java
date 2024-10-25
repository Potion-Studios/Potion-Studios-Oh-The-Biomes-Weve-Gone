package net.potionstudios.biomeswevegone.world.entity.pumpkinwarden;

import net.minecraft.resources.ResourceLocation;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

/**
 * Pumpkin Warden Model
 * @see GeoModel
 * @author YaBoiChips
 */
class PumpkinWardenModel<T extends GeoAnimatable> extends GeoModel<T> {

    @Override
    public ResourceLocation getModelResource(T object) {
        return BiomesWeveGone.id("geo/pumpkinwarden.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return ((PumpkinWarden) object).isHiding() ? BiomesWeveGone.id("textures/entity/pumpkin_warden/hiding.png") : BiomesWeveGone.id("textures/entity/pumpkin_warden/default.png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return BiomesWeveGone.id("animations/pumpkinwarden.animation.json");
    }

    @Override
    public void setCustomAnimations(T entity, long uniqueID, AnimationState<T> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        GeoBone head = this.getAnimationProcessor().getBone("Head");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setPivotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setPivotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
    }
}