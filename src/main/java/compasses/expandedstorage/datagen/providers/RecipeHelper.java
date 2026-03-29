package compasses.expandedstorage.datagen.providers;

import compasses.expandedstorage.datagen.content.ModTags;
import compasses.expandedstorage.item.ChestMinecartItem;
import compasses.expandedstorage.misc.Utils;
import compasses.expandedstorage.registry.AllItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import compasses.expandedstorage.ExpandedStorage;

import java.util.function.Consumer;
import java.util.function.Function;

public class RecipeHelper {
    private final Function<Item, ResourceLocation> itemIdGetter;
    private final TagKey<Item> copperIngots, ironNuggets, ironIngots, goldIngots, diamonds, obsidianBlocks, netheriteIngots;
    private final TagKey<Item> glassBlocks, woodenChests, woodenBarrels, redDyes, whiteDyes, bamboo;

    public RecipeHelper(
            Function<Item, ResourceLocation> itemIdGetter,
            TagKey<Item> copperIngots, TagKey<Item> ironNuggets, TagKey<Item> ironIngots, TagKey<Item> goldIngots, TagKey<Item> diamonds, TagKey<Item> obsidianBlocks, TagKey<Item> netheriteIngots,
            TagKey<Item> woodenChests, TagKey<Item> woodenBarrels,
            TagKey<Item> glassBlocks, TagKey<Item> redDyes, TagKey<Item> whiteDyes, TagKey<Item> bamboo
    ) {
        this.itemIdGetter = itemIdGetter;
        this.copperIngots = copperIngots;
        this.ironNuggets = ironNuggets;
        this.ironIngots = ironIngots;
        this.goldIngots = goldIngots;
        this.diamonds = diamonds;
        this.obsidianBlocks = obsidianBlocks;
        this.netheriteIngots = netheriteIngots;
        this.woodenChests = woodenChests;
        this.woodenBarrels = woodenBarrels;
        this.glassBlocks = glassBlocks;
        this.redDyes = redDyes;
        this.whiteDyes = whiteDyes;
        this.bamboo = bamboo;
    }

    public static InventoryChangeTrigger.TriggerInstance has(ItemLike item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }

