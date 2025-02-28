package net.potionstudios.biomeswevegone.client;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import net.potionstudios.biomeswevegone.client.particle.BWGParticles;
import net.potionstudios.biomeswevegone.client.particle.particles.FallingLeafParticle;
import net.potionstudios.biomeswevegone.client.particle.particles.FireFlyParticle;
import net.potionstudios.biomeswevegone.client.renderer.entity.boat.BWGBoatRenderer;
import net.potionstudios.biomeswevegone.world.entity.BWGEntities;
import net.potionstudios.biomeswevegone.world.entity.boats.BWGBoatEntity;
import net.potionstudios.biomeswevegone.client.renderer.entity.manowar.ManOWarRenderer;
import net.potionstudios.biomeswevegone.client.renderer.entity.oddion.OddionRenderer;
import net.potionstudios.biomeswevegone.client.renderer.entity.pumpkinwarden.PumpkinWardenRenderer;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.entities.BWGBlockEntities;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWoodSet;

import java.awt.*;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * The common client class for Oh The Biomes We've Gone.
 * This class is used for client-side only code.
 * @author Joseph T. McQuigg
 */
public class BiomesWeveGoneClient {

    /**
     * Initializes the client-side Common code for Oh The Biomes We've Gone.
     */
    public static void onInitialize() {
        BWGWoodSet.woodsets().forEach(set -> registerWoodTypes(set.woodType()));
    }

    /**
     * Registers the wood types for the sign materials.
     * @param woodType the wood type to register
     */
    private static void registerWoodTypes(WoodType woodType) {
        Sheets.SIGN_MATERIALS.put(woodType, Sheets.createSignMaterial(woodType));
        Sheets.HANGING_SIGN_MATERIALS.put(woodType, Sheets.createHangingSignMaterial(woodType));
    }

    /**
     * Registers the entity renderers.
     * @see EntityRenderers
     * @see BWGEntities
     */
    public static void registerEntityRenderers(BiConsumer<EntityType<? extends Entity>, EntityRendererProvider> consumer) {
        consumer.accept(BWGEntities.MAN_O_WAR.get(), ManOWarRenderer::new);
        consumer.accept(BWGEntities.PUMPKIN_WARDEN.get(), PumpkinWardenRenderer::new);
        consumer.accept(BWGEntities.ODDION.get(), OddionRenderer::new);
        consumer.accept(BWGEntities.BWG_BOAT.get(), context -> new BWGBoatRenderer(context, false));
        consumer.accept(BWGEntities.BWG_CHEST_BOAT.get(), context -> new BWGBoatRenderer(context, true));
    }

    /**
     * Registers the block key renderers.
     * @see BlockEntityRenderers
     * @see BWGBlockEntities
     */
    public static void registerBlockEntityRenderers(BiConsumer<BlockEntityType<? extends BlockEntity>, BlockEntityRendererProvider> consumer) {
        consumer.accept(BWGBlockEntities.SIGNS.get(), SignRenderer::new);
        consumer.accept(BWGBlockEntities.HANGING_SIGNS.get(), HangingSignRenderer::new);
    }

    /**
     * Registers the layer definitions for the boat models.
     * @see BWGBoatRenderer
     */
    public static void registerLayerDefinitions(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> consumer) {
        for (BWGBoatEntity.Type type : BWGBoatEntity.Type.values()) {
            consumer.accept(BWGBoatRenderer.createBoatModelName(type), BoatModel::createBodyModel);
            consumer.accept(BWGBoatRenderer.createChestBoatModelName(type), ChestBoatModel::createBodyModel);
        }
    }

    /**
     * Registers the Particle Providers.
     * @see ParticleProvider
     */
    public static void registerParticles(BiConsumer<SimpleParticleType, Function<SpriteSet, ParticleProvider<SimpleParticleType>>> consumer) {
        consumer.accept(BWGParticles.FIREFLY.get(), FireFlyParticle.Provider::new);
        consumer.accept(BWGParticles.BOREALIS_GLINT.get(), FallingLeafParticle.Provider::new);
        consumer.accept(BWGParticles.WITCH_HAZEL_LEAVES.get(), FallingLeafParticle.Provider::new);
        consumer.accept(BWGParticles.WHITE_SAKURA_LEAVES.get(), FallingLeafParticle.Provider::new);
        consumer.accept(BWGParticles.YELLOW_SAKURA_LEAVES.get(), FallingLeafParticle.Provider::new);
        consumer.accept(BWGParticles.RED_MAPLE_LEAVES.get(), FallingLeafParticle.Provider::new);
        consumer.accept(BWGParticles.SILVER_MAPLE_LEAVES.get(), FallingLeafParticle.Provider::new);
        consumer.accept(BWGParticles.IRONWOOD_LEAVES.get(), FallingLeafParticle.Provider::new);
        consumer.accept(BWGParticles.SPIRIT.get(), FallingLeafParticle.Provider::new);
        consumer.accept(BWGParticles.SPIRIT_LEAVES.get(), FallingLeafParticle.Provider::new);
    }

