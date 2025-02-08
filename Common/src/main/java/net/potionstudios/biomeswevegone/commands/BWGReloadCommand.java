package net.potionstudios.biomeswevegone.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.config.ConfigLoader;
import net.potionstudios.biomeswevegone.config.configs.BWGMiscConfig;
import net.potionstudios.biomeswevegone.config.configs.BWGMobSpawnConfig;

import java.util.function.Consumer;

public class BWGReloadCommand {

	public static void register(Consumer<LiteralArgumentBuilder<CommandSourceStack>> dispatcher) {
		LiteralArgumentBuilder<CommandSourceStack> root = LiteralArgumentBuilder.literal(BiomesWeveGone.MOD_ID);

		LiteralArgumentBuilder<CommandSourceStack> reload = LiteralArgumentBuilder.literal("reload");

		LiteralArgumentBuilder<CommandSourceStack> reloadMisc = LiteralArgumentBuilder.literal("misc");
		reloadMisc.requires(commandSourceStack -> commandSourceStack.hasPermission(2));
		reloadMisc.executes(context -> {
			BWGMiscConfig.INSTANCE = ConfigLoader.loadConfig(BWGMiscConfig.class, "misc");
			context.getSource().sendSuccess(() -> Component.translatable("biomeswevegone.commands.reload.misc.success").withStyle(ChatFormatting.GREEN), true);
			return 1;
		});

		LiteralArgumentBuilder<CommandSourceStack> reloadSpawn = LiteralArgumentBuilder.literal("spawn");
		reloadSpawn.requires(commandSourceStack -> commandSourceStack.hasPermission(2));
		reloadSpawn.executes(context -> {
			BWGMobSpawnConfig.INSTANCE = ConfigLoader.loadConfig(BWGMobSpawnConfig.class, "spawn").spawn;
			context.getSource().sendSuccess(() -> Component.translatable("biomeswevegone.commands.reload.spawn.success").withStyle(ChatFormatting.GREEN), true);
			return 1;
		});

		reload.then(reloadMisc).then(reloadSpawn);
		dispatcher.accept(root.then(reload));
	}

}