    public static InventoryChangeTrigger.TriggerInstance has(TagKey<Item> tag) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(
            ItemPredicate.Builder.item().of(tag).build()
        );
    }

    @SuppressWarnings("SpellCheckingInspection")
    private void smithingRecipe(Item output, Item base, TagKey<Item> addition, RecipeCategory category, String criterion, Consumer<FinishedRecipe> exporter) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(base), Ingredient.of(addition), category, output)
                                      .unlocks(criterion, RecipeHelper.has(base))
                                      .save(exporter, itemIdGetter.apply(output));
    }

    private ShapedRecipeBuilder shapedRecipe(ItemLike output, RecipeCategory category, int count, String criterion, TagKey<Item> tag) {
        return ShapedRecipeBuilder.shaped(category, output, count).unlockedBy(criterion, RecipeHelper.has(tag));
    }

    private ShapedRecipeBuilder shapedRecipe(ItemLike output, RecipeCategory category, int count, String criterion, Item item) {
        return ShapedRecipeBuilder.shaped(category, output, count).unlockedBy(criterion, RecipeHelper.has(item));
    }

    public void registerRecipes(Consumer<FinishedRecipe> exporter) {
        shapedRecipe(AllItems.STORAGE_MUTATOR_REGISTRY.get(), RecipeCategory.MISC, 1, "has_chest", ModTags.Items.ES_WOODEN_CHESTS)
                .pattern("  C")
                .pattern(" S ")
                .pattern("S  ")
                .define('C', ModTags.Items.ES_WOODEN_CHESTS)
                .define('S', Items.STICK)
                .save(exporter);
        this.offerConversionKitRecipes(exporter);
        this.offerChestRecipes(exporter);
        this.offerChestMinecartRecipes(exporter);
        this.offerOldChestRecipes(exporter);
        this.offerChestToOldChestRecipes(exporter);
        this.offerOldChestToChestRecipes(exporter);
        this.offerBarrelRecipes(exporter);
        this.offerMiniStorageRecipes(exporter);
    }

    private void offerConversionKitRecipes(Consumer<FinishedRecipe> exporter) {
        shapedRecipe(AllItems.WOOD_TO_COPPER_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_ITEM, ItemTags.PLANKS)
                .pattern("III")
                .pattern("IPI")
                .pattern("III")
                .define('I', copperIngots)
                .define('P', ItemTags.PLANKS)
                .save(exporter);
        shapedRecipe(AllItems.WOOD_TO_IRON_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_ITEM, AllItems.WOOD_TO_COPPER_CONVERSION_KIT_REGISTRY.get())
                .pattern("NNN")
                .pattern("IKI")
                .pattern("NNN")
                .define('N', ironNuggets)
                .define('I', ironIngots)
                .define('K', AllItems.WOOD_TO_COPPER_CONVERSION_KIT_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.WOOD_TO_GOLD_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_KIT, AllItems.WOOD_TO_IRON_CONVERSION_KIT_REGISTRY.get())
                .pattern("GGG")
                .pattern("GKG")
                .pattern("GGG")
                .define('G', goldIngots)
                .define('K', AllItems.WOOD_TO_IRON_CONVERSION_KIT_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.WOOD_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_KIT, AllItems.WOOD_TO_GOLD_CONVERSION_KIT_REGISTRY.get())
                .pattern("GGG")
                .pattern("DKD")
                .pattern("GGG")
                .define('G', glassBlocks)
                .define('D', diamonds)
                .define('K', AllItems.WOOD_TO_GOLD_CONVERSION_KIT_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.WOOD_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_KIT, AllItems.WOOD_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get())
                .pattern("OOO")
                .pattern("OKO")
                .pattern("OOO")
                .define('O', obsidianBlocks)
                .define('K', AllItems.WOOD_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get())
                .save(exporter);
        smithingRecipe(AllItems.WOOD_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get(), AllItems.WOOD_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(), netheriteIngots, RecipeCategory.MISC, Criterions.HAS_PREVIOUS_KIT, exporter);
        shapedRecipe(AllItems.COPPER_TO_IRON_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_ITEM, copperIngots)
                .pattern("NNN")
                .pattern("ICI")
                .pattern("NNN")
                .define('N', ironNuggets)
                .define('I', ironIngots)
                .define('C', copperIngots)
                .save(exporter);
        shapedRecipe(AllItems.COPPER_TO_GOLD_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_ITEM, AllItems.COPPER_TO_IRON_CONVERSION_KIT_REGISTRY.get())
                .pattern("GGG")
                .pattern("GKG")
                .pattern("GGG")
                .define('G', goldIngots)
                .define('K', AllItems.COPPER_TO_IRON_CONVERSION_KIT_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.COPPER_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_KIT, AllItems.COPPER_TO_GOLD_CONVERSION_KIT_REGISTRY.get())
                .pattern("GGG")
                .pattern("DKD")
                .pattern("GGG")
                .define('G', glassBlocks)
                .define('D', diamonds)
                .define('K', AllItems.COPPER_TO_GOLD_CONVERSION_KIT_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.COPPER_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_KIT, AllItems.COPPER_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get())
                .pattern("OOO")
                .pattern("OKO")
                .pattern("OOO")
                .define('O', obsidianBlocks)
                .define('K', AllItems.COPPER_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get())
                .save(exporter);
        smithingRecipe(AllItems.COPPER_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get(), AllItems.COPPER_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(), netheriteIngots, RecipeCategory.MISC, Criterions.HAS_PREVIOUS_KIT, exporter);
        shapedRecipe(AllItems.IRON_TO_GOLD_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_ITEM, ironIngots)
                .pattern("GGG")
                .pattern("GIG")
                .pattern("GGG")
                .define('G', goldIngots)
                .define('I', ironIngots)
                .save(exporter);
        shapedRecipe(AllItems.IRON_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_KIT, AllItems.IRON_TO_GOLD_CONVERSION_KIT_REGISTRY.get())
                .pattern("GGG")
                .pattern("DKD")
                .pattern("GGG")
                .define('G', glassBlocks)
                .define('D', diamonds)
                .define('K', AllItems.IRON_TO_GOLD_CONVERSION_KIT_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.IRON_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_KIT, AllItems.IRON_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get())
                .pattern("OOO")
                .pattern("OKO")
                .pattern("OOO")
                .define('O', obsidianBlocks)
                .define('K', AllItems.IRON_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get())
                .save(exporter);
        smithingRecipe(AllItems.IRON_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get(), AllItems.IRON_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(), netheriteIngots, RecipeCategory.MISC, Criterions.HAS_PREVIOUS_KIT, exporter);
        shapedRecipe(AllItems.GOLD_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_ITEM, goldIngots)
                .pattern("GGG")
                .pattern("DID")
                .pattern("GGG")
                .define('G', glassBlocks)
                .define('D', diamonds)
                .define('I', goldIngots)
                .save(exporter);
        shapedRecipe(AllItems.GOLD_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_KIT, AllItems.GOLD_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get())
                .pattern("OOO")
                .pattern("OKO")
                .pattern("OOO")
                .define('O', obsidianBlocks)
                .define('K', AllItems.GOLD_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get())
                .save(exporter);
        smithingRecipe(AllItems.GOLD_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get(), AllItems.GOLD_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(), netheriteIngots, RecipeCategory.MISC, Criterions.HAS_PREVIOUS_KIT, exporter);
        shapedRecipe(AllItems.DIAMOND_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_ITEM, diamonds)
                .pattern("OOO")
                .pattern("ODO")
                .pattern("OOO")
                .define('O', obsidianBlocks)
                .define('D', diamonds)
                .save(exporter);
        smithingRecipe(AllItems.DIAMOND_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get(), AllItems.DIAMOND_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(), netheriteIngots, RecipeCategory.MISC, Criterions.HAS_PREVIOUS_KIT, exporter);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(obsidianBlocks), Ingredient.of(netheriteIngots), RecipeCategory.MISC, AllItems.OBSIDIAN_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get())
                                      .unlocks(Criterions.HAS_ITEM, RecipeHelper.has(obsidianBlocks))
                                      .save(exporter, itemIdGetter.apply(AllItems.OBSIDIAN_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get()));
    }

    private void offerChestRecipes(Consumer<FinishedRecipe> exporter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.WOOD_CHEST_REGISTRY.get())
                              .requires(Items.CHEST)
                              .group(id(AllItems.WOOD_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_PREVIOUS_BLOCK, RecipeHelper.has(Items.CHEST))
                              .save(exporter);
        shapedRecipe(AllItems.PUMPKIN_CHEST_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, woodenChests)
                .pattern("SSS")
                .pattern("SBS")
                .pattern("SSS")
                .define('S', Items.PUMPKIN_SEEDS)
                .define('B', woodenChests)
                .group(id(AllItems.PUMPKIN_CHEST_REGISTRY.get()))
                .save(exporter);
        shapedRecipe(AllItems.PRESENT_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, woodenChests)
                .pattern(" B ")
                .pattern("RCW")
                .pattern(" S ")
                .define('B', Items.SWEET_BERRIES)
                .define('R', redDyes)
                .define('C', woodenChests)
                .define('W', whiteDyes)
                .define('S', Items.SPRUCE_SAPLING)
                .group(id(AllItems.PRESENT_REGISTRY.get()))
                .save(exporter);
        shapedRecipe(AllItems.BAMBOO_CHEST_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, woodenChests)
                .pattern("BBB")
                .pattern("BCB")
                .pattern("BBB")
                .define('B', bamboo)
                .define('C', woodenChests)
                .group(id(AllItems.BAMBOO_CHEST_REGISTRY.get()))
                .save(exporter);
        shapedRecipe(AllItems.MOSS_CHEST_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, woodenChests)
                .pattern("BBB")
                .pattern("BCB")
                .pattern("BBB")
                .define('B', Blocks.MOSS_BLOCK)
                .define('C', woodenChests)
                .group(id(AllItems.BAMBOO_CHEST_REGISTRY.get()))
                .save(exporter);
        shapedRecipe(AllItems.IRON_CHEST_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, ModTags.Items.ES_WOODEN_CHESTS)
                .pattern("III")
                .pattern("IBI")
                .pattern("III")
                .define('I', ironIngots)
                .define('B', ModTags.Items.ES_WOODEN_CHESTS)
                .group(id(AllItems.IRON_CHEST_REGISTRY.get()))
                .save(exporter);
        shapedRecipe(AllItems.GOLD_CHEST_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, AllItems.IRON_CHEST_REGISTRY.get())
                .pattern("GGG")
                .pattern("GBG")
                .pattern("GGG")
                .define('G', goldIngots)
                .define('B', AllItems.IRON_CHEST_REGISTRY.get())
                .group(id(AllItems.GOLD_CHEST_REGISTRY.get()))
                .save(exporter);
        shapedRecipe(AllItems.DIAMOND_CHEST_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, AllItems.GOLD_CHEST_REGISTRY.get())
                .pattern("GGG")
                .pattern("DBD")
                .pattern("GGG")
                .define('G', glassBlocks)
                .define('D', diamonds)
                .define('B', AllItems.GOLD_CHEST_REGISTRY.get())
                .group(id(AllItems.DIAMOND_CHEST_REGISTRY.get()))
                .save(exporter);
        shapedRecipe(AllItems.OBSIDIAN_CHEST_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, AllItems.DIAMOND_CHEST_REGISTRY.get())
                .pattern("OOO")
                .pattern("OBO")
                .pattern("OOO")
                .define('O', obsidianBlocks)
                .define('B', AllItems.DIAMOND_CHEST_REGISTRY.get())
                .group(id(AllItems.OBSIDIAN_CHEST_REGISTRY.get()))
                .save(exporter);
        smithingRecipe(AllItems.NETHERITE_CHEST_REGISTRY.get(), AllItems.OBSIDIAN_CHEST_REGISTRY.get(), netheriteIngots, RecipeCategory.MISC, Criterions.HAS_PREVIOUS_BLOCK, exporter);
    }

    private void offerChestMinecartRecipes(Consumer<FinishedRecipe> exporter) {
        cartRecipe(AllItems.WOOD_CHEST_REGISTRY.get(), AllItems.WOOD_CHEST_MINECART_REGISTRY.get(), exporter);
        cartRecipe(AllItems.PUMPKIN_CHEST_REGISTRY.get(), AllItems.PUMPKIN_CHEST_MINECART_REGISTRY.get(), exporter);
        cartRecipe(AllItems.PRESENT_REGISTRY.get(), AllItems.PRESENT_MINECART_REGISTRY.get(), exporter);
        cartRecipe(AllItems.BAMBOO_CHEST_REGISTRY.get(), AllItems.BAMBOO_CHEST_MINECART_REGISTRY.get(), exporter);
        cartRecipe(AllItems.IRON_CHEST_REGISTRY.get(), AllItems.IRON_CHEST_MINECART_REGISTRY.get(), exporter);
        cartRecipe(AllItems.GOLD_CHEST_REGISTRY.get(), AllItems.GOLD_CHEST_MINECART_REGISTRY.get(), exporter);
        cartRecipe(AllItems.DIAMOND_CHEST_REGISTRY.get(), AllItems.DIAMOND_CHEST_MINECART_REGISTRY.get(), exporter);
        cartRecipe(AllItems.OBSIDIAN_CHEST_REGISTRY.get(), AllItems.OBSIDIAN_CHEST_MINECART_REGISTRY.get(), exporter);
        cartRecipe(AllItems.NETHERITE_CHEST_REGISTRY.get(), AllItems.NETHERITE_CHEST_MINECART_REGISTRY.get(), exporter);
    }

    private void cartRecipe(BlockItem chest, ChestMinecartItem cart, Consumer<FinishedRecipe> exporter) {
        shapedRecipe(cart, RecipeCategory.MISC, 1, "has_chest", chest)
                .pattern("C")
                .pattern("M")
                .define('C', chest)
                .define('M', Items.MINECART)
                .save(exporter);
    }

    private void offerOldChestRecipes(Consumer<FinishedRecipe> exporter) {
        shapedRecipe(AllItems.OLD_IRON_CHEST_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, AllItems.OLD_WOOD_CHEST_REGISTRY.get())
                .pattern("III")
                .pattern("IBI")
                .pattern("III")
                .define('I', ironIngots)
                .define('B', AllItems.OLD_WOOD_CHEST_REGISTRY.get())
                .group(id(AllItems.OLD_IRON_CHEST_REGISTRY.get()))
                .save(exporter);
        shapedRecipe(AllItems.OLD_GOLD_CHEST_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, AllItems.OLD_IRON_CHEST_REGISTRY.get())
                .pattern("GGG")
                .pattern("GBG")
                .pattern("GGG")
                .define('G', goldIngots)
                .define('B', AllItems.OLD_IRON_CHEST_REGISTRY.get())
                .group(id(AllItems.OLD_GOLD_CHEST_REGISTRY.get()))
                .save(exporter);
        shapedRecipe(AllItems.OLD_DIAMOND_CHEST_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, AllItems.OLD_GOLD_CHEST_REGISTRY.get())
                .pattern("GGG")
                .pattern("DBD")
                .pattern("GGG")
                .define('G', glassBlocks)
                .define('D', diamonds)
                .define('B', AllItems.OLD_GOLD_CHEST_REGISTRY.get())
                .group(id(AllItems.OLD_DIAMOND_CHEST_REGISTRY.get()))
                .save(exporter);
        shapedRecipe(AllItems.OLD_OBSIDIAN_CHEST_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, AllItems.OLD_DIAMOND_CHEST_REGISTRY.get())
                .pattern("OOO")
                .pattern("OBO")
                .pattern("OOO")
                .define('O', obsidianBlocks)
                .define('B', AllItems.OLD_DIAMOND_CHEST_REGISTRY.get())
                .group(id(AllItems.OLD_OBSIDIAN_CHEST_REGISTRY.get()))
                .save(exporter);
        smithingRecipe(AllItems.OLD_NETHERITE_CHEST_REGISTRY.get(), AllItems.OLD_OBSIDIAN_CHEST_REGISTRY.get(), netheriteIngots, RecipeCategory.MISC, Criterions.HAS_PREVIOUS_BLOCK, exporter);
    }

    private void offerChestToOldChestRecipes(Consumer<FinishedRecipe> exporter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.OLD_WOOD_CHEST_REGISTRY.get())
                              .requires(AllItems.WOOD_CHEST_REGISTRY.get())
                              .group(id(AllItems.OLD_WOOD_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_ITEM, RecipeHelper.has(AllItems.WOOD_CHEST_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.id("wood_to_old_wood_chest"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.OLD_IRON_CHEST_REGISTRY.get())
                              .requires(AllItems.IRON_CHEST_REGISTRY.get())
                              .group(id(AllItems.OLD_IRON_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_ITEM, RecipeHelper.has(AllItems.IRON_CHEST_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.id("iron_to_old_iron_chest"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.OLD_GOLD_CHEST_REGISTRY.get())
                              .requires(AllItems.GOLD_CHEST_REGISTRY.get())
                              .group(id(AllItems.OLD_GOLD_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_ITEM, RecipeHelper.has(AllItems.GOLD_CHEST_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.id("gold_to_old_gold_chest"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.OLD_DIAMOND_CHEST_REGISTRY.get())
                              .requires(AllItems.DIAMOND_CHEST_REGISTRY.get())
                              .group(id(AllItems.OLD_DIAMOND_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_ITEM, RecipeHelper.has(AllItems.DIAMOND_CHEST_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.id("diamond_to_old_diamond_chest"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.OLD_OBSIDIAN_CHEST_REGISTRY.get())
                              .requires(AllItems.OBSIDIAN_CHEST_REGISTRY.get())
                              .group(id(AllItems.OLD_OBSIDIAN_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_ITEM, RecipeHelper.has(AllItems.OBSIDIAN_CHEST_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.id("obsidian_to_old_obsidian_chest"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.OLD_NETHERITE_CHEST_REGISTRY.get())
                              .requires(AllItems.NETHERITE_CHEST_REGISTRY.get())
                              .group(id(AllItems.OLD_NETHERITE_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_ITEM, RecipeHelper.has(AllItems.NETHERITE_CHEST_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.id("netherite_to_old_netherite_chest"));
    }

    private void offerOldChestToChestRecipes(Consumer<FinishedRecipe> exporter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.WOOD_CHEST_REGISTRY.get())
                              .requires(AllItems.OLD_WOOD_CHEST_REGISTRY.get())
                              .group(id(AllItems.WOOD_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_ITEM, RecipeHelper.has(AllItems.OLD_WOOD_CHEST_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.id("old_wood_to_wood_chest"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.IRON_CHEST_REGISTRY.get())
                              .requires(AllItems.OLD_IRON_CHEST_REGISTRY.get())
                              .group(id(AllItems.IRON_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_ITEM, RecipeHelper.has(AllItems.OLD_IRON_CHEST_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.id("old_iron_to_iron_chest"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.GOLD_CHEST_REGISTRY.get())
                              .requires(AllItems.OLD_GOLD_CHEST_REGISTRY.get())
                              .group(id(AllItems.GOLD_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_ITEM, RecipeHelper.has(AllItems.OLD_GOLD_CHEST_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.id("old_gold_to_gold_chest"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.DIAMOND_CHEST_REGISTRY.get())
                              .requires(AllItems.OLD_DIAMOND_CHEST_REGISTRY.get())
                              .group(id(AllItems.DIAMOND_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_ITEM, RecipeHelper.has(AllItems.OLD_DIAMOND_CHEST_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.id("old_diamond_to_diamond_chest"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.OBSIDIAN_CHEST_REGISTRY.get())
                              .requires(AllItems.OLD_OBSIDIAN_CHEST_REGISTRY.get())
                              .group(id(AllItems.OBSIDIAN_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_ITEM, RecipeHelper.has(AllItems.OLD_OBSIDIAN_CHEST_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.id("old_obsidian_to_obsidian_chest"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.NETHERITE_CHEST_REGISTRY.get())
                              .requires(AllItems.OLD_NETHERITE_CHEST_REGISTRY.get())
                              .group(id(AllItems.NETHERITE_CHEST_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_ITEM, RecipeHelper.has(AllItems.OLD_NETHERITE_CHEST_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.id("old_netherite_to_netherite_chest"));
    }

    private void offerBarrelRecipes(Consumer<FinishedRecipe> exporter) {
        shapedRecipe(AllItems.COPPER_BARREL_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, woodenBarrels)
                .pattern("III")
                .pattern("IBI")
                .pattern("III")
                .define('I', copperIngots)
                .define('B', woodenBarrels)
                .save(exporter);
        shapedRecipe(AllItems.IRON_BARREL_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, AllItems.COPPER_BARREL_REGISTRY.get())
                .pattern("NNN")
                .pattern("IBI")
                .pattern("NNN")
                .define('N', ironNuggets)
                .define('I', ironIngots)
                .define('B', AllItems.COPPER_BARREL_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.GOLD_BARREL_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, AllItems.IRON_BARREL_REGISTRY.get())
                .pattern("GGG")
                .pattern("GBG")
                .pattern("GGG")
                .define('G', goldIngots)
                .define('B', AllItems.IRON_BARREL_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.DIAMOND_BARREL_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, AllItems.GOLD_BARREL_REGISTRY.get())
                .pattern("GGG")
                .pattern("DBD")
                .pattern("GGG")
                .define('G', glassBlocks)
                .define('D', diamonds)
                .define('B', AllItems.GOLD_BARREL_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.OBSIDIAN_BARREL_REGISTRY.get(), RecipeCategory.MISC, 1, Criterions.HAS_PREVIOUS_BLOCK, AllItems.DIAMOND_BARREL_REGISTRY.get())
                .pattern("OOO")
                .pattern("OBO")
                .pattern("OOO")
                .define('O', obsidianBlocks)
                .define('B', AllItems.DIAMOND_BARREL_REGISTRY.get())
                .save(exporter);
        smithingRecipe(AllItems.NETHERITE_BARREL_REGISTRY.get(), AllItems.OBSIDIAN_BARREL_REGISTRY.get(), netheriteIngots, RecipeCategory.MISC, Criterions.HAS_PREVIOUS_BLOCK, exporter);
    }

    private void offerMiniStorageRecipes(Consumer<FinishedRecipe> exporter) {
        shapedRecipe(AllItems.VANILLA_WOOD_MINI_CHEST_REGISTRY.get(), RecipeCategory.MISC, 4, Criterions.HAS_ITEM, Items.CHEST)
                .pattern(" P ")
                .pattern("PBP")
                .pattern(" P ")
                .define('P', Items.PAPER)
                .define('B', Items.CHEST)
                .save(exporter);
        shapedRecipe(AllItems.WOOD_MINI_CHEST_REGISTRY.get(), RecipeCategory.MISC, 4, Criterions.HAS_ITEM, AllItems.WOOD_CHEST_REGISTRY.get())
                .pattern(" P ")
                .pattern("PBP")
                .pattern(" P ")
                .define('P', Items.PAPER)
                .define('B', AllItems.WOOD_CHEST_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.PUMPKIN_MINI_CHEST_REGISTRY.get(), RecipeCategory.MISC, 4, Criterions.HAS_ITEM, AllItems.PUMPKIN_CHEST_REGISTRY.get())
                .pattern(" P ")
                .pattern("PBP")
                .pattern(" P ")
                .define('P', Items.PAPER)
                .define('B', AllItems.PUMPKIN_CHEST_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.RED_MINI_PRESENT_REGISTRY.get(), RecipeCategory.MISC, 4, Criterions.HAS_ITEM, AllItems.PRESENT_REGISTRY.get())
                .pattern(" P ")
                .pattern("PBP")
                .pattern(" P ")
                .define('P', Items.PAPER)
                .define('B', AllItems.PRESENT_REGISTRY.get())
                .group(id(AllItems.RED_MINI_PRESENT_REGISTRY.get()))
                .save(exporter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.WHITE_MINI_PRESENT_REGISTRY.get())
                              .requires(AllItems.RED_MINI_PRESENT_REGISTRY.get())
                              .unlockedBy(Criterions.HAS_PREVIOUS_BLOCK, RecipeHelper.has(AllItems.RED_MINI_PRESENT_REGISTRY.get()))
                              .save(exporter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.CANDY_CANE_MINI_PRESENT_REGISTRY.get())
                              .requires(AllItems.WHITE_MINI_PRESENT_REGISTRY.get())
                              .unlockedBy(Criterions.HAS_PREVIOUS_BLOCK, RecipeHelper.has(AllItems.WHITE_MINI_PRESENT_REGISTRY.get()))
                              .save(exporter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.GREEN_MINI_PRESENT_REGISTRY.get())
                              .requires(AllItems.CANDY_CANE_MINI_PRESENT_REGISTRY.get())
                              .unlockedBy(Criterions.HAS_PREVIOUS_BLOCK, RecipeHelper.has(AllItems.CANDY_CANE_MINI_PRESENT_REGISTRY.get()))
                              .save(exporter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.RED_MINI_PRESENT_REGISTRY.get())
                              .requires(AllItems.GREEN_MINI_PRESENT_REGISTRY.get())
                              .group(id(AllItems.RED_MINI_PRESENT_REGISTRY.get()))
                              .unlockedBy(Criterions.HAS_PREVIOUS_BLOCK, RecipeHelper.has(AllItems.GREEN_MINI_PRESENT_REGISTRY.get()))
                              .save(exporter, ExpandedStorage.MOD_ID + ":red_mini_present_cycle");
        shapedRecipe(AllItems.IRON_MINI_CHEST_REGISTRY.get(), RecipeCategory.MISC, 8, Criterions.HAS_ITEM, AllItems.WOOD_CHEST_REGISTRY.get())
                .pattern(" I ")
                .pattern("PBP")
                .pattern(" P ")
                .define('I', Items.IRON_INGOT)
                .define('P', Items.PAPER)
                .define('B', AllItems.WOOD_CHEST_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.GOLD_MINI_CHEST_REGISTRY.get(), RecipeCategory.MISC, 8, Criterions.HAS_ITEM, AllItems.WOOD_CHEST_REGISTRY.get())
                .pattern(" I ")
                .pattern("PBP")
                .pattern(" P ")
                .define('I', Items.GOLD_INGOT)
                .define('P', Items.PAPER)
                .define('B', AllItems.WOOD_CHEST_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.DIAMOND_MINI_CHEST_REGISTRY.get(), RecipeCategory.MISC, 8, Criterions.HAS_ITEM, AllItems.WOOD_CHEST_REGISTRY.get())
                .pattern(" I ")
                .pattern("PBP")
                .pattern(" P ")
                .define('I', Items.DIAMOND)
                .define('P', Items.PAPER)
                .define('B', AllItems.WOOD_CHEST_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.OBSIDIAN_MINI_CHEST_REGISTRY.get(), RecipeCategory.MISC, 8, Criterions.HAS_ITEM, AllItems.WOOD_CHEST_REGISTRY.get())
                .pattern(" I ")
                .pattern("PBP")
                .pattern(" P ")
                .define('I', Items.OBSIDIAN)
                .define('P', Items.PAPER)
                .define('B', AllItems.WOOD_CHEST_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.NETHERITE_MINI_CHEST_REGISTRY.get(), RecipeCategory.MISC, 8, Criterions.HAS_ITEM, AllItems.WOOD_CHEST_REGISTRY.get())
                .pattern(" I ")
                .pattern("PBP")
                .pattern(" P ")
                .define('I', Items.NETHERITE_INGOT)
                .define('P', Items.PAPER)
                .define('B', AllItems.WOOD_CHEST_REGISTRY.get())
                .save(exporter);
        shapedRecipe(AllItems.MINI_BARREL_REGISTRY.get(), RecipeCategory.MISC, 4, Criterions.HAS_ITEM, woodenBarrels)
                .pattern(" P ")
                .pattern("PBP")
                .pattern(" P ")
                .define('P', Items.PAPER)
                .define('B', woodenBarrels)
                .save(exporter);
        shapedRecipe(AllItems.COPPER_MINI_BARREL_REGISTRY.get(), RecipeCategory.MISC, 8, Criterions.HAS_ITEM, woodenBarrels)
                .pattern(" I ")
                .pattern("PBP")
                .pattern(" P ")
                .define('I', Items.COPPER_INGOT)
                .define('P', Items.PAPER)
                .define('B', woodenBarrels)
                .save(exporter);
        shapedRecipe(AllItems.IRON_MINI_BARREL_REGISTRY.get(), RecipeCategory.MISC, 8, Criterions.HAS_ITEM, woodenBarrels)
                .pattern(" I ")
                .pattern("PBP")
                .pattern(" P ")
                .define('I', Items.IRON_INGOT)
                .define('P', Items.PAPER)
                .define('B', woodenBarrels)
                .save(exporter);
        shapedRecipe(AllItems.GOLD_MINI_BARREL_REGISTRY.get(), RecipeCategory.MISC, 8, Criterions.HAS_ITEM, woodenBarrels)
                .pattern(" I ")
                .pattern("PBP")
                .pattern(" P ")
                .define('I', Items.GOLD_INGOT)
                .define('P', Items.PAPER)
                .define('B', woodenBarrels)
                .save(exporter);
        shapedRecipe(AllItems.DIAMOND_MINI_BARREL_REGISTRY.get(), RecipeCategory.MISC, 8, Criterions.HAS_ITEM, woodenBarrels)
                .pattern(" I ")
                .pattern("PBP")
                .pattern(" P ")
                .define('I', Items.DIAMOND)
                .define('P', Items.PAPER)
                .define('B', woodenBarrels)
                .save(exporter);
        shapedRecipe(AllItems.OBSIDIAN_MINI_BARREL_REGISTRY.get(), RecipeCategory.MISC, 8, Criterions.HAS_ITEM, woodenBarrels)
                .pattern(" I ")
                .pattern("PBP")
                .pattern(" P ")
                .define('I', Items.OBSIDIAN)
                .define('P', Items.PAPER)
                .define('B', woodenBarrels)
                .save(exporter);
        shapedRecipe(AllItems.NETHERITE_MINI_BARREL_REGISTRY.get(), RecipeCategory.MISC, 8, Criterions.HAS_ITEM, woodenBarrels)
                .pattern(" I ")
                .pattern("PBP")
                .pattern(" P ")
                .define('I', Items.NETHERITE_INGOT)
                .define('P', Items.PAPER)
                .define('B', woodenBarrels)
                .save(exporter);
    }

    private String id(ItemLike like) {
        return itemIdGetter.apply(like.asItem()).toString();
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static class Criterions {
        public static final String HAS_ITEM = "has_item";
        private static final String HAS_PREVIOUS_KIT = "has_previous_kit";
        private static final String HAS_PREVIOUS_BLOCK = "has_previous_block";
    }
}

