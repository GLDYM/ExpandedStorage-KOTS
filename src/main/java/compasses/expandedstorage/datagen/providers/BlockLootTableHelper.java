package compasses.expandedstorage.datagen.providers;

import compasses.expandedstorage.block.MiniStorageBlock;
import compasses.expandedstorage.registry.AllBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class BlockLootTableHelper {
    public static void registerLootTables(BiConsumer<Block, Function<Block, LootTable.Builder>> consumer, Function<Block, LootTable.Builder> lootTableBuilder) {
        consumer.accept(AllBlocks.WOOD_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.PUMPKIN_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.PRESENT_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.BAMBOO_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.MOSS_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.IRON_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.GOLD_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.DIAMOND_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.OBSIDIAN_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.NETHERITE_CHEST_REGISTRY.get(), lootTableBuilder);

        consumer.accept(AllBlocks.OLD_WOOD_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.OLD_IRON_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.OLD_GOLD_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.OLD_DIAMOND_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.OLD_OBSIDIAN_CHEST_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.OLD_NETHERITE_CHEST_REGISTRY.get(), lootTableBuilder);

        consumer.accept(AllBlocks.COPPER_BARREL_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.EXPOSED_COPPER_BARREL_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.WEATHERED_COPPER_BARREL_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.OXIDIZED_COPPER_BARREL_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.WAXED_COPPER_BARREL_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.WAXED_EXPOSED_COPPER_BARREL_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.WAXED_WEATHERED_COPPER_BARREL_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.WAXED_OXIDIZED_COPPER_BARREL_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.IRON_BARREL_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.GOLD_BARREL_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.DIAMOND_BARREL_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.OBSIDIAN_BARREL_REGISTRY.get(), lootTableBuilder);
        consumer.accept(AllBlocks.NETHERITE_BARREL_REGISTRY.get(), lootTableBuilder);

        consumer.accept(AllBlocks.VANILLA_WOOD_MINI_CHEST_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.WOOD_MINI_CHEST_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.PUMPKIN_MINI_CHEST_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.RED_MINI_PRESENT_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.WHITE_MINI_PRESENT_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.CANDY_CANE_MINI_PRESENT_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.GREEN_MINI_PRESENT_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.LAVENDER_MINI_PRESENT_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.PINK_AMETHYST_MINI_PRESENT_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.IRON_MINI_CHEST_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.GOLD_MINI_CHEST_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.DIAMOND_MINI_CHEST_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.OBSIDIAN_MINI_CHEST_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.NETHERITE_MINI_CHEST_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.COPPER_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.EXPOSED_COPPER_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.WEATHERED_COPPER_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.OXIDIZED_COPPER_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.WAXED_COPPER_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.WAXED_EXPOSED_COPPER_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.WAXED_WEATHERED_COPPER_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.WAXED_OXIDIZED_COPPER_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.IRON_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.GOLD_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.DIAMOND_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.OBSIDIAN_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
        consumer.accept(AllBlocks.NETHERITE_MINI_BARREL_REGISTRY.get(), BlockLootTableHelper::createMiniStorageDrop);
    }

    private static LootTable.Builder createMiniStorageDrop(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(
                LootItem.lootTableItem(block)
                        .apply(CopyBlockState.copyState(block).copy(MiniStorageBlock.SPARROW))
        ));
    }
}

