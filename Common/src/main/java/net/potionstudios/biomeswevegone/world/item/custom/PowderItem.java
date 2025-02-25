package net.potionstudios.biomeswevegone.world.item.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail.ColorProperty;

public class PowderItem extends Item {
    private final ColorProperty colorProperty;

    public PowderItem(ColorProperty colorProperty) {
        super(new Item.Properties().setId(BiomesWeveGone.key(Registries.ITEM,  colorProperty.getSerializedName() + "_glowcane_powder")));
        this.colorProperty = colorProperty;
    }

    public ColorProperty getColor() {
        return colorProperty;
    }
}
