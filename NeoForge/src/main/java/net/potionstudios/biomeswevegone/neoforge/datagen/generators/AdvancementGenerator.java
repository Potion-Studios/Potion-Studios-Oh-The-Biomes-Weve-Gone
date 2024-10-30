package net.potionstudios.biomeswevegone.neoforge.datagen.generators;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.packs.VanillaAdventureAdvancements;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.potionstudios.biomeswevegone.BiomesWeveGone;
import net.potionstudios.biomeswevegone.tags.BWGBlockTags;
import net.potionstudios.biomeswevegone.tags.BWGItemTags;
import net.potionstudios.biomeswevegone.world.entity.BWGEntities;
import net.potionstudios.biomeswevegone.world.item.BWGItems;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.wood.BWGWood;
import net.potionstudios.biomeswevegone.world.level.levelgen.biome.BWGBiomes;
import net.potionstudios.biomeswevegone.world.level.levelgen.structure.BWGStructures;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

public class AdvancementGenerator implements AdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.@NotNull Provider arg, @NotNull Consumer<AdvancementHolder> consumer, @NotNull ExistingFileHelper existingFileHelper) {
        AdvancementHolder root = Advancement.Builder.advancement()
                .addCriterion("tick", PlayerTrigger.TriggerInstance.tick())
                .display(
                        BWGItems.BWG_LOGO.get(),
                        translateAble("title.root"),
                        translateAble("description.root"),
                        BiomesWeveGone.id("textures/block/lush_dirt.png"),
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/root"), existingFileHelper);

        AdvancementHolder adventureRoot = VanillaAdventureAdvancements.addBiomes(Advancement.Builder.advancement(), arg, BWGBiomes.BIOME_FACTORIES.keySet().stream().sorted().toList())
                .parent(root)
                .requirements(AdvancementRequirements.Strategy.OR)
                .display(
                        BWGItems.BWG_LOGO.get(),
                        translateAble("adventure.root.title"),
                        translateAble("adventure.root.description"),
                        BiomesWeveGone.id("textures/block/lush_dirt.png"),
                        AdvancementType.TASK, false, false, false
                ).save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/adventure/root"), existingFileHelper);

        VanillaAdventureAdvancements.addBiomes(Advancement.Builder.advancement(), arg, BWGBiomes.BIOME_FACTORIES.keySet().stream().sorted().toList())
                .parent(adventureRoot)
                .requirements(AdvancementRequirements.Strategy.AND)
                .display(
                        BWGItems.BWG_LOGO.get(),
                        translateAble("adventure.oh_the_biomes_weve_gone.title"),
                        translateAble("adventure.oh_the_biomes_weve_gone.description"),
                        BiomesWeveGone.id("textures/block/lush_dirt.png"),
                        AdvancementType.CHALLENGE, true, true, false
                ).rewards(AdvancementRewards.Builder.experience(1000))
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/adventure/oh_the_biomes_weve_gone"), existingFileHelper);

        Advancement.Builder.advancement()
                .parent(adventureRoot)
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("inside_quicksand", EnterBlockTrigger.TriggerInstance.entersBlock(BWGBlocks.QUICKSAND.get()))
                .addCriterion("inside_red_quicksand", EnterBlockTrigger.TriggerInstance.entersBlock(BWGBlocks.RED_QUICKSAND.get()))
                .display(
                        BWGBlocks.QUICKSAND.get().asItem().getDefaultInstance(),
                        translateAble("adventure.inside_quicksand.title"),
                        translateAble("adventure.inside_quicksand.description"),
                        BiomesWeveGone.id("textures/block/lush_dirt.png"),
                        AdvancementType.TASK,
                        true, true, false
                )
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/adventure/inside_quicksand"), existingFileHelper);

        Advancement.Builder.advancement()
                .parent(adventureRoot)
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("prairie_house", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(arg.holderOrThrow(BWGStructures.PRAIRIE_HOUSE))))
                .addCriterion("abondoned_prairie_house", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(arg.holderOrThrow(BWGStructures.ABANDONED_PRAIRIE_HOUSE))))
                .display(
                        BWGBlocks.PRAIRIE_GRASS.get().asItem().getDefaultInstance(),
                        translateAble("adventure.little_house_on_the_prairie.title"),
                        translateAble("adventure.little_house_on_the_prairie.description"),
                        BiomesWeveGone.id("textures/block/lush_dirt.png"),
                        AdvancementType.TASK, true, true, false
                )
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/adventure/little_house_on_the_prairie"), existingFileHelper);

        Advancement.Builder.advancement()
                .parent(adventureRoot)
                .addCriterion("pixe_club_music_disc", InventoryChangeTrigger.TriggerInstance.hasItems(BWGItems.MUSIC_DISC_PIXIE_CLUB.get()))
                .display(
                        BWGItems.MUSIC_DISC_PIXIE_CLUB.get(),
                        translateAble("adventure.forgotten_fae.title"),
                        translateAble("adventure.forgotten_fae.description"),
                        BiomesWeveGone.id("textures/block/lush_dirt.png"),
                        AdvancementType.TASK, true, true, false
                )
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/adventure/forgotten_fae"), existingFileHelper);

        AdvancementHolder paleBogRoot = Advancement.Builder.advancement()
                .parent(adventureRoot)
                .addCriterion("pale_bog", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inBiome(arg.holderOrThrow(BWGBiomes.PALE_BOG))))
                .display(
                        BWGItems.SOUL_FRUIT.get(),
                        translateAble("adventure.pale_in_comparison.title"),
                        translateAble("adventure.pale_in_comparison.description"),
                        null,
                        AdvancementType.TASK, true, true, false)
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/adventure/pale_in_comparison"), existingFileHelper);

        Advancement.Builder.advancement()
                .parent(paleBogRoot)
                .addCriterion("bog_trial", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(arg.holderOrThrow(BWGStructures.BOG_TRIAL))))
                .display(
                        BWGWood.SPIRIT_ROOTS.get(),
                        translateAble("adventure.witches_road.title"),
                        translateAble("adventure.witches_road.description"),
                        null,
                        AdvancementType.TASK, true, true, false)
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/adventure/witches_road"), existingFileHelper);

        AdvancementHolder husbandryRoot = Advancement.Builder.advancement()
                .parent(root)
                .addCriterion("consumed_item", ConsumeItemTrigger.TriggerInstance.usedItem())
                .display(
                        BWGItems.GREEN_APPLE.get(),
                        translateAble("husbandry.root.title"),
                        translateAble("husbandry.root.description"),
                        null, AdvancementType.TASK, false, false, false)
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/husbandry/root"), existingFileHelper);

        AdvancementHolder grannySmith = Advancement.Builder.advancement()
                .parent(husbandryRoot)
                .addCriterion("green_apple", InventoryChangeTrigger.TriggerInstance.hasItems(BWGItems.GREEN_APPLE.get()))
                .display(
                        BWGItems.GREEN_APPLE.get(),
                        translateAble("husbandry.granny_smith.title"),
                        translateAble("husbandry.granny_smith.description"),
                        null, AdvancementType.TASK, true, true, false)
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/husbandry/granny_smith"), existingFileHelper);

        Advancement.Builder.advancement()
                .parent(husbandryRoot)
                .addCriterion("white_puffball_cap", InventoryChangeTrigger.TriggerInstance.hasItems(BWGItems.WHITE_PUFFBALL_CAP.get()))
                .display(
                        BWGItems.WHITE_PUFFBALL_CAP.get(),
                        translateAble("husbandry.forager.title"),
                        translateAble("husbandry.forager.description"),
                        null, AdvancementType.TASK, true, true, false)
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/husbandry/forager"), existingFileHelper);

        Advancement.Builder.advancement()
                .parent(husbandryRoot)
                .addCriterion("blue_berry", InventoryChangeTrigger.TriggerInstance.hasItems(BWGItems.BLUEBERRIES.get()))
                .display(
                        BWGItems.BLUEBERRIES.get(),
                        translateAble("husbandry.berrily_alive.title"),
                        translateAble("husbandry.berrily_alive.description"),
                        null, AdvancementType.TASK, true, true, false)
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/husbandry/berrily_alive"), existingFileHelper);

        Advancement.Builder.advancement()
                .parent(husbandryRoot)
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("blueberry_pie", InventoryChangeTrigger.TriggerInstance.hasItems(BWGItems.BLUEBERRY_PIE.get()))
                .addCriterion("green_apple_pie", InventoryChangeTrigger.TriggerInstance.hasItems(BWGItems.GREEN_APPLE_PIE.get()))
                .display(
                        BWGItems.GREEN_APPLE_PIE.get(),
                        translateAble("husbandry.just_like_grandmas.title"),
                        translateAble("husbandry.just_like_grandmas.description"),
                        null, AdvancementType.GOAL, true, true, false)
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/husbandry/just_like_grandmas"), existingFileHelper);

        Advancement.Builder.advancement()
                .parent(grannySmith)
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("green_apple", InventoryChangeTrigger.TriggerInstance.hasItems(BWGItems.GREEN_APPLE.get()))
                .addCriterion("apple", InventoryChangeTrigger.TriggerInstance.hasItems(Items.APPLE))
                .addCriterion("golden_apple", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLDEN_APPLE))
                .display(
                        Items.GOLDEN_APPLE,
                        translateAble("husbandry.johnny_appleseed.title"),
                        translateAble("husbandry.johnny_appleseed.description"),
                        null, AdvancementType.TASK, true, true, false)
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/husbandry/johnny_appleseed"), existingFileHelper);

        Advancement.Builder.advancement()
                .parent(husbandryRoot)
                .requirements(AdvancementRequirements.Strategy.OR)
                .addCriterion("cattail_sprout", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(BlockTags.CAMPFIRES)), ItemPredicate.Builder.item().of(BWGItems.CATTAIL_SPROUT.get())))
                .addCriterion("fluorescent_cattail_sprout", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(BlockTags.CAMPFIRES)), ItemPredicate.Builder.item().of(BWGItems.FLUORESCENT_CATTAIL_SPROUT.get())))
                .display(
                        BWGItems.CATTAIL_SPROUT.get(),
                        translateAble("husbandry.hot_diggity_not_dog.title"),
                        translateAble("husbandry.hot_diggity_not_dog.description"),
                        null, AdvancementType.TASK, true, true, false)
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/husbandry/hot_diggity_not_dog"), existingFileHelper);

        Advancement.Builder.advancement()
                .parent(husbandryRoot)
                .rewards(new AdvancementRewards.Builder().addLootTable(ResourceKey.create(Registries.LOOT_TABLE, BiomesWeveGone.id("blocks/pale_pumpkin"))))
                .addCriterion("forgotten_nostalgia", PlayerInteractTrigger.TriggerInstance.itemUsedOnEntity(ItemPredicate.Builder.item().of(BWGItemTags.ROSES), Optional.of(EntityPredicate.wrap(EntityPredicate.Builder.entity().of(BWGEntities.PUMPKIN_WARDEN.get())))))
                .display(
                        BWGBlocks.ROSE.getBlock().asItem(),
                        translateAble("husbandry.forgotten_nostalgia.title"),
                        translateAble("husbandry.forgotten_nostalgia.description"),
                        null, AdvancementType.CHALLENGE, true, true, false)
                .save(consumer, BiomesWeveGone.id(BiomesWeveGone.MOD_ID + "/husbandry/forgotten_nostalgia"), existingFileHelper);

    }

    private static MutableComponent translateAble(String key) {
        return Component.translatable( "advancements." + BiomesWeveGone.MOD_ID +"." + key);
    }
}
