package net.potionstudios.biomeswevegone.neoforge.datagen.generators;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.client.BWGSounds;

/**
 * Generates the Sounds.json file
 * @see SoundDefinitionsProvider
 * @author Joseph T. McQuigg
 */
public class SoundDefinitionsGenerator extends SoundDefinitionsProvider {
    /**
     * Creates a new instance of this data provider.
     *
     * @param output The {@linkplain PackOutput} instance provided by the data generator.
     * @param helper The existing file helper provided by the event you are initializing this provider in.
     */
    public SoundDefinitionsGenerator(PackOutput output, ExistingFileHelper helper) {
        super(output, BiomesWeveGone.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        add(BWGSounds.ODDION_AMBIENT, definition().with(sound(BiomesWeveGone.id("entity/oddion/oddion_ambient"))).subtitle(subtitle("entity.oddion_ambient")));
        add(BWGSounds.ODDION_HURT, definition().with(sound(BiomesWeveGone.id("entity/oddion/oddion_hurt"))).subtitle(subtitle("entity.oddion_hurt")));
        add(BWGSounds.ODDION_DEATH, definition().with(sound(BiomesWeveGone.id("entity/oddion/oddion_death"))).subtitle(subtitle("entity.oddion_death")));
        add(BWGSounds.ODDION_HAPPY, definition().with(sound(BiomesWeveGone.id("entity/oddion/oddion_happy"))).subtitle(subtitle("entity.oddion_happy")));
        add(BWGSounds.SOUL_FRUIT_WAIL, definition().with(sound(BiomesWeveGone.id("block/soul_fruit_wail"))).subtitle(subtitle("block.soul_fruit_wail")));
        add(BWGSounds.MUSIC_DISC_PIXIE_CLUB.get().value(), definition().with(sound(BiomesWeveGone.id("music/disc/pixie_club")).stream(true)));
        add(BWGSounds.MUSIC_BIOME_CRAG_GARDENS.get().value(), definition().with(sound(BiomesWeveGone.id("music/overworld/crag_gardens"))));
        add(BWGSounds.MUSIC_BIOME_ERODED_BOREALIS.get().value(), definition().with(sound(BiomesWeveGone.id("music/overworld/eroded_borealis"))));
        add(BWGSounds.MUSIC_BIOME_FORGOTTEN_FOREST.get().value(), definition().with(sound(BiomesWeveGone.id("music/overworld/forgotten_forest"))));
    }

    private String subtitle(String subtitle) {
        return "subtitles." + subtitle;
    }
}
