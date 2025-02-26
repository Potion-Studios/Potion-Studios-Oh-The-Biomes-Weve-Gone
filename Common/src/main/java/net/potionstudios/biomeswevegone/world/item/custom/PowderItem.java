package net.potionstudios.biomeswevegone.world.item.custom;

import net.minecraft.world.item.Item;
import net.potionstudios.biomeswevegone.world.level.block.plants.vegetation.cattail.ColorProperty;

public class PowderItem extends Item {
    private final ColorProperty colorProperty;

    public PowderItem(Item.Properties properties, ColorProperty colorProperty) {
        super(properties);
        this.colorProperty = colorProperty;
    }

    public ColorProperty getColor() {
        return colorProperty;
    }
}