    /**
     * Registers the block colors.
     * @see BlockColors
     */
    public static void registerBlockColors(BiConsumer<BlockColor, Block[]> consumer) {
        consumer.accept((state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getAverageGrassColor(view, pos) : GrassColor.getDefaultColor(), new Block[] {BWGBlocks.FLOWER_PATCH.get(), BWGBlocks.TINY_LILY_PADS.get(), BWGBlocks.FLOWERING_TINY_LILY_PADS.get(), BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.WHITE_OVERGROWN_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get(), BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.WHITE_SAKURA_PETALS.get(), BWGBlocks.YELLOW_SAKURA_PETALS.get()});
        consumer.accept((state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getAverageFoliageColor(view, pos) : FoliageColor.get(0.5D, 1.0D), new Block[] {
                BWGBlocks.CLOVER_PATCH.get(), BWGBlocks.LEAF_PILE.get(), BWGBlocks.POISON_IVY.get(), BWGWood.MAHOGANY.leaves(),
                BWGWood.WILLOW.leaves(), BWGWood.MAPLE.leaves(), BWGWood.YUCCA_LEAVES.get(), BWGWood.FLOWERING_YUCCA_LEAVES.get(), BWGWood.RIPE_YUCCA_LEAVES.get(), BWGWood.CYPRESS.leaves()});
        consumer.accept((state, view, pos, tintIndex) -> getBorealisIceColor(Objects.requireNonNullElse(pos, BlockPos.ZERO)), new Block[] {BWGBlocks.BOREALIS_ICE.get(), BWGBlocks.PACKED_BOREALIS_ICE.get()});
        consumer.accept((state, view, pos, tintIndex) -> view != null && pos != null ? BiomeColors.getAverageWaterColor(view, pos) : -1, new Block[] {BWGBlocks.CARVED_BARREL_CACTUS.get()});
        consumer.accept((state, view, pos, tintIndex) -> {
            int age = state.getValue(StemBlock.AGE);
            return FastColor.ARGB32.color(age * 32, 255 - age, age *4);
        }, new Block[] {BWGBlocks.PALE_PUMPKIN_STEM.get()});
        consumer.accept((state, view, pos, tintIndex) -> -2046180, new Block[] {BWGBlocks.ATTACHED_PALE_PUMPKIN_STEM.get()});
    }

    /**
     * Registers the item colors.
     * @see ItemColors
     */
    public static void registerBlockItemColors(Consumer<Block[]> consumer) {
        consumer.accept(new Block[]{BWGBlocks.TINY_LILY_PADS.get(), BWGBlocks.FLOWERING_TINY_LILY_PADS.get(), BWGBlocks.CLOVER_PATCH.get(), BWGBlocks.LEAF_PILE.get(), BWGBlocks.POISON_IVY.get()
                , BWGWood.MAHOGANY.leaves(), BWGWood.WILLOW.leaves(), BWGWood.MAPLE.leaves(), BWGWood.YUCCA_LEAVES.get(), BWGWood.FLOWERING_YUCCA_LEAVES.get(), BWGWood.RIPE_YUCCA_LEAVES.get(),
                BWGWood.CYPRESS.leaves(), BWGBlocks.LUSH_GRASS_BLOCK.get(), BWGBlocks.OVERGROWN_DACITE.get(), BWGBlocks.WHITE_OVERGROWN_DACITE.get(), BWGBlocks.OVERGROWN_STONE.get()});
    }
    
    private static final ImprovedNoise NOISE = new ImprovedNoise(new XoroshiroRandomSource(1));

    private static int getBorealisIceColor(BlockPos pos) {
        float frequency = 0.01F;

        float factor = (float) ((NOISE.noise(pos.getX() * frequency, pos.getY() * frequency, pos.getZ() * frequency) + 1)* 0.5F);

        float startHue = 320;
        float endHue = 120;

        float hue =  startHue + (endHue - startHue) * factor;

        return Color.getHSBColor(hue / 360F, 0.6F, 1).getRGB();
    }
}
