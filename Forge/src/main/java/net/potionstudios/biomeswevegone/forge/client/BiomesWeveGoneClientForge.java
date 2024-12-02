package net.potionstudios.biomeswevegone.forge.client;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StemBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.potionstudios.biomeswevegone.client.BiomesWeveGoneClient;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * This class is used to initialize the Forge client side of the mod.
 * @see FMLClientSetupEvent
 * @see BiomesWeveGoneClient
 * @author Joseph T. McQuigg
 */
@OnlyIn(Dist.CLIENT)
public class BiomesWeveGoneClientForge {

    /**
     * Initializes the client side of the Forge mod.
     * @param eventBus The event bus to register the client side of the mod to.
     */
    public static void init(final IEventBus eventBus) {
        eventBus.addListener((FMLClientSetupEvent event) -> BiomesWeveGoneClient.onInitialize());
        eventBus.addListener((Consumer<EntityRenderersEvent.RegisterRenderers>) event -> BiomesWeveGoneClient.registerEntityRenderers(event::registerEntityRenderer));
        eventBus.addListener((Consumer<EntityRenderersEvent.RegisterRenderers>) event -> BiomesWeveGoneClient.registerBlockEntityRenderers(event::registerBlockEntityRenderer));
        eventBus.addListener((RegisterParticleProvidersEvent event) -> BiomesWeveGoneClient.registerParticles((type, spriteProviderFactory) -> event.registerSpriteSet(type, spriteProviderFactory::apply)));
        eventBus.addListener((Consumer<EntityRenderersEvent.RegisterLayerDefinitions>) event -> BiomesWeveGoneClient.registerLayerDefinitions(event::registerLayerDefinition));
        eventBus.addListener(BiomesWeveGoneClientForge::registerColorChangingBlocks);
        eventBus.addListener(BiomesWeveGoneClientForge::registerItemColorHandlers);
    }

    /**
     * Registers the blocks that change color based on the biome.
     * @param event The event to register the blocks to.
     * @see RegisterColorHandlersEvent.Block
     */
    private static void registerColorChangingBlocks(final RegisterColorHandlersEvent.Block event) {
        event.register((state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getAverageGrassColor(view, pos) : GrassColor.getDefaultColor(), BWGBlocks.FLOWER_PATCH.get(), BWGBlocks.TINY_LILY_PADS.get(), BWGBlocks.FLOWERING_TINY_LILY_PADS.get(), BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get(), BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.WHITE_SAKURA_PETALS.get(), BWGBlocks.YELLOW_SAKURA_PETALS.get());
        event.register((state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getAverageFoliageColor(view, pos) : FoliageColor.get(0.5D, 1.0D), BWGBlocks.CLOVER_PATCH.get(), BWGBlocks.LEAF_PILE.get(), BWGBlocks.POISON_IVY.get(), BWGWood.MAHOGANY.leaves(),
                BWGWood.WILLOW.leaves(), BWGWood.MAPLE.leaves(), BWGWood.YUCCA_LEAVES.get(), BWGWood.FLOWERING_YUCCA_LEAVES.get(), BWGWood.RIPE_YUCCA_LEAVES.get(), BWGWood.CYPRESS.leaves());
        event.register((state, view, pos, tintIndex) -> BiomesWeveGoneClient.getBorealisIceColor(Objects.requireNonNullElse(pos, BlockPos.ZERO)), BWGBlocks.BOREALIS_ICE.get(), BWGBlocks.PACKED_BOREALIS_ICE.get());
        event.register((state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getAverageWaterColor(view, pos) : -1, BWGBlocks.CARVED_BARREL_CACTUS.get());
        event.register((state, view, pos, tintIndex) -> {
            int age = state.getValue(StemBlock.AGE);
            return FastColor.ARGB32.color(age * 32, 255 - age, age *4);
        }, BWGBlocks.PALE_PUMPKIN_STEM.get());
        event.register((state, view, pos, tintIndex) -> -2046180, BWGBlocks.ATTACHED_PALE_PUMPKIN_STEM.get());
    }

    /**
     * Registers the item color handlers for the mod.
     * @param event The event to register the item color handlers to.
     * @see RegisterColorHandlersEvent.Item
     */
    private static void registerItemColorHandlers(final RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
            Block block = ((BlockItem) stack.getItem()).getBlock();
            return event.getBlockColors().getColor(block.defaultBlockState(), null, null, tintIndex);
        }, BWGBlocks.TINY_LILY_PADS.get(), BWGBlocks.FLOWERING_TINY_LILY_PADS.get(), BWGBlocks.CLOVER_PATCH.get(), BWGBlocks.LEAF_PILE.get(), BWGBlocks.POISON_IVY.get()
                , BWGWood.MAHOGANY.leaves(), BWGWood.WILLOW.leaves(), BWGWood.MAPLE.leaves(), BWGWood.YUCCA_LEAVES.get(), BWGWood.FLOWERING_YUCCA_LEAVES.get(), BWGWood.RIPE_YUCCA_LEAVES.get(), BWGWood.CYPRESS.leaves(), BWGBlocks.LUSH_GRASS_BLOCK.get()
                , BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get());
    }

}
