package net.potionstudios.biomeswevegone.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.potionstudios.biomeswevegone.BiomesWeveGone;

/**
 * Holds Custom Structure Tags for Biomes We've Gone
 * @see net.minecraft.tags.StructureTags
 * @author Joseph T. McQuigg
 */
public interface BWGStructureTags {

	TagKey<Structure> PRAIRIE_HOUSES = create("prairie_houses");
	TagKey<Structure> ASPEN_MANORS = create("aspen_manors");
	TagKey<Structure> VILLAGE = create("village");

	private static TagKey<Structure> create(String name) {
		return TagKey.create(Registries.STRUCTURE, BiomesWeveGone.id(name));
	}
}
