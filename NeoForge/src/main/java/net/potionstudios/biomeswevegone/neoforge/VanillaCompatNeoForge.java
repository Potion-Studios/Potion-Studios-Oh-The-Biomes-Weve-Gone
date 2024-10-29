package net.potionstudios.biomeswevegone.neoforge;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.potionstudios.biomeswevegone.world.entity.npc.BWGTradesConfig;
import net.potionstudios.biomeswevegone.world.entity.npc.BWGVillagerTrades;
import net.potionstudios.biomeswevegone.world.item.brewing.BWGBrewingRecipes;
import net.potionstudios.biomeswevegone.world.item.tools.ToolInteractions;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.BlockFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGOverworldVegationPlacedFeatures;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Used for Vanilla compatibility on the Forge platform.
 * @author Joseph T. McQuigg
 */
public class VanillaCompatNeoForge {
    public static void init() {
        ToolInteractions.registerStrippableBlocks((block, stripped) -> {
            AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);
            AxeItem.STRIPPABLES.put(block, stripped);
        });
        BlockFeatures.registerFlammable(((FireBlock) Blocks.FIRE)::setFlammable);
        ToolInteractions.registerFlattenables(ShovelItem.FLATTENABLES::put);
    }

    public static void registerVanillaCompatEvents(IEventBus bus) {
        bus.addListener(VanillaCompatNeoForge::registerTillables);
        if (BWGTradesConfig.INSTANCE.get().enableTrades()) bus.addListener(VanillaCompatNeoForge::onVillagerTrade);
        bus.addListener(VanillaCompatNeoForge::onBoneMealUse);
        bus.addListener(VanillaCompatNeoForge::registerBrewingRecipes);
    }

    private static void registerTillables(final BlockEvent.BlockToolModificationEvent event) {
        if (event.getItemAbility() == ItemAbilities.HOE_TILL && event.getLevel().getBlockState(event.getPos().above()).isAir()) {
            BlockState state = event.getState();
            if (state.is(BWGBlocks.LUSH_GRASS_BLOCK.get()) || state.is(BWGBlocks.LUSH_DIRT.get()))
                event.setFinalState(BWGBlocks.LUSH_FARMLAND.get().defaultBlockState());
            else if (state.is(BWGBlocks.SANDY_DIRT.get()))
                event.setFinalState(BWGBlocks.SANDY_FARMLAND.get().defaultBlockState());
            else if (state.is(BWGBlocks.PEAT.get()))
                event.setFinalState(Blocks.FARMLAND.defaultBlockState());
        }
    }

    /**
     * Register villager trades.
     * @see VillagerTradesEvent
     */
    private static void onVillagerTrade(final VillagerTradesEvent event) {
        if (BWGVillagerTrades.TRADES.containsKey(event.getType())) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            BWGVillagerTrades.TRADES.get(event.getType())
                    .forEach(pair -> trades.get(pair.getFirst().intValue()).add((trader, random) -> pair.getSecond()));
        }
    }

    /**
     * Register brewing recipes.
     * @see RegisterBrewingRecipesEvent
     */
    public static void registerBrewingRecipes(final RegisterBrewingRecipesEvent event) {
        BWGBrewingRecipes.buildBrewingRecipes(event.getBuilder()::addMix);
    }

    private static void onBoneMealUse(final BonemealEvent event) {
        if (event.getLevel().isClientSide()) return;
        ServerLevel level = (ServerLevel) event.getLevel();
        if (event.getState().is(Blocks.GRASS_BLOCK) && level.getBiome(event.getPos()).is(BWGBiomes.PRAIRIE)) {
            BlockPos blockPos = event.getPos().above();
            BlockState blockState = BWGBlocks.PRAIRIE_GRASS.get().defaultBlockState();
            Optional<Holder.Reference<PlacedFeature>> optional = level.registryAccess()
                    .registryOrThrow(Registries.PLACED_FEATURE)
                    .getHolder(BWGOverworldVegationPlacedFeatures.PRAIRIE_GRASS_BONEMEAL);

            label49:
            for(int i = 0; i < 128; ++i) {
                BlockPos blockPos2 = blockPos;
                RandomSource random = level.getRandom();
                for(int j = 0; j < i / 16; ++j) {
                    blockPos2 = blockPos2.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                    if (!level.getBlockState(blockPos2.below()).is(Blocks.GRASS_BLOCK) || level.getBlockState(blockPos2).isCollisionShapeFullBlock(level, blockPos2))
                        continue label49;
                }

                BlockState blockState2 = level.getBlockState(blockPos2);
                if (blockState2.is(blockState.getBlock()) && random.nextInt(10) == 0)
                    ((BonemealableBlock)blockState.getBlock()).performBonemeal(level, random, blockPos2, blockState2);


                if (blockState2.isAir()) {
                    Holder<PlacedFeature> holder;
                    if (random.nextInt(8) == 0) {
                        List<ConfiguredFeature<?, ?>> list = level.getBiome(blockPos2).value().getGenerationSettings().getFlowerFeatures();
                        if (list.isEmpty()) continue;

                        holder = ((RandomPatchConfiguration) list.getFirst().config()).feature();
                    } else {
                        if (!optional.isPresent()) continue;
                        holder = optional.get();
                    }

                    holder.value().place(level, level.getChunkSource().getGenerator(), random, blockPos2);
                }
            }
            event.setSuccessful(true);
        }
    }
}
