package net.potionstudios.biomeswevegone.fabric;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.potionstudios.biomeswevegone.config.configs.BWGTradesConfig;
import net.potionstudios.biomeswevegone.world.entity.BWGEntities;
import net.potionstudios.biomeswevegone.world.entity.npc.BWGVillagerTrades;
import net.potionstudios.biomeswevegone.world.entity.pumpkinwarden.PumpkinWarden;
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
        registerVillagerInteraction();
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
            if (key.equals(BuiltInLootTables.SNIFFER_DIGGING))
                tableBuilder.withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .with(LootItem.lootTableItem(BWGItems.FLUORESCENT_CATTAIL_SPROUT.get()).build())
                        .with(LootItem.lootTableItem(BWGItems.BLUE_GLOWCANE_SHOOT.get()).build())
                        .with(LootItem.lootTableItem(BWGItems.GREEN_GLOWCANE_SHOOT.get()).build())
                        .with(LootItem.lootTableItem(BWGItems.RED_GLOWCANE_SHOOT.get()).build())
                        .with(LootItem.lootTableItem(BWGItems.YELLOW_GLOWCANE_SHOOT.get()).build())
                        .with(LootItem.lootTableItem(BWGItems.PALE_PUMPKIN_SEEDS.get()).build()));
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

    private static void registerVillagerInteraction() {
        UseEntityCallback.EVENT.register(((player, level, interactionHand, entity, entityHitResult) -> {
            if (entity instanceof Villager villager && villager.isBaby() && villager.hasEffect(MobEffects.WEAKNESS)) {
                ItemStack stack = player.getItemInHand(interactionHand);
                if (stack.is(Items.CARVED_PUMPKIN) || stack.is(BWGBlocks.CARVED_PALE_PUMPKIN.get().asItem())) {
                    if (level instanceof ServerLevel serverLevel) {
                        PumpkinWarden warden = BWGEntities.PUMPKIN_WARDEN.get().create(serverLevel);
                        warden.setPos(villager.position());
                        if (stack.is(BWGBlocks.CARVED_PALE_PUMPKIN.get().asItem()))
                            warden.setVariant(PumpkinWarden.Variant.PALE);
                        serverLevel.addFreshEntity(warden);
                        serverLevel.playSound(null, villager.blockPosition(), SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.NEUTRAL, 1, 1);
                        villager.remove(Entity.RemovalReason.DISCARDED);
                        stack.shrink(1);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
            return InteractionResult.PASS;
        }));
    }
}
