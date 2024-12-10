package net.potionstudios.biomeswevegone.fabric;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.*;
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
        registerTrades();
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> BWGBrewingRecipes.buildBrewingRecipes(builder::addMix));
    }

    private static void registerFuels() {
        FuelRegistry.INSTANCE.add(BWGBlocks.PEAT.get(), 1200);
    }

    private static void registerBiomeModifiers() {
        if (BWGWorldGenConfig.INSTANCE.get().vanillaAdditions()) {
            BWGBiomeModifiers.init();
            BWGBiomeModifiers.BIOME_MODIFIERS_FACTORIES.forEach((id, modifier) -> BiomeModifications.addFeature(BiomeSelectors.includeByKey(modifier.biomes()), modifier.step(), modifier.feature()));
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
        if (!BWGTradesConfig.INSTANCE.get().enableTrades()) return;
        BWGVillagerTrades.makeTrades();
        BWGVillagerTrades.TRADES.forEach(((villagerProfession, pairs) -> pairs.forEach(pair ->
                TradeOfferHelper.registerVillagerOffers(villagerProfession, pair.getFirst(), factory -> factory.add(((trader, random) -> pair.getSecond()))))));
    }
}
