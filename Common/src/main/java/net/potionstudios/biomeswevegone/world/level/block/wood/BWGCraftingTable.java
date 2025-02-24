package net.potionstudios.biomeswevegone.world.level.block.wood;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

/**
 * Crafting table block
 * @see CraftingTableBlock
 * @author Joseph T. McQuigg
 */
public class BWGCraftingTable extends CraftingTableBlock {
    private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");
    public BWGCraftingTable(MapColor color) {
        super(Properties.ofFullCopy(Blocks.CRAFTING_TABLE).mapColor(color));
    }

    @Override
    public @NotNull MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return new SimpleMenuProvider((i, inventory, player) -> new WorkBenchContainer(i, inventory, ContainerLevelAccess.create(level, pos), this), CONTAINER_TITLE);
    }
}

/**
 * Crafting table container
 * @see CraftingMenu
 * @author Joseph T. McQuigg
 */
class WorkBenchContainer extends CraftingMenu {

    private final Block workbench;
    private final ContainerLevelAccess worldPos;

    public WorkBenchContainer(int id, Inventory playerInv, ContainerLevelAccess worldPos, Block workbench) {
        super(id, playerInv, worldPos);
        this.workbench = workbench;
        this.worldPos = worldPos;
    }

    protected static boolean isWithinUsableDistance(ContainerLevelAccess worldPos, Player playerIn, Block targetBlock) {
        return worldPos.evaluate((world, pos) ->
                world.getBlockState(pos).getBlock() == targetBlock && playerIn.distanceToSqr((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D) <= 64.0D, true);
    }

    @Override
    public boolean stillValid(@NotNull Player playerIn) {
        return isWithinUsableDistance(this.worldPos, playerIn, this.workbench);
    }
}
