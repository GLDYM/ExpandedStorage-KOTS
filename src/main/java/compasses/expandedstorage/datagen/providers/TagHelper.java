package compasses.expandedstorage.datagen.providers;

import compasses.expandedstorage.datagen.content.ModEntityTypes;
import compasses.expandedstorage.datagen.content.ModTags;
import compasses.expandedstorage.registry.AllBlocks;
import compasses.expandedstorage.registry.AllItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class TagHelper {

    private static <T> ResourceKey<T> resourceKeyOf(Registry<T> registry, T thing) {
        return registry.getResourceKey(thing).orElseThrow();
    }

    private static ResourceKey<Block> block(Block block) {
        return resourceKeyOf(BuiltInRegistries.BLOCK, block);
    }

    private static ResourceKey<Item> item(Item item) {
        return resourceKeyOf(BuiltInRegistries.ITEM, item);
    }

    private static ResourceKey<EntityType<?>> entityType(EntityType<?> entityType) {
        return resourceKeyOf(BuiltInRegistries.ENTITY_TYPE, entityType);
    }

    public static void registerBlockTags(Function<TagKey<Block>, TagsProvider.TagAppender<Block>> tagMaker) {
        tagMaker.apply(BlockTags.MINEABLE_WITH_AXE)
                .add(block(AllBlocks.COPPER_BARREL))
                .add(block(AllBlocks.EXPOSED_COPPER_BARREL))
                .add(block(AllBlocks.WEATHERED_COPPER_BARREL))
                .add(block(AllBlocks.OXIDIZED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_EXPOSED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_WEATHERED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_OXIDIZED_COPPER_BARREL))
                .add(block(AllBlocks.IRON_BARREL))
                .add(block(AllBlocks.GOLD_BARREL))
                .add(block(AllBlocks.DIAMOND_BARREL))
                .add(block(AllBlocks.OBSIDIAN_BARREL))
                .add(block(AllBlocks.NETHERITE_BARREL))
                .add(block(AllBlocks.WOOD_CHEST))
                .add(block(AllBlocks.PUMPKIN_CHEST))
                .add(block(AllBlocks.PRESENT))
                .add(block(AllBlocks.OLD_WOOD_CHEST))
                .add(block(AllBlocks.VANILLA_WOOD_MINI_CHEST))
                .add(block(AllBlocks.WOOD_MINI_CHEST))
                .add(block(AllBlocks.PUMPKIN_MINI_CHEST))
                .add(block(AllBlocks.RED_MINI_PRESENT))
                .add(block(AllBlocks.WHITE_MINI_PRESENT))
                .add(block(AllBlocks.CANDY_CANE_MINI_PRESENT))
                .add(block(AllBlocks.GREEN_MINI_PRESENT))
                .add(block(AllBlocks.LAVENDER_MINI_PRESENT))
                .add(block(AllBlocks.PINK_AMETHYST_MINI_PRESENT))
                .add(block(AllBlocks.MINI_BARREL))
                .add(block(AllBlocks.COPPER_MINI_BARREL))
                .add(block(AllBlocks.EXPOSED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WEATHERED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.OXIDIZED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WAXED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WAXED_EXPOSED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WAXED_WEATHERED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WAXED_OXIDIZED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.IRON_MINI_BARREL))
                .add(block(AllBlocks.GOLD_MINI_BARREL))
                .add(block(AllBlocks.DIAMOND_MINI_BARREL))
                .add(block(AllBlocks.OBSIDIAN_MINI_BARREL))
                .add(block(AllBlocks.NETHERITE_MINI_BARREL));
        tagMaker.apply(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(block(AllBlocks.IRON_CHEST))
                .add(block(AllBlocks.GOLD_CHEST))
                .add(block(AllBlocks.DIAMOND_CHEST))
                .add(block(AllBlocks.OBSIDIAN_CHEST))
                .add(block(AllBlocks.NETHERITE_CHEST))
                .add(block(AllBlocks.OLD_IRON_CHEST))
                .add(block(AllBlocks.OLD_GOLD_CHEST))
                .add(block(AllBlocks.OLD_DIAMOND_CHEST))
                .add(block(AllBlocks.OLD_OBSIDIAN_CHEST))
                .add(block(AllBlocks.OLD_NETHERITE_CHEST))
                .add(block(AllBlocks.IRON_MINI_CHEST))
                .add(block(AllBlocks.GOLD_MINI_CHEST))
                .add(block(AllBlocks.DIAMOND_MINI_CHEST))
                .add(block(AllBlocks.OBSIDIAN_MINI_CHEST))
                .add(block(AllBlocks.NETHERITE_MINI_CHEST));
        tagMaker.apply(BlockTags.MINEABLE_WITH_HOE)
                .add(block(AllBlocks.MOSS_CHEST));
        tagMaker.apply(BlockTags.GUARDED_BY_PIGLINS)
                .add(block(AllBlocks.COPPER_BARREL))
                .add(block(AllBlocks.EXPOSED_COPPER_BARREL))
                .add(block(AllBlocks.WEATHERED_COPPER_BARREL))
                .add(block(AllBlocks.OXIDIZED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_EXPOSED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_WEATHERED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_OXIDIZED_COPPER_BARREL))
                .add(block(AllBlocks.IRON_BARREL))
                .add(block(AllBlocks.GOLD_BARREL))
                .add(block(AllBlocks.DIAMOND_BARREL))
                .add(block(AllBlocks.OBSIDIAN_BARREL))
                .add(block(AllBlocks.NETHERITE_BARREL))
                .add(block(AllBlocks.WOOD_CHEST))
                .add(block(AllBlocks.PUMPKIN_CHEST))
                .add(block(AllBlocks.PRESENT))
                .add(block(AllBlocks.BAMBOO_CHEST))
                .add(block(AllBlocks.MOSS_CHEST))
                .add(block(AllBlocks.IRON_CHEST))
                .add(block(AllBlocks.GOLD_CHEST))
                .add(block(AllBlocks.DIAMOND_CHEST))
                .add(block(AllBlocks.OBSIDIAN_CHEST))
                .add(block(AllBlocks.NETHERITE_CHEST))
                .add(block(AllBlocks.OLD_WOOD_CHEST))
                .add(block(AllBlocks.OLD_IRON_CHEST))
                .add(block(AllBlocks.OLD_GOLD_CHEST))
                .add(block(AllBlocks.OLD_DIAMOND_CHEST))
                .add(block(AllBlocks.OLD_OBSIDIAN_CHEST))
                .add(block(AllBlocks.OLD_NETHERITE_CHEST))
                .add(block(AllBlocks.VANILLA_WOOD_MINI_CHEST))
                .add(block(AllBlocks.WOOD_MINI_CHEST))
                .add(block(AllBlocks.PUMPKIN_MINI_CHEST))
                .add(block(AllBlocks.RED_MINI_PRESENT))
                .add(block(AllBlocks.WHITE_MINI_PRESENT))
                .add(block(AllBlocks.CANDY_CANE_MINI_PRESENT))
                .add(block(AllBlocks.GREEN_MINI_PRESENT))
                .add(block(AllBlocks.LAVENDER_MINI_PRESENT))
                .add(block(AllBlocks.PINK_AMETHYST_MINI_PRESENT))
                .add(block(AllBlocks.IRON_MINI_CHEST))
                .add(block(AllBlocks.GOLD_MINI_CHEST))
                .add(block(AllBlocks.DIAMOND_MINI_CHEST))
                .add(block(AllBlocks.OBSIDIAN_MINI_CHEST))
                .add(block(AllBlocks.NETHERITE_MINI_CHEST))
                .add(block(AllBlocks.MINI_BARREL))
                .add(block(AllBlocks.COPPER_MINI_BARREL))
                .add(block(AllBlocks.EXPOSED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WEATHERED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.OXIDIZED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WAXED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WAXED_EXPOSED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WAXED_WEATHERED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WAXED_OXIDIZED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.IRON_MINI_BARREL))
                .add(block(AllBlocks.GOLD_MINI_BARREL))
                .add(block(AllBlocks.DIAMOND_MINI_BARREL))
                .add(block(AllBlocks.OBSIDIAN_MINI_BARREL))
                .add(block(AllBlocks.NETHERITE_MINI_BARREL));
        tagMaker.apply(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(block(AllBlocks.OBSIDIAN_BARREL))
                .add(block(AllBlocks.NETHERITE_BARREL))
                .add(block(AllBlocks.OBSIDIAN_CHEST))
                .add(block(AllBlocks.NETHERITE_CHEST))
                .add(block(AllBlocks.OLD_OBSIDIAN_CHEST))
                .add(block(AllBlocks.OLD_NETHERITE_CHEST))
                .add(block(AllBlocks.OBSIDIAN_MINI_CHEST))
                .add(block(AllBlocks.NETHERITE_MINI_CHEST))
                .add(block(AllBlocks.OBSIDIAN_MINI_BARREL))
                .add(block(AllBlocks.NETHERITE_MINI_BARREL));
        tagMaker.apply(BlockTags.NEEDS_IRON_TOOL)
                .add(block(AllBlocks.GOLD_BARREL))
                .add(block(AllBlocks.DIAMOND_BARREL))
                .add(block(AllBlocks.GOLD_CHEST))
                .add(block(AllBlocks.DIAMOND_CHEST))
                .add(block(AllBlocks.OLD_GOLD_CHEST))
                .add(block(AllBlocks.OLD_DIAMOND_CHEST))
                .add(block(AllBlocks.GOLD_MINI_CHEST))
                .add(block(AllBlocks.DIAMOND_MINI_CHEST))
                .add(block(AllBlocks.GOLD_MINI_BARREL))
                .add(block(AllBlocks.DIAMOND_MINI_BARREL));
        tagMaker.apply(BlockTags.NEEDS_STONE_TOOL)
                .add(block(AllBlocks.COPPER_BARREL))
                .add(block(AllBlocks.EXPOSED_COPPER_BARREL))
                .add(block(AllBlocks.WEATHERED_COPPER_BARREL))
                .add(block(AllBlocks.OXIDIZED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_EXPOSED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_WEATHERED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_OXIDIZED_COPPER_BARREL))
                .add(block(AllBlocks.IRON_BARREL))
                .add(block(AllBlocks.IRON_CHEST))
                .add(block(AllBlocks.OLD_IRON_CHEST))
                .add(block(AllBlocks.IRON_MINI_CHEST))
                .add(block(AllBlocks.COPPER_MINI_BARREL))
                .add(block(AllBlocks.EXPOSED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WEATHERED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.OXIDIZED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WAXED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WAXED_EXPOSED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WAXED_WEATHERED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.WAXED_OXIDIZED_COPPER_MINI_BARREL))
                .add(block(AllBlocks.IRON_MINI_BARREL));
        tagMaker.apply(ModTags.Blocks.COPPER_BARRELS)
                .add(block(AllBlocks.COPPER_BARREL))
                .add(block(AllBlocks.EXPOSED_COPPER_BARREL))
                .add(block(AllBlocks.WEATHERED_COPPER_BARREL))
                .add(block(AllBlocks.OXIDIZED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_EXPOSED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_WEATHERED_COPPER_BARREL))
                .add(block(AllBlocks.WAXED_OXIDIZED_COPPER_BARREL));
        tagMaker.apply(ModTags.Blocks.ES_WOODEN_CHESTS)
                .add(block(AllBlocks.PUMPKIN_CHEST))
                .add(block(AllBlocks.PRESENT))
                .add(block(AllBlocks.BAMBOO_CHEST))
                .add(block(AllBlocks.MOSS_CHEST));
//        tagMaker.apply(ModTags.Blocks.COPPER_CHESTS)
//                .add(AllBlocks.COPPER_CHEST)
//                .add(AllBlocks.EXPOSED_COPPER_CHEST)
//                .add(AllBlocks.WEATHERED_COPPER_CHEST)
//                .add(AllBlocks.OXIDIZED_COPPER_CHEST)
//                .add(AllBlocks.WAXED_COPPER_CHEST)
//                .add(AllBlocks.WAXED_EXPOSED_COPPER_CHEST)
//                .add(AllBlocks.WAXED_WEATHERED_COPPER_CHEST)
//                .add(AllBlocks.WAXED_OXIDIZED_COPPER_CHEST);
//        tagMaker.apply(ModTags.Blocks.OLD_COPPER_CHESTS)
//                .add(AllBlocks.OLD_COPPER_CHEST)
//                .add(AllBlocks.OLD_EXPOSED_COPPER_CHEST)
//                .add(AllBlocks.OLD_WEATHERED_COPPER_CHEST)
//                .add(AllBlocks.OLD_OXIDIZED_COPPER_CHEST)
//                .add(AllBlocks.WAXED_OLD_COPPER_CHEST)
//                .add(AllBlocks.WAXED_OLD_EXPOSED_COPPER_CHEST)
//                .add(AllBlocks.WAXED_OLD_WEATHERED_COPPER_CHEST)
//                .add(AllBlocks.WAXED_OLD_OXIDIZED_COPPER_CHEST);
    }

    public static void registerItemTags(Function<TagKey<Item>, TagsProvider.TagAppender<Item>> tagMaker) {
        tagMaker.apply(ModTags.Items.ES_WOODEN_CHESTS)
                .add(item(AllItems.PUMPKIN_CHEST))
                .add(item(AllItems.PRESENT))
                .add(item(AllItems.BAMBOO_CHEST))
                .add(item(AllItems.MOSS_CHEST));
        tagMaker.apply(ItemTags.PIGLIN_LOVED)
                .add(item(AllItems.WOOD_TO_GOLD_CONVERSION_KIT))
                .add(item(AllItems.COPPER_TO_GOLD_CONVERSION_KIT))
                .add(item(AllItems.IRON_TO_GOLD_CONVERSION_KIT))
                .add(item(AllItems.GOLD_TO_DIAMOND_CONVERSION_KIT))
                .add(item(AllItems.GOLD_TO_OBSIDIAN_CONVERSION_KIT))
                .add(item(AllItems.GOLD_TO_NETHERITE_CONVERSION_KIT))
                .add(item(AllItems.GOLD_BARREL))
                .add(item(AllItems.GOLD_CHEST))
                .add(item(AllItems.GOLD_CHEST_MINECART))
                .add(item(AllItems.OLD_GOLD_CHEST))
                .add(item(AllItems.GOLD_MINI_CHEST))
                .add(item(AllItems.GOLD_MINI_BARREL));
    }

    public static void registerEntityTypeTags(Function<TagKey<EntityType<?>>, TagsProvider.TagAppender<EntityType<?>>> tagMaker) {
        tagMaker.apply(ModTags.Entities.ES_CHEST_MINECARTS)
                .addTag(ModTags.Entities.ES_WOODEN_CHEST_MINECARTS)
                .add(entityType(ModEntityTypes.IRON_CHEST_MINECART))
                .add(entityType(ModEntityTypes.GOLD_CHEST_MINECART))
                .add(entityType(ModEntityTypes.DIAMOND_CHEST_MINECART))
                .add(entityType(ModEntityTypes.OBSIDIAN_CHEST_MINECART))
                .add(entityType(ModEntityTypes.NETHERITE_CHEST_MINECART));
    }
}

