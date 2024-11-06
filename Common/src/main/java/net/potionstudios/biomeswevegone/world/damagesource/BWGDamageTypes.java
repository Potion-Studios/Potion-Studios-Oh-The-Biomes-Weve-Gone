package net.potionstudios.biomeswevegone.world.damagesource;

import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.potionstudios.biomeswevegone.BiomesWeveGone;

import java.util.Map;

public interface BWGDamageTypes {

    Map<ResourceKey<DamageType>, DamageTypeFactory> DAMAGE_TYPE_FACTORIES = new Reference2ObjectOpenHashMap<>();

    ResourceKey<DamageType> IN_QUICKSAND = register("in_quicksand", (damageTypeBootstapContext) -> new DamageType(BiomesWeveGone.MOD_ID + ".inQuicksand", DamageScaling.ALWAYS, 0.3F, DamageEffects.DROWNING));
    ResourceKey<DamageType> CATTAIL_EXPLOSION = register("cattail_explosion", (damageTypeBootstapContext -> new DamageType(BiomesWeveGone.MOD_ID + ".cattailExplosion", DamageScaling.ALWAYS, 0.1F)));

    private static ResourceKey<DamageType> register(String id, DamageTypeFactory factory) {
        ResourceKey<DamageType> key = BiomesWeveGone.key(Registries.DAMAGE_TYPE, id);
        DAMAGE_TYPE_FACTORIES.put(key, factory);
        return key;
    }

    @FunctionalInterface
    interface DamageTypeFactory {
        DamageType generate(BootstrapContext<DamageType> damageTypeBootstapContext);
    }
}
