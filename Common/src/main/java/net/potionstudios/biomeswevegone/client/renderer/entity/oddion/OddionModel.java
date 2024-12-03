package net.potionstudios.biomeswevegone.client.renderer.entity.oddion;

import net.minecraft.resources.ResourceLocation;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.entity.oddion.Oddion;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

/**
 * Oddion Model
 * @see GeoModel
 * @author YaBoiChips, Joseph T. McQuigg
 */
class OddionModel<T extends GeoAnimatable> extends GeoModel<T> {

    @Override
    public ResourceLocation getModelResource(T object) {
        return BiomesWeveGone.id("geo/oddion.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return BiomesWeveGone.id("textures/entity/oddion/" + ((Oddion) object).getVariant().getName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return BiomesWeveGone.id("animations/oddion.animation.json");
    }
}