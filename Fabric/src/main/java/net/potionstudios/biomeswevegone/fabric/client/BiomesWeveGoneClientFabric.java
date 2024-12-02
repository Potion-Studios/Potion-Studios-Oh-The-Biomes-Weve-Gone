package net.potionstudios.biomeswevegone.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.potionstudios.biomeswevegone.client.BiomesWeveGoneClient;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.custom.BWGSpreadableBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.cactus.BWGCactusBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.fruit.BWGFruitBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.GlowCaneBlock;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail.CattailSproutBlock;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;

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
        BiomesWeveGoneClient.onInitialize();
        registerRenderTypes();
        BiomesWeveGoneClient.registerEntityRenderers(EntityRendererRegistry::register);
        BiomesWeveGoneClient.registerBlockEntityRenderers(BlockEntityRenderers::register);
        BiomesWeveGoneClient.registerParticles((type, spriteProviderFactory) -> ParticleFactoryRegistry.getInstance().register(type, spriteProviderFactory::apply));
        BiomesWeveGoneClient.registerLayerDefinitions((a, b) -> EntityModelLayerRegistry.registerModelLayer(a, b::get));
        BiomesWeveGoneClient.registerBlockColors(ColorProviderRegistry.BLOCK::register);
        registerItemColorHandlers();
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

    /**
     * Registers the item colors.
     * @see ColorProviderRegistry.ITEM
     */
    private void registerItemColorHandlers() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            Block block = ((BlockItem) stack.getItem()).getBlock();
            return ColorProviderRegistry.BLOCK.get(block).getColor(block.defaultBlockState(), null, null, tintIndex);
        }, BWGBlocks.TINY_LILY_PADS.get(), BWGBlocks.FLOWERING_TINY_LILY_PADS.get(), BWGBlocks.CLOVER_PATCH.get(), BWGBlocks.LEAF_PILE.get(), BWGBlocks.POISON_IVY.get()
                , BWGWood.MAHOGANY.leaves(), BWGWood.WILLOW.leaves(), BWGWood.MAPLE.leaves(), BWGWood.YUCCA_LEAVES.get(), BWGWood.FLOWERING_YUCCA_LEAVES.get(), BWGWood.RIPE_YUCCA_LEAVES.get(), BWGWood.CYPRESS.leaves(),
                BWGBlocks.BOREALIS_ICE.get(), BWGBlocks.PACKED_BOREALIS_ICE.get(), BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get());
    }
}
