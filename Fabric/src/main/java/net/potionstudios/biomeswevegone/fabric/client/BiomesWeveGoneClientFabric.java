package net.potionstudios.biomeswevegone.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.*;
import net.potionstudios.biomeswevegone.client.BiomesWeveGoneClient;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.custom.BWGSpreadableBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.cactus.BWGCactusBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit.BWGFruitBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.GlowCaneBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail.CattailSproutBlock;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;

import java.util.Objects;

/**
 * Initializes the Fabric client.
 * @see ClientModInitializer#onInitializeClient()
 * @see BiomesWeveGoneClient
 * @author Joseph T. McQuigg
 */
@Environment(EnvType.CLIENT)
public class BiomesWeveGoneClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BiomesWeveGoneClient.onInitialize(Minecraft.getInstance());
        registerRenderTypes();
        BiomesWeveGoneClient.registerEntityRenderers(EntityRendererRegistry::register);
        BiomesWeveGoneClient.registerBlockEntityRenderers(BlockEntityRenderers::register);
        BiomesWeveGoneClient.registerParticles((type, spriteProviderFactory) -> ParticleFactoryRegistry.getInstance().register(type, spriteProviderFactory::apply));
        BiomesWeveGoneClient.registerLayerDefinitions((a, b) -> EntityModelLayerRegistry.registerModelLayer(a, b::get));
        registerColorChangingBlocks();
    }

    /**
     * Registers the render types for the blocks.
     * @see BlockRenderLayerMap
     */
    private void registerRenderTypes() {
        BWGWood.WOOD.forEach(entry -> renderTypeBlock(entry.get()));
        BWGBlocks.BLOCKS.forEach(entry -> renderTypeBlock(entry.get()));
        BlockRenderLayerMap.INSTANCE.putBlock(BWGWood.MAPLE.door(), RenderType.translucent());
    }

    private void renderTypeBlock(Block block) {
        if (block instanceof BWGFruitBlock || block instanceof DoorBlock || block instanceof TrapDoorBlock || block instanceof BushBlock || block instanceof GlowCaneBlock || block instanceof LanternBlock)
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        else if (block instanceof LeavesBlock || block instanceof VineBlock || block instanceof MangroveRootsBlock
                || block instanceof FlowerPotBlock || block instanceof BWGCactusBlock || block instanceof CattailSproutBlock
                || block instanceof BWGSpreadableBlock || block instanceof SporeBlossomBlock || block instanceof BaseCoralPlantTypeBlock)
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutoutMipped());
        else if (block instanceof StainedGlassPaneBlock || block instanceof HalfTransparentBlock)
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.translucent());
    }

    private void registerColorChangingBlocks() {
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getAverageGrassColor(view, pos) : GrassColor.getDefaultColor(), BWGBlocks.FLOWER_PATCH.get(), BWGBlocks.TINY_LILY_PADS.get(), BWGBlocks.FLOWERING_TINY_LILY_PADS.get(), BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get(), BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.WHITE_SAKURA_PETALS.get(), BWGBlocks.YELLOW_SAKURA_PETALS.get());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getAverageFoliageColor(view, pos) : FoliageColor.get(0.5D, 1.0D), BWGBlocks.CLOVER_PATCH.get(), BWGBlocks.LEAF_PILE.get(), BWGBlocks.POISON_IVY.get(), BWGWood.MAHOGANY.leaves(),
                BWGWood.WILLOW.leaves(), BWGWood.MAPLE.leaves(), BWGWood.YUCCA_LEAVES.get(), BWGWood.FLOWERING_YUCCA_LEAVES.get(), BWGWood.RIPE_YUCCA_LEAVES.get(), BWGWood.CYPRESS.leaves());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomesWeveGoneClient.getBorealisIceColor(Objects.requireNonNullElse(pos, BlockPos.ZERO)), BWGBlocks.BOREALIS_ICE.get(), BWGBlocks.PACKED_BOREALIS_ICE.get());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getAverageWaterColor(view, pos) : -1, BWGBlocks.CARVED_BARREL_CACTUS.get());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
            int age = state.getValue(StemBlock.AGE);
            return FastColor.ARGB32.color(age * 32, 255 - age, age *4);
        }, BWGBlocks.PALE_PUMPKIN_STEM.get());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> -2046180, BWGBlocks.ATTACHED_PALE_PUMPKIN_STEM.get());
        registerItemColorHandlers();
    }


    private void registerItemColorHandlers() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            Block block = ((BlockItem) stack.getItem()).getBlock();
            return ColorProviderRegistry.BLOCK.get(block).getColor(block.defaultBlockState(), null, null, tintIndex);
        }, BWGBlocks.TINY_LILY_PADS.get(), BWGBlocks.FLOWERING_TINY_LILY_PADS.get(), BWGBlocks.CLOVER_PATCH.get(), BWGBlocks.LEAF_PILE.get(), BWGBlocks.POISON_IVY.get()
                , BWGWood.MAHOGANY.leaves(), BWGWood.WILLOW.leaves(), BWGWood.MAPLE.leaves(), BWGWood.YUCCA_LEAVES.get(), BWGWood.FLOWERING_YUCCA_LEAVES.get(), BWGWood.RIPE_YUCCA_LEAVES.get(), BWGWood.CYPRESS.leaves(),
                BWGBlocks.BOREALIS_ICE.get(), BWGBlocks.PACKED_BOREALIS_ICE.get(), BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get());
    }
}
