package net.potionstudios.biomeswevegone.world.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.potionstudios.biomeswevegone.world.damagesource.BWGDamageTypes;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class CampfireExplodingBlockItem extends BlockItem {
    public CampfireExplodingBlockItem(Supplier<? extends Block> block, Properties properties) {
        super(block.get(), properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide()) {
            BlockPos clickedPos = context.getClickedPos();
            if (level.getBlockEntity(clickedPos) instanceof CampfireBlockEntity campfireBlockEntity)
                if (campfireBlockEntity.getBlockState().getValue(CampfireBlock.LIT) && campfireBlockEntity.getItems().stream().anyMatch(ItemStack::isEmpty)) {
                    context.getItemInHand().shrink(1);
                    level.explode(null, new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(BWGDamageTypes.CATTAIL_EXPLOSION)), null, clickedPos.getX() + 0.5D, clickedPos.getY() + 0.5D, clickedPos.getZ() + 0.5D, 5.0F, false, Level.ExplosionInteraction.NONE);
                    return InteractionResult.SUCCESS;
                }
        }
        return super.useOn(context);
    }
}
