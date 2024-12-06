package net.potionstudios.biomeswevegone.forge.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
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
            -> RecordCodecBuilder.mapCodec(inst -> codecStart(inst).and(ForgeRegistries.ITEMS.getCodec().listOf()
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
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> objectArrayList, LootContext arg) {
        for(LootItemCondition condition : this.conditions)
            if(!condition.test(arg)) return objectArrayList;

        items.forEach(item -> objectArrayList.add(new ItemStack(item)));
        return objectArrayList;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
