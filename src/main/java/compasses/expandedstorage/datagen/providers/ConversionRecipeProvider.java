package compasses.expandedstorage.datagen.providers;

import com.google.gson.JsonElement;
import compasses.expandedstorage.block.MiniStorageBlock;
import compasses.expandedstorage.datagen.content.ModEntityTypes;
import compasses.expandedstorage.datagen.content.ModTags;
import compasses.expandedstorage.misc.Utils;
import compasses.expandedstorage.recipe.BlockConversionRecipe;
import compasses.expandedstorage.recipe.EntityConversionRecipe;
import compasses.expandedstorage.recipe.conditions.AndCondition;
import compasses.expandedstorage.recipe.conditions.HasPropertyCondition;
import compasses.expandedstorage.recipe.conditions.IsInTagCondition;
import compasses.expandedstorage.recipe.conditions.IsRegistryObject;
import compasses.expandedstorage.recipe.conditions.RecipeCondition;
import compasses.expandedstorage.recipe.misc.PartialBlockState;
import compasses.expandedstorage.recipe.misc.RecipeTool;
import compasses.expandedstorage.registry.AllBlocks;
import compasses.expandedstorage.registry.AllItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import compasses.expandedstorage.ExpandedStorage;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public abstract class ConversionRecipeProvider implements DataProvider {
    protected static final RecipeTool UNNAMED_MUTATOR = new RecipeTool.MutatorTool(null);
    protected static final RecipeTool SPARROW_MUTATOR = new RecipeTool.MutatorTool("sparrow");

    protected static final RecipeTool WOOD_TO_COPPER_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.WOOD_TO_COPPER_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool WOOD_TO_IRON_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.WOOD_TO_IRON_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool WOOD_TO_GOLD_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.WOOD_TO_GOLD_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool WOOD_TO_DIAMOND_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.WOOD_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool WOOD_TO_OBSIDIAN_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.WOOD_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool WOOD_TO_NETHERITE_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.WOOD_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get());

    protected static final RecipeTool COPPER_TO_IRON_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.COPPER_TO_IRON_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool COPPER_TO_GOLD_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.COPPER_TO_GOLD_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool COPPER_TO_DIAMOND_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.COPPER_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool COPPER_TO_OBSIDIAN_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.COPPER_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool COPPER_TO_NETHERITE_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.COPPER_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get());

    protected static final RecipeTool IRON_TO_GOLD_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.IRON_TO_GOLD_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool IRON_TO_DIAMOND_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.IRON_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool IRON_TO_OBSIDIAN_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.IRON_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool IRON_TO_NETHERITE_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.IRON_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get());

    protected static final RecipeTool GOLD_TO_DIAMOND_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.GOLD_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool GOLD_TO_OBSIDIAN_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.GOLD_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool GOLD_TO_NETHERITE_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.GOLD_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get());

    protected static final RecipeTool DIAMOND_TO_OBSIDIAN_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.DIAMOND_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get());
    protected static final RecipeTool DIAMOND_TO_NETHERITE_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.DIAMOND_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get());

    protected static final RecipeTool OBSIDIAN_TO_NETHERITE_CONVERSION_KIT = new RecipeTool.UpgradeTool(AllItems.OBSIDIAN_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get());

    protected final PackOutput.PathProvider pathProvider;
    private final HashMap<ResourceLocation, BlockConversionRecipe<?>> blockRecipes = new HashMap<>();
    private final HashMap<ResourceLocation, EntityConversionRecipe<?>> entityRecipes = new HashMap<>();

    public ConversionRecipeProvider(PackOutput output) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "conversion_recipes");

        pathProvider.json(ResourceLocation.fromNamespaceAndPath("expandedstorage", "block/wood_to_copper_chest"));
    }

    protected void registerBlockRecipe(ResourceLocation id, BlockConversionRecipe<?> recipe) {
        ResourceLocation realId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "block/" + id.getPath());
        if (blockRecipes.containsKey(realId)) {
            throw new IllegalStateException("Tried registering duplicate block recipe with id: " + realId);
        }
        blockRecipes.put(realId, recipe);
    }

    protected void registerEntityRecipe(ResourceLocation id, EntityConversionRecipe<?> recipe) {
        ResourceLocation realId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "entity/" + id.getPath());
        if (entityRecipes.containsKey(realId)) {
            throw new IllegalStateException("Tried registering duplicate entity recipe with id: " + realId);
        }
        entityRecipes.put(realId, recipe);
    }

    protected void simpleBlockThemeSwap(ResourceLocation id, Block from, Block to) {
        this.registerBlockRecipe(id,
                new BlockConversionRecipe<>(UNNAMED_MUTATOR, new PartialBlockState<>(to),
                        new IsRegistryObject(BuiltInRegistries.BLOCK, from.builtInRegistryHolder().key().location())
                )
        );
    }

    protected void sparrowBlockThemeSwap(ResourceLocation id, Block from, boolean fromSparrow, Block to, boolean toSparrow) {
        ResourceLocation fromId = from.builtInRegistryHolder().key().location();
        this.registerBlockRecipe(id,
                new BlockConversionRecipe<>(SPARROW_MUTATOR, new PartialBlockState<>(to, Map.of(MiniStorageBlock.SPARROW, toSparrow)),
                        new AndCondition(new IsRegistryObject(BuiltInRegistries.BLOCK, fromId),
                                new HasPropertyCondition(fromId, Map.of(MiniStorageBlock.SPARROW, fromSparrow), false))
                )
        );
    }

    protected void sparrowReversibleBlockThemeSwap(String blockName, Block block) {
        ResourceLocation blockId = block.builtInRegistryHolder().key().location();
        this.registerBlockRecipe(ExpandedStorage.id("%s_to_with_sparrow".formatted(blockName)),
                new BlockConversionRecipe<>(SPARROW_MUTATOR, new PartialBlockState<>(block, Map.of(MiniStorageBlock.SPARROW, true)),
                        new AndCondition(new IsRegistryObject(BuiltInRegistries.BLOCK, blockId), new HasPropertyCondition(blockId, Map.of(MiniStorageBlock.SPARROW, false), false))
                )
        );
        this.registerBlockRecipe(ExpandedStorage.id("%s_to_without_sparrow".formatted(blockName)),
                new BlockConversionRecipe<>(UNNAMED_MUTATOR, new PartialBlockState<>(block, Map.of(MiniStorageBlock.SPARROW, false)),
                        new AndCondition(new IsRegistryObject(BuiltInRegistries.BLOCK, blockId), new HasPropertyCondition(blockId, Map.of(MiniStorageBlock.SPARROW, true), false))
                )
        );
    }

    protected void simpleEntityThemeSwap(ResourceLocation id, EntityType<?> from, EntityType<?> to) {
        this.registerEntityRecipe(id,
                new EntityConversionRecipe<>(UNNAMED_MUTATOR, to,
                        new IsRegistryObject(BuiltInRegistries.ENTITY_TYPE, from.builtInRegistryHolder().key().location())
                )
        );
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        blockRecipes.clear();
        entityRecipes.clear();

        this.registerBlockRecipes();
        this.registerEntityRecipes();

        return CompletableFuture.allOf(Stream.concat(blockRecipes.entrySet().stream(), entityRecipes.entrySet().stream())
                                             .map(entry -> {
                                                 JsonElement json = entry.getValue().toJson();
                                                 Path path = pathProvider.json(entry.getKey());
                                                 return DataProvider.saveStable(cachedOutput, json, path);
                                             }).toArray(CompletableFuture[]::new));
    }

    protected abstract void registerBlockRecipes();

    protected void registerBlockRecipes(RecipeCondition isWoodBarrel, RecipeCondition isWoodChest) {
        // Chest upgrade recipes
        {
            var isWoodTier = new IsInTagCondition(ModTags.Blocks.ES_WOODEN_CHESTS);
            var isIronTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.IRON_CHEST_REGISTRY.get().getBlockId());
            var isGoldTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.GOLD_CHEST_REGISTRY.get().getBlockId());
            var isDiamondTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.DIAMOND_CHEST_REGISTRY.get().getBlockId());
            var isObsidianTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.OBSIDIAN_CHEST_REGISTRY.get().getBlockId());
            var ironChest = new PartialBlockState<>(AllBlocks.IRON_CHEST_REGISTRY.get());
            var goldChest = new PartialBlockState<>(AllBlocks.GOLD_CHEST_REGISTRY.get());
            var diamondChest = new PartialBlockState<>(AllBlocks.DIAMOND_CHEST_REGISTRY.get());
            var obsidianChest = new PartialBlockState<>(AllBlocks.OBSIDIAN_CHEST_REGISTRY.get());
            var netheriteChest = new PartialBlockState<>(AllBlocks.NETHERITE_CHEST_REGISTRY.get());

            registerBlockRecipe(ExpandedStorage.id("wood_to_iron_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_IRON_CONVERSION_KIT, ironChest, isWoodTier)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_gold_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_GOLD_CONVERSION_KIT, goldChest, isWoodTier)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_diamond_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_DIAMOND_CONVERSION_KIT, diamondChest, isWoodTier)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_obsidian_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_OBSIDIAN_CONVERSION_KIT, obsidianChest, isWoodTier)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_netherite_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_NETHERITE_CONVERSION_KIT, netheriteChest, isWoodTier)
            );

            registerBlockRecipe(ExpandedStorage.id("iron_to_gold_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.IRON_TO_GOLD_CONVERSION_KIT, goldChest, isIronTier)
            );
            registerBlockRecipe(ExpandedStorage.id("iron_to_diamond_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.IRON_TO_DIAMOND_CONVERSION_KIT, diamondChest, isIronTier)
            );
            registerBlockRecipe(ExpandedStorage.id("iron_to_obsidian_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.IRON_TO_OBSIDIAN_CONVERSION_KIT, obsidianChest, isIronTier)
            );
            registerBlockRecipe(ExpandedStorage.id("iron_to_netherite_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.IRON_TO_NETHERITE_CONVERSION_KIT, netheriteChest, isIronTier)
            );

            registerBlockRecipe(ExpandedStorage.id("gold_to_diamond_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.GOLD_TO_DIAMOND_CONVERSION_KIT, diamondChest, isGoldTier)
            );
            registerBlockRecipe(ExpandedStorage.id("gold_to_obsidian_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.GOLD_TO_OBSIDIAN_CONVERSION_KIT, obsidianChest, isGoldTier)
            );
            registerBlockRecipe(ExpandedStorage.id("gold_to_netherite_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.GOLD_TO_NETHERITE_CONVERSION_KIT, netheriteChest, isGoldTier)
            );

            registerBlockRecipe(ExpandedStorage.id("diamond_to_obsidian_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.DIAMOND_TO_OBSIDIAN_CONVERSION_KIT, obsidianChest, isDiamondTier)
            );
            registerBlockRecipe(ExpandedStorage.id("diamond_to_netherite_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.DIAMOND_TO_NETHERITE_CONVERSION_KIT, netheriteChest, isDiamondTier)
            );

            registerBlockRecipe(ExpandedStorage.id("obsidian_to_netherite_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.OBSIDIAN_TO_NETHERITE_CONVERSION_KIT, netheriteChest, isObsidianTier)
            );
        }

        // Old chest upgrade recipes
        {
            var isWoodTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.OLD_WOOD_CHEST_REGISTRY.get().getBlockId());
            var isIronTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.OLD_IRON_CHEST_REGISTRY.get().getBlockId());
            var isGoldTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.OLD_GOLD_CHEST_REGISTRY.get().getBlockId());
            var isDiamondTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.OLD_DIAMOND_CHEST_REGISTRY.get().getBlockId());
            var isObsidianTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.OLD_OBSIDIAN_CHEST_REGISTRY.get().getBlockId());
            var ironChest = new PartialBlockState<>(AllBlocks.OLD_IRON_CHEST_REGISTRY.get());
            var goldChest = new PartialBlockState<>(AllBlocks.OLD_GOLD_CHEST_REGISTRY.get());
            var diamondChest = new PartialBlockState<>(AllBlocks.OLD_DIAMOND_CHEST_REGISTRY.get());
            var obsidianChest = new PartialBlockState<>(AllBlocks.OLD_OBSIDIAN_CHEST_REGISTRY.get());
            var netheriteChest = new PartialBlockState<>(AllBlocks.OLD_NETHERITE_CHEST_REGISTRY.get());

            registerBlockRecipe(ExpandedStorage.id("wood_to_iron_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_IRON_CONVERSION_KIT, ironChest, isWoodTier)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_gold_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_GOLD_CONVERSION_KIT, goldChest, isWoodTier)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_diamond_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_DIAMOND_CONVERSION_KIT, diamondChest, isWoodTier)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_obsidian_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_OBSIDIAN_CONVERSION_KIT, obsidianChest, isWoodTier)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_netherite_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_NETHERITE_CONVERSION_KIT, netheriteChest, isWoodTier)
            );

            registerBlockRecipe(ExpandedStorage.id("iron_to_gold_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.IRON_TO_GOLD_CONVERSION_KIT, goldChest, isIronTier)
            );
            registerBlockRecipe(ExpandedStorage.id("iron_to_diamond_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.IRON_TO_DIAMOND_CONVERSION_KIT, diamondChest, isIronTier)
            );
            registerBlockRecipe(ExpandedStorage.id("iron_to_obsidian_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.IRON_TO_OBSIDIAN_CONVERSION_KIT, obsidianChest, isIronTier)
            );
            registerBlockRecipe(ExpandedStorage.id("iron_to_netherite_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.IRON_TO_NETHERITE_CONVERSION_KIT, netheriteChest, isIronTier)
            );

            registerBlockRecipe(ExpandedStorage.id("gold_to_diamond_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.GOLD_TO_DIAMOND_CONVERSION_KIT, diamondChest, isGoldTier)
            );
            registerBlockRecipe(ExpandedStorage.id("gold_to_obsidian_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.GOLD_TO_OBSIDIAN_CONVERSION_KIT, obsidianChest, isGoldTier)
            );
            registerBlockRecipe(ExpandedStorage.id("gold_to_netherite_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.GOLD_TO_NETHERITE_CONVERSION_KIT, netheriteChest, isGoldTier)
            );

            registerBlockRecipe(ExpandedStorage.id("diamond_to_obsidian_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.DIAMOND_TO_OBSIDIAN_CONVERSION_KIT, obsidianChest, isDiamondTier)
            );
            registerBlockRecipe(ExpandedStorage.id("diamond_to_netherite_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.DIAMOND_TO_NETHERITE_CONVERSION_KIT, netheriteChest, isDiamondTier)
            );

            registerBlockRecipe(ExpandedStorage.id("obsidian_to_netherite_old_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.OBSIDIAN_TO_NETHERITE_CONVERSION_KIT, netheriteChest, isObsidianTier)
            );
        }

        // Barrel upgrade recipes
        {
            var isCopperTier = new IsInTagCondition(ModTags.Blocks.COPPER_BARRELS);
            var isIronTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.IRON_BARREL_REGISTRY.get().getBlockId());
            var isGoldTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.GOLD_BARREL_REGISTRY.get().getBlockId());
            var isDiamondTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.DIAMOND_BARREL_REGISTRY.get().getBlockId());
            var isObsidianTier = new IsRegistryObject(BuiltInRegistries.BLOCK, AllBlocks.OBSIDIAN_BARREL_REGISTRY.get().getBlockId());
            var ironBarrel = new PartialBlockState<>(AllBlocks.IRON_BARREL_REGISTRY.get());
            var goldBarrel = new PartialBlockState<>(AllBlocks.GOLD_BARREL_REGISTRY.get());
            var diamondBarrel = new PartialBlockState<>(AllBlocks.DIAMOND_BARREL_REGISTRY.get());
            var obsidianBarrel = new PartialBlockState<>(AllBlocks.OBSIDIAN_BARREL_REGISTRY.get());
            var netheriteBarrel = new PartialBlockState<>(AllBlocks.NETHERITE_BARREL_REGISTRY.get());

            registerBlockRecipe(ExpandedStorage.id("wood_to_copper_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_COPPER_CONVERSION_KIT, new PartialBlockState<>(AllBlocks.COPPER_BARREL_REGISTRY.get()), isWoodBarrel)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_iron_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_IRON_CONVERSION_KIT, ironBarrel, isWoodBarrel)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_gold_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_GOLD_CONVERSION_KIT, goldBarrel, isWoodBarrel)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_diamond_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_DIAMOND_CONVERSION_KIT, diamondBarrel, isWoodBarrel)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_obsidian_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_OBSIDIAN_CONVERSION_KIT, obsidianBarrel, isWoodBarrel)
            );
            registerBlockRecipe(ExpandedStorage.id("wood_to_netherite_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_NETHERITE_CONVERSION_KIT, netheriteBarrel, isWoodBarrel)
            );

            registerBlockRecipe(ExpandedStorage.id("copper_to_iron_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.COPPER_TO_IRON_CONVERSION_KIT, ironBarrel, isCopperTier)
            );
            registerBlockRecipe(ExpandedStorage.id("copper_to_gold_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.COPPER_TO_GOLD_CONVERSION_KIT, goldBarrel, isCopperTier)
            );
            registerBlockRecipe(ExpandedStorage.id("copper_to_diamond_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.COPPER_TO_DIAMOND_CONVERSION_KIT, diamondBarrel, isCopperTier)
            );
            registerBlockRecipe(ExpandedStorage.id("copper_to_obsidian_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.COPPER_TO_OBSIDIAN_CONVERSION_KIT, obsidianBarrel, isCopperTier)
            );
            registerBlockRecipe(ExpandedStorage.id("copper_to_netherite_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.COPPER_TO_NETHERITE_CONVERSION_KIT, netheriteBarrel, isCopperTier)
            );

            registerBlockRecipe(ExpandedStorage.id("iron_to_gold_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.IRON_TO_GOLD_CONVERSION_KIT, goldBarrel, isIronTier)
            );
            registerBlockRecipe(ExpandedStorage.id("iron_to_diamond_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.IRON_TO_DIAMOND_CONVERSION_KIT, diamondBarrel, isIronTier)
            );
            registerBlockRecipe(ExpandedStorage.id("iron_to_obsidian_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.IRON_TO_OBSIDIAN_CONVERSION_KIT, obsidianBarrel, isIronTier)
            );
            registerBlockRecipe(ExpandedStorage.id("iron_to_netherite_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.IRON_TO_NETHERITE_CONVERSION_KIT, netheriteBarrel, isIronTier)
            );

            registerBlockRecipe(ExpandedStorage.id("gold_to_diamond_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.GOLD_TO_DIAMOND_CONVERSION_KIT, diamondBarrel, isGoldTier)
            );
            registerBlockRecipe(ExpandedStorage.id("gold_to_obsidian_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.GOLD_TO_OBSIDIAN_CONVERSION_KIT, obsidianBarrel, isGoldTier)
            );
            registerBlockRecipe(ExpandedStorage.id("gold_to_netherite_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.GOLD_TO_NETHERITE_CONVERSION_KIT, netheriteBarrel, isGoldTier)
            );

            registerBlockRecipe(ExpandedStorage.id("diamond_to_obsidian_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.DIAMOND_TO_OBSIDIAN_CONVERSION_KIT, obsidianBarrel, isDiamondTier)
            );
            registerBlockRecipe(ExpandedStorage.id("diamond_to_netherite_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.DIAMOND_TO_NETHERITE_CONVERSION_KIT, netheriteBarrel, isDiamondTier)
            );

            registerBlockRecipe(ExpandedStorage.id("obsidian_to_netherite_barrel"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.OBSIDIAN_TO_NETHERITE_CONVERSION_KIT, netheriteBarrel, isObsidianTier)
            );
        }

        // Chest theme swap recipes
        {
            registerBlockRecipe(ExpandedStorage.id("vanilla_to_wood_chest"),
                    new BlockConversionRecipe<>(ConversionRecipeProvider.UNNAMED_MUTATOR, new PartialBlockState<>(AllBlocks.WOOD_CHEST_REGISTRY.get()), isWoodChest)
            );

            simpleBlockThemeSwap(ExpandedStorage.id("wood_to_pumpkin_chest"), AllBlocks.WOOD_CHEST_REGISTRY.get(), AllBlocks.PUMPKIN_CHEST_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("pumpkin_to_present_chest"), AllBlocks.PUMPKIN_CHEST_REGISTRY.get(), AllBlocks.PRESENT_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("present_to_bamboo_chest"), AllBlocks.PRESENT_REGISTRY.get(), AllBlocks.BAMBOO_CHEST_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("bamboo_to_moss_chest"), AllBlocks.BAMBOO_CHEST_REGISTRY.get(), AllBlocks.MOSS_CHEST_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("moss_to_old_wood_chest"), AllBlocks.MOSS_CHEST_REGISTRY.get(), AllBlocks.OLD_WOOD_CHEST_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("old_wood_chest_to_wood_chest"), AllBlocks.OLD_WOOD_CHEST_REGISTRY.get(), AllBlocks.WOOD_CHEST_REGISTRY.get());

            simpleBlockThemeSwap(ExpandedStorage.id("iron_to_old_iron_chest"), AllBlocks.IRON_CHEST_REGISTRY.get(), AllBlocks.OLD_IRON_CHEST_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("old_iron_to_iron_chest"), AllBlocks.OLD_IRON_CHEST_REGISTRY.get(), AllBlocks.IRON_CHEST_REGISTRY.get());

            simpleBlockThemeSwap(ExpandedStorage.id("gold_to_old_gold_chest"), AllBlocks.GOLD_CHEST_REGISTRY.get(), AllBlocks.OLD_GOLD_CHEST_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("old_gold_to_gold_chest"), AllBlocks.OLD_GOLD_CHEST_REGISTRY.get(), AllBlocks.GOLD_CHEST_REGISTRY.get());

            simpleBlockThemeSwap(ExpandedStorage.id("diamond_to_old_diamond_chest"), AllBlocks.DIAMOND_CHEST_REGISTRY.get(), AllBlocks.OLD_DIAMOND_CHEST_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("old_diamond_to_diamond_chest"), AllBlocks.OLD_DIAMOND_CHEST_REGISTRY.get(), AllBlocks.DIAMOND_CHEST_REGISTRY.get());

            simpleBlockThemeSwap(ExpandedStorage.id("obsidian_to_old_obsidian_chest"), AllBlocks.OBSIDIAN_CHEST_REGISTRY.get(), AllBlocks.OLD_OBSIDIAN_CHEST_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("old_obsidian_to_obsidian_chest"), AllBlocks.OLD_OBSIDIAN_CHEST_REGISTRY.get(), AllBlocks.OBSIDIAN_CHEST_REGISTRY.get());

            simpleBlockThemeSwap(ExpandedStorage.id("netherite_to_old_netherite_chest"), AllBlocks.NETHERITE_CHEST_REGISTRY.get(), AllBlocks.OLD_NETHERITE_CHEST_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("old_netherite_to_netherite_chest"), AllBlocks.OLD_NETHERITE_CHEST_REGISTRY.get(), AllBlocks.NETHERITE_CHEST_REGISTRY.get());
        }

        // Mini storage theme swap recipes
        {
            simpleBlockThemeSwap(ExpandedStorage.id("vanilla_to_wood_mini_chest"), AllBlocks.VANILLA_WOOD_MINI_CHEST_REGISTRY.get(), AllBlocks.WOOD_MINI_CHEST_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("wood_to_pumpkin_mini_chest"), AllBlocks.WOOD_MINI_CHEST_REGISTRY.get(), AllBlocks.PUMPKIN_MINI_CHEST_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("pumpkin_to_red_mini_present"), AllBlocks.PUMPKIN_MINI_CHEST_REGISTRY.get(), AllBlocks.RED_MINI_PRESENT_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("red_to_white_mini_present"), AllBlocks.RED_MINI_PRESENT_REGISTRY.get(), AllBlocks.WHITE_MINI_PRESENT_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("white_to_candy_cane_mini_present"), AllBlocks.WHITE_MINI_PRESENT_REGISTRY.get(), AllBlocks.CANDY_CANE_MINI_PRESENT_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("candy_cane_to_green_mini_present"), AllBlocks.CANDY_CANE_MINI_PRESENT_REGISTRY.get(), AllBlocks.GREEN_MINI_PRESENT_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("green_to_lavender_present"), AllBlocks.GREEN_MINI_PRESENT_REGISTRY.get(), AllBlocks.LAVENDER_MINI_PRESENT_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("lavender_to_pink_amethyst_mini_present"), AllBlocks.LAVENDER_MINI_PRESENT_REGISTRY.get(), AllBlocks.PINK_AMETHYST_MINI_PRESENT_REGISTRY.get());
            simpleBlockThemeSwap(ExpandedStorage.id("pink_amethyst_to_vanilla_mini_chest"), AllBlocks.PINK_AMETHYST_MINI_PRESENT_REGISTRY.get(), AllBlocks.VANILLA_WOOD_MINI_CHEST_REGISTRY.get());

            sparrowBlockThemeSwap(ExpandedStorage.id("pink_amethyst_to_vanilla_mini_chest_with_sparrow"), AllBlocks.PINK_AMETHYST_MINI_PRESENT_REGISTRY.get(), false, AllBlocks.VANILLA_WOOD_MINI_CHEST_REGISTRY.get(), true);
            sparrowBlockThemeSwap(ExpandedStorage.id("vanilla_to_wood_mini_chest_with_sparrow"), AllBlocks.VANILLA_WOOD_MINI_CHEST_REGISTRY.get(), true, AllBlocks.WOOD_MINI_CHEST_REGISTRY.get(), true);
            sparrowBlockThemeSwap(ExpandedStorage.id("wood_to_pumpkin_mini_chest_with_sparrow"), AllBlocks.WOOD_MINI_CHEST_REGISTRY.get(), true, AllBlocks.PUMPKIN_MINI_CHEST_REGISTRY.get(), true);
            sparrowBlockThemeSwap(ExpandedStorage.id("pumpkin_to_red_mini_present_with_sparrow"), AllBlocks.PUMPKIN_MINI_CHEST_REGISTRY.get(), true, AllBlocks.RED_MINI_PRESENT_REGISTRY.get(), true);
            sparrowBlockThemeSwap(ExpandedStorage.id("red_to_white_mini_present_with_sparrow"), AllBlocks.RED_MINI_PRESENT_REGISTRY.get(), true, AllBlocks.WHITE_MINI_PRESENT_REGISTRY.get(), true);
            sparrowBlockThemeSwap(ExpandedStorage.id("white_to_candy_cane_mini_present_with_sparrow"), AllBlocks.WHITE_MINI_PRESENT_REGISTRY.get(), true, AllBlocks.CANDY_CANE_MINI_PRESENT_REGISTRY.get(), true);
            sparrowBlockThemeSwap(ExpandedStorage.id("candy_cane_to_green_mini_present_with_sparrow"), AllBlocks.CANDY_CANE_MINI_PRESENT_REGISTRY.get(), true, AllBlocks.GREEN_MINI_PRESENT_REGISTRY.get(), true);
            sparrowBlockThemeSwap(ExpandedStorage.id("green_to_lavender_present_with_sparrow"), AllBlocks.GREEN_MINI_PRESENT_REGISTRY.get(), true, AllBlocks.LAVENDER_MINI_PRESENT_REGISTRY.get(), true);
            sparrowBlockThemeSwap(ExpandedStorage.id("lavender_to_pink_amethyst_mini_present_with_sparrow"), AllBlocks.LAVENDER_MINI_PRESENT_REGISTRY.get(), true, AllBlocks.PINK_AMETHYST_MINI_PRESENT_REGISTRY.get(), true);
            sparrowBlockThemeSwap(ExpandedStorage.id("pink_amethyst_with_sparrow_to_vanilla_mini_chest"), AllBlocks.PINK_AMETHYST_MINI_PRESENT_REGISTRY.get(), true, AllBlocks.VANILLA_WOOD_MINI_CHEST_REGISTRY.get(), false);

            sparrowReversibleBlockThemeSwap("iron_mini_chest", AllBlocks.IRON_MINI_CHEST_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("gold_mini_chest", AllBlocks.GOLD_MINI_CHEST_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("diamond_mini_chest", AllBlocks.DIAMOND_MINI_CHEST_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("obsidian_mini_chest", AllBlocks.OBSIDIAN_MINI_CHEST_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("netherite_mini_chest", AllBlocks.NETHERITE_MINI_CHEST_REGISTRY.get());

            sparrowReversibleBlockThemeSwap("mini_barrel", AllBlocks.MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("copper_mini_barrel", AllBlocks.COPPER_MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("exposed_copper_mini_barrel", AllBlocks.EXPOSED_COPPER_MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("weathered_copper_mini_barrel", AllBlocks.WEATHERED_COPPER_MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("oxidized_copper_mini_barrel", AllBlocks.OXIDIZED_COPPER_MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("waxed_copper_mini_barrel", AllBlocks.WAXED_COPPER_MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("waxed_exposed_copper_mini_barrel", AllBlocks.WAXED_EXPOSED_COPPER_MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("waxed_weathered_copper_mini_barrel", AllBlocks.WAXED_WEATHERED_COPPER_MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("waxed_oxidized_copper_mini_barrel", AllBlocks.WAXED_OXIDIZED_COPPER_MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("iron_mini_barrel", AllBlocks.IRON_MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("gold_mini_barrel", AllBlocks.GOLD_MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("diamond_mini_barrel", AllBlocks.DIAMOND_MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("obsidian_mini_barrel", AllBlocks.OBSIDIAN_MINI_BARREL_REGISTRY.get());
            sparrowReversibleBlockThemeSwap("netherite_mini_barrel", AllBlocks.NETHERITE_MINI_BARREL_REGISTRY.get());
        }
    }

    protected abstract void registerEntityRecipes();

    protected void registerEntityRecipes(RecipeCondition isWoodenMinecart) {
        {
            var isWoodTier = new IsInTagCondition(ModTags.Entities.ES_WOODEN_CHEST_MINECARTS);
            var isIronTier = new IsRegistryObject(BuiltInRegistries.ENTITY_TYPE, ModEntityTypes.IRON_CHEST_MINECART.builtInRegistryHolder().key().location());
            var isGoldTier = new IsRegistryObject(BuiltInRegistries.ENTITY_TYPE, ModEntityTypes.GOLD_CHEST_MINECART.builtInRegistryHolder().key().location());
            var isDiamondTier = new IsRegistryObject(BuiltInRegistries.ENTITY_TYPE, ModEntityTypes.DIAMOND_CHEST_MINECART.builtInRegistryHolder().key().location());
            var isObsidianTier = new IsRegistryObject(BuiltInRegistries.ENTITY_TYPE, ModEntityTypes.OBSIDIAN_CHEST_MINECART.builtInRegistryHolder().key().location());

            registerEntityRecipe(ExpandedStorage.id("wood_to_iron_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_IRON_CONVERSION_KIT, ModEntityTypes.IRON_CHEST_MINECART, isWoodTier)
            );
            registerEntityRecipe(ExpandedStorage.id("wood_to_gold_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_GOLD_CONVERSION_KIT, ModEntityTypes.GOLD_CHEST_MINECART, isWoodTier)
            );
            registerEntityRecipe(ExpandedStorage.id("wood_to_diamond_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_DIAMOND_CONVERSION_KIT, ModEntityTypes.DIAMOND_CHEST_MINECART, isWoodTier)
            );
            registerEntityRecipe(ExpandedStorage.id("wood_to_obsidian_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_OBSIDIAN_CONVERSION_KIT, ModEntityTypes.OBSIDIAN_CHEST_MINECART, isWoodTier)
            );
            registerEntityRecipe(ExpandedStorage.id("wood_to_netherite_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.WOOD_TO_NETHERITE_CONVERSION_KIT, ModEntityTypes.NETHERITE_CHEST_MINECART, isWoodTier)
            );

            registerEntityRecipe(ExpandedStorage.id("iron_to_gold_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.IRON_TO_GOLD_CONVERSION_KIT, ModEntityTypes.GOLD_CHEST_MINECART, isIronTier)
            );
            registerEntityRecipe(ExpandedStorage.id("iron_to_diamond_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.IRON_TO_DIAMOND_CONVERSION_KIT, ModEntityTypes.DIAMOND_CHEST_MINECART, isIronTier)
            );
            registerEntityRecipe(ExpandedStorage.id("iron_to_obsidian_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.IRON_TO_OBSIDIAN_CONVERSION_KIT, ModEntityTypes.OBSIDIAN_CHEST_MINECART, isIronTier)
            );
            registerEntityRecipe(ExpandedStorage.id("iron_to_netherite_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.IRON_TO_NETHERITE_CONVERSION_KIT, ModEntityTypes.NETHERITE_CHEST_MINECART, isIronTier)
            );

            registerEntityRecipe(ExpandedStorage.id("gold_to_diamond_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.GOLD_TO_DIAMOND_CONVERSION_KIT, ModEntityTypes.DIAMOND_CHEST_MINECART, isGoldTier)
            );
            registerEntityRecipe(ExpandedStorage.id("gold_to_obsidian_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.GOLD_TO_OBSIDIAN_CONVERSION_KIT, ModEntityTypes.OBSIDIAN_CHEST_MINECART, isGoldTier)
            );
            registerEntityRecipe(ExpandedStorage.id("gold_to_netherite_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.GOLD_TO_NETHERITE_CONVERSION_KIT, ModEntityTypes.NETHERITE_CHEST_MINECART, isGoldTier)
            );

            registerEntityRecipe(ExpandedStorage.id("diamond_to_obsidian_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.DIAMOND_TO_OBSIDIAN_CONVERSION_KIT, ModEntityTypes.OBSIDIAN_CHEST_MINECART, isDiamondTier)
            );
            registerEntityRecipe(ExpandedStorage.id("diamond_to_netherite_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.DIAMOND_TO_NETHERITE_CONVERSION_KIT, ModEntityTypes.NETHERITE_CHEST_MINECART, isDiamondTier)
            );

            registerEntityRecipe(ExpandedStorage.id("obsidian_to_netherite_chest_minecart"),
                    new EntityConversionRecipe<>(ConversionRecipeProvider.OBSIDIAN_TO_NETHERITE_CONVERSION_KIT, ModEntityTypes.NETHERITE_CHEST_MINECART, isObsidianTier)
            );
        }

        registerEntityRecipe(ExpandedStorage.id("vanilla_to_wood_chest_minecart"),
                new EntityConversionRecipe<>(UNNAMED_MUTATOR, ModEntityTypes.WOOD_CHEST_MINECART, isWoodenMinecart)
        );

        simpleEntityThemeSwap(ExpandedStorage.id("wood_to_pumpkin_chest_minecart"), ModEntityTypes.WOOD_CHEST_MINECART, ModEntityTypes.PUMPKIN_CHEST_MINECART);
        simpleEntityThemeSwap(ExpandedStorage.id("pumpkin_to_present_chest_minecart"), ModEntityTypes.PUMPKIN_CHEST_MINECART, ModEntityTypes.PRESENT_MINECART);
        simpleEntityThemeSwap(ExpandedStorage.id("present_to_bamboo_chest_minecart"), ModEntityTypes.PRESENT_MINECART, ModEntityTypes.BAMBOO_CHEST_MINECART);
        simpleEntityThemeSwap(ExpandedStorage.id("bamboo_to_moss_chest_minecart"), ModEntityTypes.BAMBOO_CHEST_MINECART, ModEntityTypes.MOSS_CHEST_MINECART);
        simpleEntityThemeSwap(ExpandedStorage.id("moss_to_wood_chest_minecart"), ModEntityTypes.MOSS_CHEST_MINECART, ModEntityTypes.WOOD_CHEST_MINECART);
    }

    @NotNull
    @Override
    public String getName() {
        return "Expanded Storage - Conversion Recipes";
    }
}

