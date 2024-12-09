package net.potionstudios.biomeswevegone.world.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.PlatformHandler;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * The creative tab for Oh The Biomes We've Gone.
 * @see Registries#CREATIVE_MODE_TAB
 * @author Joseph T. McQuigg
 */
public class BWGCreativeTabs {
    public static final ResourceKey<CreativeModeTab> CREATIVE_TAB = createCreativeTab("biomes_weve_gone", () -> BWGItems.BWG_LOGO.get().getDefaultInstance(), BWGBlocks.BLOCK_ITEMS, BWGItems.ITEMS, BWGItems.NO_LANG_ITEMS);
    public static final ResourceKey<CreativeModeTab> WOOD_TAB = createCreativeTab("biomes_weve_gone_wood", () -> BWGWood.ASPEN.logstem().asItem().getDefaultInstance(), BWGWood.WOOD_BLOCK_ITEMS);

    @SafeVarargs
    private static ResourceKey<CreativeModeTab> createCreativeTab(String name, Supplier<ItemStack> icon, ArrayList<Supplier<? extends Item>>... items) {
        PlatformHandler.PLATFORM_HANDLER.createCreativeTab(name, icon, items);
        return BiomesWeveGone.key(Registries.CREATIVE_MODE_TAB, name);
    }

    public static void tabs() {
        BiomesWeveGone.LOGGER.info("Registering Oh The Biomes We've Gone Creative Tabs");
    }
}
