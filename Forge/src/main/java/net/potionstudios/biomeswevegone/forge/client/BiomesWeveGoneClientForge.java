package net.potionstudios.biomeswevegone.forge.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.potionstudios.biomeswevegone.client.BiomesWeveGoneClient;

/**
 * This class is used to initialize the Forge client side of the mod.
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
        eventBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> BiomesWeveGoneClient.registerEntityRenderers(event::registerEntityRenderer));
        eventBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> BiomesWeveGoneClient.registerBlockEntityRenderers(event::registerBlockEntityRenderer));
        eventBus.addListener((RegisterParticleProvidersEvent event) -> BiomesWeveGoneClient.registerParticles((type, spriteProviderFactory) -> event.registerSpriteSet(type, spriteProviderFactory::apply)));
        eventBus.addListener((EntityRenderersEvent.RegisterLayerDefinitions event) -> BiomesWeveGoneClient.registerLayerDefinitions(event::registerLayerDefinition));
        eventBus.addListener((RegisterColorHandlersEvent.Block event) -> BiomesWeveGoneClient.registerBlockColors(event::register));
    }
}
