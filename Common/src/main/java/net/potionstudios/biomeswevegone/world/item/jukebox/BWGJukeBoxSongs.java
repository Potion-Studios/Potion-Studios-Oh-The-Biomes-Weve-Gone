package net.potionstudios.biomeswevegone.world.item.jukebox;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.sounds.BWGSounds;

import java.util.Map;
import java.util.function.Supplier;

public interface BWGJukeBoxSongs {
    Map<ResourceKey<JukeboxSong>, JukeBoxSongFactory> JUKEBOX_SONG_FACTORIES = new Reference2ObjectOpenHashMap<>();

    ResourceKey<JukeboxSong> PIXIE_CLUB = register("pixie_club", BWGSounds.MUSIC_DISC_PIXIE_CLUB, 213, 4);

    private static ResourceKey<JukeboxSong> register(String id, Supplier<Holder.Reference<SoundEvent>> soundEvent, int lengthInSeconds, int comparatorOutput) {
        ResourceKey<JukeboxSong> key = BiomesWeveGone.key(Registries.JUKEBOX_SONG, id);
        JUKEBOX_SONG_FACTORIES.put(key, context -> new JukeboxSong(soundEvent.get(), Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), lengthInSeconds, comparatorOutput));
        return key;
    }

    @FunctionalInterface
    interface JukeBoxSongFactory{
        JukeboxSong generate(BootstrapContext<JukeboxSong> context);
    }
}
