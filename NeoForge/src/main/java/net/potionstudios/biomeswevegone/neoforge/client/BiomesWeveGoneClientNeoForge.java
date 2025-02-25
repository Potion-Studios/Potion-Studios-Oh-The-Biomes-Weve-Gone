package net.potionstudios.biomeswevegone.neoforge.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.client.BiomesWeveGoneClient;

/**
 * This class is used to initialize the Forge client side of the mod.
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
    }
}