package net.potionstudios.biomeswevegone.neoforge;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.EnderManAngerEvent;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.potionstudios.biomeswevegone.util.BoneMealHandler;
import net.potionstudios.biomeswevegone.config.configs.BWGTradesConfig;
import net.potionstudios.biomeswevegone.world.entity.npc.BWGVillagerTrades;
import net.potionstudios.biomeswevegone.world.item.brewing.BWGBrewingRecipes;
import net.potionstudios.biomeswevegone.world.item.tools.ToolInteractions;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.BlockFeatures;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;
import net.potionstudios.biomeswevegone.world.level.levelgen.feature.placed.BWGOverworldVegationPlacedFeatures;

import java.util.HashMap;
import java.util.List;

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
        bus.addListener(VanillaCompatNeoForge::onEnderManAnger);
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

    private static void onVillagerTrade(final VillagerTradesEvent event) {
        if (BWGVillagerTrades.TRADES.containsKey(event.getType())) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            BWGVillagerTrades.TRADES.get(event.getType())
                    .forEach((level, offers) -> {
                        List<VillagerTrades.ItemListing> tradeList = trades.get(level.intValue());
                        for (MerchantOffer offer : offers) tradeList.add((trader, random) -> offer);
                    });
        }
    }

    /**
     * Register brewing recipes.
     * @see RegisterBrewingRecipesEvent
     */
    private static void registerBrewingRecipes(final RegisterBrewingRecipesEvent event) {
        BWGBrewingRecipes.buildBrewingRecipes(event.getBuilder()::addMix);
    }

    /**
     * Handle Enderman anger.
     * @see EnderManAngerEvent
     */
    private static void onEnderManAnger(final EnderManAngerEvent event) {
        if (event.getPlayer().getItemBySlot(EquipmentSlot.HEAD).is(BWGBlocks.CARVED_PALE_PUMPKIN.get().asItem()))
            event.setCanceled(true);
    }

    /**
     * Handle bone meal use.
     * @see BonemealEvent
     */
    private static void onBoneMealUse(final BonemealEvent event) {
        if (event.getLevel().isClientSide()) return;
        ServerLevel level = (ServerLevel) event.getLevel();
        BlockPos pos = event.getPos();
        if (event.getState().is(Blocks.GRASS_BLOCK))
            if (level.getBiome(pos).is(BWGBiomes.PRAIRIE))
                event.setSuccessful(BoneMealHandler.grassBoneMealHandler(level, pos.above(), BWGBlocks.PRAIRIE_GRASS.get(), BWGOverworldVegationPlacedFeatures.PRAIRIE_GRASS_BONEMEAL, false));
            else if (level.getBiome(pos).is(BWGBiomes.ALLIUM_SHRUBLAND))
                event.setSuccessful(BoneMealHandler.grassBoneMealHandler(level, pos.above(), Blocks.SHORT_GRASS, VegetationPlacements.GRASS_BONEMEAL, true));
    }
}
