package net.potionstudios.biomeswevegone.world.entity.npc;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.potionstudios.biomeswevegone.config.configs.BWGTradesConfig;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BWGVillagerTrades {

    public static final Map<VillagerProfession, Int2ObjectMap<List<MerchantOffer>>> TRADES = new HashMap<>();

    public static void makeTrades() {
        TRADES.put(BWGVillagerProfessions.FORAGER.get(), toIntMap(ImmutableMap.of(
                1, ImmutableList.of(
                        createEmeraldForItemsOffer(Items.RED_MUSHROOM, 10, 12, 2),
                        createEmeraldForItemsOffer(Items.BROWN_MUSHROOM, 10, 12, 2),
                        createEmeraldForItemsOffer(BWGBlocks.GREEN_MUSHROOM.get(), 10, 12, 2)
                ),
                2, ImmutableList.of(
                        createEmeraldForItemsOffer(BWGBlocks.WOOD_BLEWIT.get(), 8, 12, 3),
                        createItemsForEmeraldsOffer(BWGItems.WHITE_PUFFBALL_CAP.get(), 4, 5, 4, 2, 0.05f)
                ),
                3, ImmutableList.of(
                        createEmeraldForItemsOffer(BWGItems.WHITE_PUFFBALL_SPORES.get(), 4, 5, 4)
                ),
                4, ImmutableList.of(
                        createItemsForEmeraldsOffer(BWGBlocks.WITCH_HAZEL_BRANCH.get(), 4, 9, 4, 3, 0.05f),
                        createItemsForEmeraldsOffer(BWGBlocks.WITCH_HAZEL_BLOSSOM.get(), 10, 1, 10, 3, 0.05f)
                ),
                5, ImmutableList.of(
                        createItemsForEmeraldsOffer(BWGBlocks.SHELF_FUNGI.get(), 3, 9, 4, 4, 0.05f),
                        createEmeraldForItemsOffer(Items.SWEET_BERRIES, 16, 4, 2),
                        createEmeraldForItemsOffer(BWGItems.BLUEBERRIES.get(), 16, 4, 2)
                )
        )));
        if (!BWGTradesConfig.INSTANCE.get().enableVanillaTradeAdditions()) return;
        TRADES.put(VillagerProfession.BUTCHER, toIntMap(ImmutableMap.of(
                2, ImmutableList.of(
                        createEmeraldForItemsOffer(BWGItems.BLUEBERRIES.get(), 10, 12, 2)
                )
        )));
        TRADES.put(VillagerProfession.FARMER, toIntMap(ImmutableMap.of(
                1, ImmutableList.of(
                        createEmeraldForItemsOffer(BWGItems.CATTAIL_SPROUT.get(), 24, 12, 2)
                ),
                2, ImmutableList.of(
                        createEmeraldForItemsOffer(BWGItems.BAOBAB_FRUIT.get(), 10, 12, 2),
                        createEmeraldForItemsOffer(BWGItems.GREEN_APPLE.get(), 24, 12, 2),
                        createEmeraldForItemsOffer(BWGBlocks.ALOE_VERA.get(), 16, 12, 2)
                ),
                3, ImmutableList.of(
                        createEmeraldForItemsOffer(BWGItems.YUCCA_FRUIT.get(), 10, 12, 2)
                )
        )));
        TRADES.put(VillagerProfession.MASON, toIntMap(ImmutableMap.of(
                3, ImmutableList.of(
                        createEmeraldForItemsOffer(BWGBlocks.ROCKY_STONE_SET.getBase(), 12, 12, 20),
                        createItemsForEmeraldsOffer(BWGBlocks.ROCKY_STONE_SET.getBase(), 1, 1, 12, 10, 0.05f),
                        createEmeraldForItemsOffer(BWGBlocks.MOSSY_STONE_SET.getBase(), 12, 12, 20),
                        createItemsForEmeraldsOffer(BWGBlocks.MOSSY_STONE_SET.getBase(), 1, 1, 12, 10, 0.05f)
                ),
                4, ImmutableList.of(
                        createEmeraldForItemsOffer(BWGBlocks.DACITE_SET.getBase(), 12, 12, 30),
                        createItemsForEmeraldsOffer(BWGBlocks.DACITE_SET.getBase(), 1, 1, 12, 15, 0.05f),
                        createEmeraldForItemsOffer(BWGBlocks.RED_ROCK_SET.getBase(), 12, 12, 30),
                        createItemsForEmeraldsOffer(BWGBlocks.RED_ROCK_SET.getBase(), 1, 1, 12, 15, 0.05f)
                )
        )));
    }

    private static MerchantOffer createEmeraldForItemsOffer(ItemLike item, int cost, int maxUses, int villagerXp) {
        return new MerchantOffer(new ItemStack(item, cost), new ItemStack(Items.EMERALD), maxUses, villagerXp, 0.05F);
    }

    private static MerchantOffer createItemsForEmeraldsOffer(ItemLike item, int emeraldCost, int numberOfItems, int maxUses, int villagerXp, float priceMultiplier) {
        return new MerchantOffer(new ItemStack(Items.EMERALD, emeraldCost), new ItemStack(item, numberOfItems), maxUses, villagerXp, priceMultiplier);
    }

    private static Int2ObjectMap<List<MerchantOffer>> toIntMap(ImmutableMap<Integer, List<MerchantOffer>> map) {
        return new Int2ObjectOpenHashMap<>(map);
    }
}
