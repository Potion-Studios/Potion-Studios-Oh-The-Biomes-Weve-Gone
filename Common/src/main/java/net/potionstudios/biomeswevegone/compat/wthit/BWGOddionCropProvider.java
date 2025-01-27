package net.potionstudios.biomeswevegone.compat.wthit;

import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.potionstudios.biomeswevegone.world.level.block.plants.bush.OddionCrop;

enum BWGOddionCropProvider implements IBlockComponentProvider {

    INSTANCE;

    private static void addHatchingTooltip(ITooltip tooltip, int timer) {
        tooltip.addLine(Component.translatable("tooltip.waila.hatch_chance", (timer * 10) + "%"));
    }

    @Override
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        if (config.getBoolean(ResourceLocation.withDefaultNamespace("plant.crop_progress")))
            if (accessor.getBlockState().getValue(OddionCrop.HATCHING))
                addHatchingTooltip(tooltip, accessor.getBlockState().getValue(OddionCrop.TIMER));
    }
}
