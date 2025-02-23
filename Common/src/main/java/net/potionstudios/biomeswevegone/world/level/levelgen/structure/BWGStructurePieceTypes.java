package net.potionstudios.biomeswevegone.world.level.levelgen.structure;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.arch.ArchPiece;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.lake.LargeLakePiece;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.plateau.GourPlateauPiece;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.sharpenedrock.SharpenedRockPiece;

import java.util.function.Supplier;

public class BWGStructurePieceTypes {

    public static final Supplier<StructurePieceType> SHARPENED_ROCK_PIECE = create("sharpened_rock_piece", () -> SharpenedRockPiece::new);
    public static final Supplier<StructurePieceType> GOUR_PLATEAU_PIECE = create("our_plateau_piece", () -> GourPlateauPiece::new);
    public static final Supplier<StructurePieceType> LARGE_LAKE = create("large_lake", () -> LargeLakePiece::new);
    public static final Supplier<StructurePieceType> ARCH_PIECE = create("arch_piece", () -> ArchPiece::new);

    public static Supplier<StructurePieceType> create(String id, Supplier<StructurePieceType> structureTypeSupplier) {
        return PlatformHandler.PLATFORM_HANDLER.register(BuiltInRegistries.STRUCTURE_PIECE, id, structureTypeSupplier);
    }

    public static void structurePieceTypes() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Structure Pieces");
    }
}
