package net.potionstudios.biomeswevegone.neoforge.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * A loot modifier that adds items to an existing loot table.
 * @see LootModifier
 * @author Joseph T. McQuigg
 */
public class AddItemModifier extends LootModifier {

    public static final Supplier<MapCodec<AddItemModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.mapCodec(inst -> codecStart(inst).and(BuiltInRegistries.ITEM.byNameCodec().listOf()
            .fieldOf("items").forGetter(m -> m.items))
            .apply(inst, AddItemModifier::new)));

    private final List<Item> items;

    public AddItemModifier(LootItemCondition[] conditionsIn, List<Item> items) {
        super(conditionsIn);
        this.items = items;
    }

    public AddItemModifier(LootItemCondition[] conditionsIn, Item... items) {
        this(conditionsIn, Arrays.asList(items));
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> objectArrayList, @NotNull LootContext arg) {
        for(LootItemCondition condition : this.conditions)
            if(!condition.test(arg)) return objectArrayList;

        items.forEach(item -> objectArrayList.add(new ItemStack(item)));
        return objectArrayList;
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
