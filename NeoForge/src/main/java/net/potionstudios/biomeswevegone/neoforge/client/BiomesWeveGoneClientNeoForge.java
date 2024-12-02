package net.potionstudios.biomeswevegone.neoforge.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.client.BiomesWeveGoneClient;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;

/**
 * This class is used to initialize the Forge client side of the mod.
 * @see FMLClientSetupEvent
 * @see BiomesWeveGoneClient
 * @author Joseph T. McQuigg
 */
@Mod(value = BiomesWeveGone.MOD_ID, dist = Dist.CLIENT)
public class BiomesWeveGoneClientNeoForge {

    /**
     * Constructor for the client side of the NeoForge mod.
     * @param eventBus The event bus to register the client side of the mod to.
     */
    public BiomesWeveGoneClientNeoForge(final IEventBus eventBus) {
        eventBus.addListener((FMLClientSetupEvent event) -> BiomesWeveGoneClient.onInitialize());
        eventBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> BiomesWeveGoneClient.registerEntityRenderers(event::registerEntityRenderer));
        eventBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> BiomesWeveGoneClient.registerBlockEntityRenderers(event::registerBlockEntityRenderer));
        eventBus.addListener((RegisterParticleProvidersEvent event) -> BiomesWeveGoneClient.registerParticles((type, spriteProviderFactory) -> event.registerSpriteSet(type, spriteProviderFactory::apply)));
        eventBus.addListener((EntityRenderersEvent.RegisterLayerDefinitions event) -> BiomesWeveGoneClient.registerLayerDefinitions(event::registerLayerDefinition));
        eventBus.addListener((RegisterColorHandlersEvent.Block event) -> BiomesWeveGoneClient.registerBlockColors(event::register));
        eventBus.addListener((RegisterColorHandlersEvent.Item event) -> BiomesWeveGoneClient.registerBlockItemColors(consumer -> event.register((stack, tintIndex) -> {
            Block block = ((BlockItem) stack.getItem()).getBlock();
            return event.getBlockColors().getColor(block.defaultBlockState(), null, null, tintIndex);
        }, consumer)));
        eventBus.addListener(BiomesWeveGoneClientNeoForge::registerGUILayers);
    }

    /**
     * Registers the GUI layers for the mod.
     * @see RegisterGuiLayersEvent
     */
    private static void registerGUILayers(final RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.CAMERA_OVERLAYS, BiomesWeveGone.id("textures/misc/palepumpkinblur.png"), (guiGraphics, deltaTracker) -> {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.options.getCameraType().isFirstPerson()) {
	            assert minecraft.player != null;
	            if (!minecraft.player.isScoping()) {
                    if (minecraft.player.getInventory().getArmor(3).is(BWGBlocks.CARVED_PALE_PUMPKIN.get().asItem())){
                        RenderSystem.disableDepthTest();
                        RenderSystem.depthMask(false);
                        RenderSystem.enableBlend();
                        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
                        guiGraphics.blit(BiomesWeveGone.id("textures/misc/palepumpkinblur.png"), 0, 0, -90, 0.0F, 0.0F, guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics.guiWidth(), guiGraphics.guiHeight());
                        RenderSystem.disableBlend();
                        RenderSystem.depthMask(true);
                        RenderSystem.enableDepthTest();
                        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
                    }
                }
            }
        });
    }
}