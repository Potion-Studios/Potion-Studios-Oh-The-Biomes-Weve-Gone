package net.potionstudios.biomeswevegone.client.renderer.entity.oddion;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.potionstudios.biomeswevegone.world.entity.oddion.Oddion;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


/**
 * Oddion Entity Renderer
 * @see GeoEntityRenderer
 * @author YaBoiChips
 */
public class OddionRenderer<T extends Oddion> extends GeoEntityRenderer<T> {

    public OddionRenderer(EntityRendererProvider.Context context) {
        super(context, new OddionModel<>());
    }

    @Override
    protected float getShadowRadius(@NotNull EntityRenderState renderState) {
        return 0.4f;
    }
}
