package net.potionstudios.biomeswevegone.world.entity.npc;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.potionstudios.biomeswevegone.config.configs.BWGTradesConfig;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BWGVillagerTrades {

    public static final Map<VillagerProfession, List<Pair<Integer, MerchantOffer>>> TRADES = new HashMap<>();

    public static void makeTrades() {
        TRADES.put(BWGVillagerProfessions.FORAGER.get(), List.of(
            Pair.of(1, createEmeraldForItemsOffer(Items.RED_MUSHROOM, 10, 12, 2)),
            Pair.of(1, createEmeraldForItemsOffer(Items.BROWN_MUSHROOM, 10, 12, 2)),
            Pair.of(1, createEmeraldForItemsOffer(BWGBlocks.GREEN_MUSHROOM.get(), 10, 12, 2)),
            Pair.of(2, createEmeraldForItemsOffer(BWGBlocks.WOOD_BLEWIT.get(), 8, 12, 3)),
            Pair.of(2, createItemsForEmeraldsOffer(BWGItems.WHITE_PUFFBALL_CAP.get(), 4, 5, 4, 2, 0.05f)),
            Pair.of(3, createEmeraldForItemsOffer(BWGItems.WHITE_PUFFBALL_SPORES.get(), 4, 5, 4)),
            Pair.of(4, createItemsForEmeraldsOffer(BWGBlocks.WITCH_HAZEL_BRANCH.get(),4, 9, 4, 3, 0.05f)),
            Pair.of(4, createItemsForEmeraldsOffer(BWGBlocks.WITCH_HAZEL_BLOSSOM.get(), 10, 1, 10, 3, 0.05f)),
            Pair.of(5, createItemsForEmeraldsOffer(BWGBlocks.SHELF_FUNGI.get(), 3, 9, 4, 4, 0.05f)),
            Pair.of(5, createEmeraldForItemsOffer(Items.SWEET_BERRIES, 16, 4, 2)),
            Pair.of(5, createEmeraldForItemsOffer(BWGItems.BLUEBERRIES.get(), 16, 4, 2))
        ));
        if (!BWGTradesConfig.INSTANCE.get().enableVanillaTradeAdditions()) return;
        TRADES.put(VillagerProfession.BUTCHER, List.of(
           Pair.of(2, createEmeraldForItemsOffer(BWGItems.BLUEBERRIES.get(), 10, 12, 2))
        ));
        TRADES.put(VillagerProfession.FARMER, List.of(
           Pair.of(1, createEmeraldForItemsOffer(BWGItems.CATTAIL_SPROUT.get(), 24, 12, 2)),
           Pair.of(2, createEmeraldForItemsOffer(BWGItems.BAOBAB_FRUIT.get(), 10, 12, 2)),
           Pair.of(2, createEmeraldForItemsOffer(BWGItems.GREEN_APPLE.get(), 24, 12, 2)),
           Pair.of(2, createEmeraldForItemsOffer(BWGBlocks.ALOE_VERA.get(), 16, 12, 2)),
           Pair.of(3, createEmeraldForItemsOffer(BWGItems.YUCCA_FRUIT.get(), 10, 12, 2))
        ));
        TRADES.put(VillagerProfession.MASON, List.of(
           Pair.of(3, createEmeraldForItemsOffer(BWGBlocks.ROCKY_STONE_SET.getBase(), 12, 12, 20)),
           Pair.of(3, createItemsForEmeraldsOffer(BWGBlocks.ROCKY_STONE_SET.getBase(), 1, 1, 12, 10, 0.05f)),
           Pair.of(3, createEmeraldForItemsOffer(BWGBlocks.MOSSY_STONE_SET.getBase(), 12, 12, 20)),
           Pair.of(3, createItemsForEmeraldsOffer(BWGBlocks.MOSSY_STONE_SET.getBase(), 1, 1, 12, 10, 0.05f)),
           Pair.of(4, createEmeraldForItemsOffer(BWGBlocks.DACITE_SET.getBase(), 12, 12, 30)),
           Pair.of(4, createItemsForEmeraldsOffer(BWGBlocks.DACITE_SET.getBase(), 1, 1, 12, 15, 0.05f)),
           Pair.of(4, createEmeraldForItemsOffer(BWGBlocks.RED_ROCK_SET.getBase(), 12, 12, 30)),
           Pair.of(4, createItemsForEmeraldsOffer(BWGBlocks.RED_ROCK_SET.getBase(), 1, 1, 12, 15, 0.05f))
        ));
    }

    private static MerchantOffer createEmeraldForItemsOffer(ItemLike item, int cost, int maxUses, int villagerXp) {
        return new MerchantOffer(new ItemCost(item, cost), new ItemStack(Items.EMERALD), maxUses, villagerXp, 0.05F);
    }

    private static MerchantOffer createItemsForEmeraldsOffer(ItemLike item, int emeraldCost, int numberOfItems, int maxUses, int villagerXp, float priceMultiplier) {
        return new MerchantOffer(new ItemCost(Items.EMERALD, emeraldCost), new ItemStack(item, numberOfItems), maxUses, villagerXp, priceMultiplier);
    }
}
