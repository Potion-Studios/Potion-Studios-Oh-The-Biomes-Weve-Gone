package net.potionstudios.biomeswevegone.client.renderer.entity.oddion;

import net.minecraft.resources.ResourceLocation;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.entity.oddion.Oddion;
import software.bernie.geckolib.model.GeoModel;

/**
 * Oddion Model
 * @see GeoModel
 * @author YaBoiChips, Joseph T. McQuigg
 */
class OddionModel<T extends Oddion> extends GeoModel<T> {

    @Override
    public ResourceLocation getModelResource(T oddion) {
        return BiomesWeveGone.id("geo/oddion.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T oddion) {
        return BiomesWeveGone.id("textures/entity/oddion/" + oddion.getVariant().getName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T oddion) {
        return BiomesWeveGone.id("animations/oddion.animation.json");
    }
}