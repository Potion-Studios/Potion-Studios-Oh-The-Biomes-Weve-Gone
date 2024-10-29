package net.potionstudios.biomeswevegone.world.item.brewing;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import org.apache.logging.log4j.util.TriConsumer;

public class BWGBrewingRecipes {

    public static void buildBrewingRecipes(TriConsumer<Holder<Potion>, Item, Holder<Potion>> consumer) {
        consumer.accept(Potions.AWKWARD, BWGItems.SOUL_FRUIT.get(), Potions.INVISIBILITY);
    }

}
