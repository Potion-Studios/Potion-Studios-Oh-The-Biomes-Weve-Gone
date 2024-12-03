package net.potionstudios.biomeswevegone.client.renderer.entity.boat;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.entity.boats.BWGBoatEntity;
import net.potionstudios.biomeswevegone.world.entity.boats.BWGChestBoatEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

/**
 * This class is a modified version of the BoatRenderer class from Minecraft. It is used to render the custom boat models
 * @see net.minecraft.client.renderer.entity.BoatRenderer
 * @author Joseph T. McQuigg
 */
public class BWGBoatRenderer extends EntityRenderer<Boat> {
    private final Map<BWGBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;
    public BWGBoatRenderer(EntityRendererProvider.Context context, boolean chestBoat) {
        super(context);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(BWGBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap(type -> type,
                type -> Pair.of(BiomesWeveGone.id(getTextureLocation(type, chestBoat)), this.createBoatModel(context, type, chestBoat))));
    }

    @Override
    public void render(Boat boat, float entityYaw, float partialTicks, PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.375F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
        float f = (float)boat.getHurtTime() - partialTicks;
        float f1 = boat.getDamage() - partialTicks;
        if (f1 < 0.0F)
            f1 = 0.0F;

        if (f > 0.0F)
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)boat.getHurtDir()));

        float f2 = boat.getBubbleAngle(partialTicks);
        if (!Mth.equal(f2, 0.0F))
            poseStack.mulPose((new Quaternionf()).setAngleAxis(boat.getBubbleAngle(partialTicks) * 0.017453292F, 1.0F, 0.0F, 1.0F));

        Pair<ResourceLocation, ListModel<Boat>> pair = this.getModelWithLocation(boat);
        ResourceLocation resourcelocation = pair.getFirst();
        ListModel<Boat> listmodel = pair.getSecond();
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        listmodel.setupAnim(boat, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = buffer.getBuffer(listmodel.renderType(resourcelocation));
        listmodel.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!boat.isUnderWater()) {
            VertexConsumer vertexconsumer1 = buffer.getBuffer(RenderType.waterMask());
            if (listmodel instanceof WaterPatchModel waterpatchmodel)
                waterpatchmodel.waterPatch().render(poseStack, vertexconsumer1, packedLight, OverlayTexture.NO_OVERLAY);
        }

        poseStack.popPose();
        super.render(boat, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    private static String getTextureLocation(BWGBoatEntity.Type pType, boolean pChestBoat) {
        return pChestBoat ? "textures/entity/chest_boat/" + pType.getName() + ".png" : "textures/entity/boat/" + pType.getName() + ".png";
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context pContext, BWGBoatEntity.Type pType, boolean pChestBoat) {
        ModelLayerLocation modellayerlocation = pChestBoat ? BWGBoatRenderer.createChestBoatModelName(pType) : BWGBoatRenderer.createBoatModelName(pType);
        ModelPart modelpart = pContext.bakeLayer(modellayerlocation);
        return pChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    private static ModelLayerLocation createLocation(String pPath) {
        return new ModelLayerLocation(BiomesWeveGone.id(pPath), "main");
    }

    public static ModelLayerLocation createBoatModelName(BWGBoatEntity.Type pType) {
        return createLocation("boat/" + pType.getName());
    }

    public static ModelLayerLocation createChestBoatModelName(BWGBoatEntity.Type pType) {
        return createLocation("chest_boat/" + pType.getName());
    }

    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        if(boat instanceof BWGBoatEntity boatEntity)
            return this.boatResources.get(boatEntity.getModVariant());
        else if(boat instanceof BWGChestBoatEntity chestBoatEntity)
            return this.boatResources.get(chestBoatEntity.getModVariant());
        else return null;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Boat entity) {
        return this.getModelWithLocation(entity).getFirst();
    }
}
