package net.potionstudios.biomeswevegone.client.renderer.entity.oddion;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
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
    public void render(@NotNull T entity, float entityYaw, float partialTicks, @NotNull PoseStack stack, @NotNull MultiBufferSource bufferIn, int packedLightIn) {
        shadowRadius = 0.4f;
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
    }
}
