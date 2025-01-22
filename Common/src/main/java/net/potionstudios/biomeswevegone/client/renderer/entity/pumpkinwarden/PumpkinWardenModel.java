package net.potionstudios.biomeswevegone.client.renderer.entity.pumpkinwarden;

import net.minecraft.resources.ResourceLocation;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.entity.pumpkinwarden.PumpkinWarden;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

/**
 * Pumpkin Warden Model
 * @see GeoModel
 * @author YaBoiChips
 */
class PumpkinWardenModel<T extends PumpkinWarden> extends GeoModel<T> {

    @Override
    public ResourceLocation getModelResource(T pumpkinWarden) {
        return BiomesWeveGone.id("geo/pumpkinwarden.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T pumpkinWarden) {
	    return pumpkinWarden.isHiding() ? BiomesWeveGone.id("textures/entity/pumpkin_warden/hiding.png") : BiomesWeveGone.id("textures/entity/pumpkin_warden/default.png");
    }

    @Override
    public ResourceLocation getAnimationResource(T pumpkinWarden) {
        return BiomesWeveGone.id("animations/pumpkinwarden.animation.json");
    }

    @Override
    public void setCustomAnimations(T pumpkinWarden, long uniqueID, AnimationState<T> customPredicate) {
        super.setCustomAnimations(pumpkinWarden, uniqueID, customPredicate);
        CoreGeoBone head = this.getAnimationProcessor().getBone("Head");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setPivotX(extraData.headPitch() * ((float) Math.PI / 180F));
        head.setPivotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
    }
}