package net.potionstudios.biomeswevegone.neoforge;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.EnderManAngerEvent;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.potionstudios.biomeswevegone.util.BoneMealHandler;
import net.potionstudios.biomeswevegone.config.configs.BWGTradesConfig;
import net.potionstudios.biomeswevegone.world.entity.npc.BWGVillagerTrades;
import net.potionstudios.biomeswevegone.world.entity.npc.BWGVillagerType;
import net.potionstudios.biomeswevegone.world.entity.pumpkinwarden.PumpkinWarden;
import net.potionstudios.biomeswevegone.world.item.brewing.BWGBrewingRecipes;
import net.potionstudios.biomeswevegone.world.item.tools.ToolInteractions;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.BlockFeatures;

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

    public static void registerVanillaCompatEvents(final IEventBus bus) {
        bus.addListener(VanillaCompatNeoForge::registerTillables);
        if (!BWGTradesConfig.INSTANCE.trades.disableTrades.value()) {
            bus.addListener(VanillaCompatNeoForge::onVillagerTrade);
            if (BWGTradesConfig.INSTANCE.wanderingTraderTrades.enableBWGItemsTrades.value())
                bus.addListener(VanillaCompatNeoForge::onWanderingTrade);
        }
        bus.addListener(VanillaCompatNeoForge::onBoneMealUse);
        bus.addListener(VanillaCompatNeoForge::registerBrewingRecipes);
        bus.addListener(VanillaCompatNeoForge::onEnderManAnger);
        bus.addListener(VanillaCompatNeoForge::onVillagerInteract);
    }

    /**
     * Register tillable blocks.
     * @see BlockEvent.BlockToolModificationEvent
     */
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
                    .forEach((level, offers) -> {
                        List<VillagerTrades.ItemListing> tradeList = trades.get(level.intValue());
                        tradeList.addAll(offers);
                    });
        }
    }

    /**
     * Register wandering trader trades.
     * @see WandererTradesEvent
     */
    private static void onWanderingTrade(final WandererTradesEvent event) {
        BWGVillagerTrades.WANDERING_TRADER_TRADES.forEach((level, offers) -> {
            for (VillagerTrades.ItemListing itemListing : offers) event.getGenericTrades().add(itemListing);
        });
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
        if (!event.getLevel().isClientSide() && BoneMealHandler.bwgBoneMealEventHandler((ServerLevel) event.getLevel(), event.getPos(), event.getState()))
            event.setSuccessful(true);
    }

    /**
     * Handle villager interaction.
     * @see PlayerInteractEvent.EntityInteractSpecific
     */
    private static void onVillagerInteract(final PlayerInteractEvent.EntityInteractSpecific event) {
        event.setCancellationResult(PumpkinWarden.villagerToPumpkinWarden(event.getTarget(), event.getItemStack(), event.getLevel()) ? InteractionResult.SUCCESS : InteractionResult.PASS);
    }
}
