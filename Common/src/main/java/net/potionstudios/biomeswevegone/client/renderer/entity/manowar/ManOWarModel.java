package net.potionstudios.biomeswevegone.client.renderer.entity.manowar;

import net.minecraft.resources.ResourceLocation;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.entity.manowar.ManOWar;
import software.bernie.geckolib.model.GeoModel;

/**
 * ManOWar Model
 * @see GeoModel
 * @author YaBoiChips
 */
class ManOWarModel<T extends ManOWar> extends GeoModel<T> {

    @Override
    public ResourceLocation getModelResource(T manOWar) {
        return BiomesWeveGone.id("geo/man_o_war.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T manOWar) {
        return BiomesWeveGone.id("textures/entity/manowar/" + manOWar.getColor().getSerializedName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T manOWar) {
        return BiomesWeveGone.id("animations/man_o_war.animation.json");
    }
}