package net.potionstudios.biomeswevegone.fabric.mixin;

import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderMan.class)
public abstract class EnderManMixin {

    @Inject(method = "isLookingAtMe", at = @At("RETURN"), cancellable = true)
    private void isLookingAtMe(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (player.getInventory().getArmor(3).is(BWGBlocks.CARVED_PALE_PUMPKIN.get().asItem()))
            cir.setReturnValue(false);
    }
}
