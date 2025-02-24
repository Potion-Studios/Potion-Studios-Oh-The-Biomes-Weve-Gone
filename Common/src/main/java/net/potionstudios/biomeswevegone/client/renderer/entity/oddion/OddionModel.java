package net.potionstudios.biomeswevegone.client.renderer.entity.oddion;

import net.minecraft.resources.ResourceLocation;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.entity.oddion.Oddion;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

/**
 * Oddion Model
 * @see GeoModel
 * @author YaBoiChips, Joseph T. McQuigg
 */
class OddionModel<T extends Oddion> extends GeoModel<T> {

    @Override
    public ResourceLocation getModelResource(T oddion, @Nullable GeoRenderer<T> renderer) {
        return BiomesWeveGone.id("geo/oddion.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T oddion, @Nullable GeoRenderer<T> renderer) {
        return BiomesWeveGone.id("textures/entity/oddion/" + oddion.getVariant().getName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T oddion) {
        return BiomesWeveGone.id("animations/oddion.animation.json");
    }
}