package net.potionstudios.biomeswevegone.client;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;

import java.util.function.Supplier;

/**
 * Sounds for Oh The Biomes We've Gone.
 * @author YaBoiChips, Joseph T. McQuigg
 */
public class BWGSounds {

    public static final Supplier<SoundEvent> ODDION_DEATH = createFixedRangeEvent("oddion.die", 8F);
    public static final Supplier<SoundEvent> ODDION_HURT = createFixedRangeEvent("oddion.hurt", 8F);
    public static final Supplier<SoundEvent> ODDION_AMBIENT = createFixedRangeEvent("oddion.ambient", 8F);
    public static final Supplier<SoundEvent> ODDION_HAPPY = createFixedRangeEvent("oddion.happy", 8F);

    public static final Supplier<SoundEvent> SOUL_FRUIT_WAIL = createFixedRangeEvent("soul_fruit.wail", 8F);

    public static final Supplier<Holder.Reference<SoundEvent>> MUSIC_DISC_PIXIE_CLUB = registerSoundEventHolder("music_disc.pixie_club");

    public static final Supplier<Holder.Reference<SoundEvent>> MUSIC_BIOME_CRAG_GARDENS = registerSoundEventHolder("music.overworld.crag_gardens");
    public static final Supplier<Holder.Reference<SoundEvent>> MUSIC_BIOME_ERODED_BOREALIS = registerSoundEventHolder("music.overworld.eroded_borealis");
    public static final Supplier<Holder.Reference<SoundEvent>> MUSIC_BIOME_FORGOTTEN_FOREST = registerSoundEventHolder("music.overworld.forgotten_forest");

    private static Supplier<SoundEvent> createFixedRangeEvent(String id, float range) {
        return PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.SOUND_EVENT, id, () -> SoundEvent.createFixedRangeEvent(BiomesWeveGone.id(id), range));
    }

    private static Supplier<Holder.Reference<SoundEvent>> registerSoundEventHolder(String id) {
        return PlatformHandler.PLATFORM_HANDLER.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, () -> SoundEvent.createVariableRangeEvent(BiomesWeveGone.id(id)));
    }

    public static void sounds() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Sounds");
    }
}
