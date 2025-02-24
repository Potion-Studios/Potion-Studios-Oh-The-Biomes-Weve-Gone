package net.potionstudios.biomeswevegone.client.renderer.entity.manowar;

import net.minecraft.resources.ResourceLocation;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.entity.manowar.ManOWar;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

/**
 * ManOWar Model
 * @see GeoModel
 * @author YaBoiChips
 */
class ManOWarModel<T extends ManOWar> extends GeoModel<T> {

    @Override
    public ResourceLocation getModelResource(T manOWar, @Nullable GeoRenderer<T> renderer) {
        return BiomesWeveGone.id("geo/man_o_war.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T manOWar, @Nullable GeoRenderer<T> renderer) {
        return BiomesWeveGone.id("textures/entity/manowar/" + manOWar.getColor().getSerializedName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T manOWar) {
        return BiomesWeveGone.id("animations/man_o_war.animation.json");
    }
}