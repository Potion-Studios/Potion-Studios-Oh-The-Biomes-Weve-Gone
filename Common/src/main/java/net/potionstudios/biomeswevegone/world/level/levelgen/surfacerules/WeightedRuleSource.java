package net.potionstudios.biomeswevegone.world.level.levelgen.surfacerules;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record WeightedRuleSource(SimpleWeightedRandomList<SurfaceRules.RuleSource> ruleSources) implements SurfaceRules.RuleSource {

	public static final KeyDispatchDataCodec<WeightedRuleSource> CODEC = KeyDispatchDataCodec.of(RecordCodecBuilder.mapCodec(builder ->
			builder.group(
					SimpleWeightedRandomList.wrappedCodec(SurfaceRules.RuleSource.CODEC).fieldOf("provider").forGetter(WeightedRuleSource::ruleSources)
			).apply(builder, WeightedRuleSource::new)));

	@Override
	public @NotNull KeyDispatchDataCodec<? extends SurfaceRules.RuleSource> codec() {
		return CODEC;
	}

	@Override
	public SurfaceRules.SurfaceRule apply(SurfaceRules.Context context) {
		PositionalRandomFactory random = context.system.noiseRandom;
		SurfaceRules.SurfaceRule[][] rules = new SurfaceRules.SurfaceRule[16][16];

		for (int x = 0; x < rules.length; x++)
			for (int z = 0; z < rules[x].length; z++) {
				SurfaceRules.SurfaceRule apply = this.ruleSources.getRandomValue(random.at(x, 0, z)).get().apply(context);
				rules[x][z] = apply;
			}

		return (x, y, z) -> rules[x & 15][z & 15].tryApply(x, y, z);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WeightedRuleSource that = (WeightedRuleSource) o;
		return Objects.equals(ruleSources, that.ruleSources);
	}

}
