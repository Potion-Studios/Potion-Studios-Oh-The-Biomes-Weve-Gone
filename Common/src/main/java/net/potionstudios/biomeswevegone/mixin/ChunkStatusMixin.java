package net.potionstudios.biomeswevegone.mixin;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.Noises;
import net.potionstudios.biomeswevegone.world.level.levelgen.customterrain.BasaltBarreraExtension;
import net.potionstudios.biomeswevegone.world.level.levelgen.customterrain.CragGardenExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(ChunkStatus.class)
public abstract class ChunkStatusMixin {

    @Inject(method = "method_16569", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void injectCragTerrain(ChunkStatus chunkStatus, ServerLevel serverLevel, ChunkGenerator chunkGenerator, List<?> list, ChunkAccess chunkAccess, CallbackInfo ci, WorldGenRegion worldGenRegion) {
        CragGardenExtension.runCragGardenExtension(worldGenRegion::getBiome, chunkAccess, serverLevel.getSeed(), worldGenRegion.registryAccess().registryOrThrow(Registries.NOISE).getOrThrow(Noises.SURFACE), worldGenRegion.registryAccess().registryOrThrow(Registries.NOISE).getOrThrow(Noises.SURFACE_SECONDARY));
        BasaltBarreraExtension.runBasaltBarreraExtension(chunkAccess, worldGenRegion, chunkGenerator);
    }
}