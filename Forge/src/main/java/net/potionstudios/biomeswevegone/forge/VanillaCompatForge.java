package net.potionstudios.biomeswevegone.forge;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
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
public class VanillaCompatForge {
    public static void init() {
        ToolInteractions.registerStrippableBlocks((block, stripped) -> {
            AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);
            AxeItem.STRIPPABLES.put(block, stripped);
        });
        BlockFeatures.registerFlammable(((FireBlock) Blocks.FIRE)::setFlammable);
        BlockFeatures.registerCompostables((item, chance) -> ComposterBlock.add(chance, item));
        ToolInteractions.registerFlattenables(ShovelItem.FLATTENABLES::put);
    }

    public static void registerVanillaCompatEvents(IEventBus bus) {
        bus.addListener(VanillaCompatForge::registerTillables);
        bus.addListener(VanillaCompatForge::registerFuels);
        if (BWGTradesConfig.INSTANCE.get().enableTrades()) bus.addListener(VanillaCompatForge::onVillagerTrade);
        bus.addListener(VanillaCompatForge::registerBrewingRecipes);
        bus.addListener(VanillaCompatForge::onBoneMealUse);
    }

    private static void registerTillables(final BlockEvent.BlockToolModificationEvent event) {
        if (event.getToolAction() == ToolActions.HOE_TILL && event.getLevel().getBlockState(event.getPos().above()).isAir()) {
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
     * Register fuels for the furnace.
     * @see FurnaceFuelBurnTimeEvent
     */
    private static void registerFuels(final FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().is(BWGBlocks.PEAT.get().asItem()))
            event.setBurnTime(1200);
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
     * @see BrewingRecipeRegisterEvent
     */
    private static void registerBrewingRecipes(final BrewingRecipeRegisterEvent event) {
        BWGBrewingRecipes.buildBrewingRecipes(event.getBuilder()::addMix);
    }

    private static void onBoneMealUse(final BonemealEvent event) {
        if (event.getLevel().isClientSide()) return;
        ServerLevel level = (ServerLevel) event.getLevel();
        if (event.getBlock().is(Blocks.GRASS_BLOCK) && level.getBiome(event.getPos()).is(BWGBiomes.PRAIRIE)) {
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
            event.setResult(Event.Result.ALLOW);
        }
    }
}
