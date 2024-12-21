package net.potionstudios.biomeswevegone.fabric;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.potionstudios.biomeswevegone.config.configs.BWGTradesConfig;
import net.potionstudios.biomeswevegone.world.entity.npc.BWGVillagerTrades;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.item.brewing.BWGBrewingRecipes;
import net.potionstudios.biomeswevegone.world.item.tools.ToolInteractions;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.BlockFeatures;
import net.potionstudios.biomeswevegone.config.configs.BWGWorldGenConfig;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.modifiers.BWGBiomeModifiers;

/**
 * Used for Vanilla compatibility on the Fabric platform.
 * @author Joseph T. McQuigg
 */
public class VanillaCompatFabric {

    public static void init() {
        ToolInteractions.registerStrippableBlocks(StrippableBlockRegistry::register);
        BlockFeatures.registerFlammable(FlammableBlockRegistry.getDefaultInstance()::add);
        registerFuels();
        BlockFeatures.registerCompostables(CompostingChanceRegistry.INSTANCE::add);
        ToolInteractions.registerFlattenables(FlattenableBlockRegistry::register);
        ToolInteractions.registerTillables((block, pair) -> TillableBlockRegistry.register(block, pair.getFirst(), pair.getSecond()));
        registerBiomeModifiers();
        registerLootModifiers();
        if (!BWGTradesConfig.INSTANCE.trades.disableTrades.value()) {
            registerTrades();
            if (BWGTradesConfig.INSTANCE.wanderingTraderTrades.enableBWGItemsTrades.value())
                registerWanderingTrades();
        }
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> BWGBrewingRecipes.buildBrewingRecipes(builder::addMix));
    }

    private static void registerFuels() {
        FuelRegistry.INSTANCE.add(BWGBlocks.PEAT.get(), 1200);
    }

    private static void registerBiomeModifiers() {
        if (BWGWorldGenConfig.INSTANCE.get().vanillaAdditions()) {
            BWGBiomeModifiers.init();
            BWGBiomeModifiers.BIOME_MODIFIERS_FACTORIES.values().stream().filter(BWGBiomeModifiers.BWGBiomeModifier::enabled).forEach((modifier) ->
                    BiomeModifications.addFeature(BiomeSelectors.includeByKey(modifier.biomes()), modifier.step(), modifier.feature()));
        }
    }

    private static void registerLootModifiers() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries)  -> {
            if (key.equals(BuiltInLootTables.SNIFFER_DIGGING)) {
                LootPool.Builder pool = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .with(LootItem.lootTableItem(BWGItems.FLUORESCENT_CATTAIL_SPROUT.get()).build())
                        .with(LootItem.lootTableItem(BWGItems.BLUE_GLOWCANE_SHOOT.get()).build())
                        .with(LootItem.lootTableItem(BWGItems.GREEN_GLOWCANE_SHOOT.get()).build())
                        .with(LootItem.lootTableItem(BWGItems.RED_GLOWCANE_SHOOT.get()).build())
                        .with(LootItem.lootTableItem(BWGItems.YELLOW_GLOWCANE_SHOOT.get()).build())
                        .with(LootItem.lootTableItem(BWGItems.PALE_PUMPKIN_SEEDS.get()).build());
                tableBuilder.withPool(pool);
            }
        });
    }

    private static void registerTrades() {
        BWGVillagerTrades.makeTrades();
        if (BWGVillagerTrades.TRADES.isEmpty()) return;
        BWGVillagerTrades.TRADES.forEach((villagerProfession, offersMap) ->
                offersMap.forEach((level, offers) ->
                        TradeOfferHelper.registerVillagerOffers(villagerProfession, level, factory -> {
                            for (MerchantOffer offer : offers) factory.add((trader, random) -> offer);
                        })
                )
        );
    }

    private static void registerWanderingTrades() {
        if (!BWGTradesConfig.INSTANCE.wanderingTraderTrades.enableBWGItemsTrades.value()) return;
        BWGVillagerTrades.makeWanderingTrades();
        BWGVillagerTrades.WANDERING_TRADER_TRADES.forEach((level, offers) ->
                TradeOfferHelper.registerWanderingTraderOffers(level, factory -> {
                    for (MerchantOffer offer : offers) factory.add((trader, random) -> offer);
                })
        );
    }
}
