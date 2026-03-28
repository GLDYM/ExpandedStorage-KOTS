package compasses.expandedstorage.datagen.providers;

import compasses.expandedstorage.registry.AllItems;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class ModelHelper {
    public static void registerItemModels(Consumer<Item> consumer) {
        consumer.accept(AllItems.WOOD_TO_COPPER_CONVERSION_KIT);
        consumer.accept(AllItems.WOOD_TO_IRON_CONVERSION_KIT);
        consumer.accept(AllItems.WOOD_TO_GOLD_CONVERSION_KIT);
        consumer.accept(AllItems.WOOD_TO_DIAMOND_CONVERSION_KIT);
        consumer.accept(AllItems.WOOD_TO_OBSIDIAN_CONVERSION_KIT);
        consumer.accept(AllItems.WOOD_TO_NETHERITE_CONVERSION_KIT);
        consumer.accept(AllItems.COPPER_TO_IRON_CONVERSION_KIT);
        consumer.accept(AllItems.COPPER_TO_GOLD_CONVERSION_KIT);
        consumer.accept(AllItems.COPPER_TO_DIAMOND_CONVERSION_KIT);
        consumer.accept(AllItems.COPPER_TO_OBSIDIAN_CONVERSION_KIT);
        consumer.accept(AllItems.COPPER_TO_NETHERITE_CONVERSION_KIT);
        consumer.accept(AllItems.IRON_TO_GOLD_CONVERSION_KIT);
        consumer.accept(AllItems.IRON_TO_DIAMOND_CONVERSION_KIT);
        consumer.accept(AllItems.IRON_TO_OBSIDIAN_CONVERSION_KIT);
        consumer.accept(AllItems.IRON_TO_NETHERITE_CONVERSION_KIT);
        consumer.accept(AllItems.GOLD_TO_DIAMOND_CONVERSION_KIT);
        consumer.accept(AllItems.GOLD_TO_OBSIDIAN_CONVERSION_KIT);
        consumer.accept(AllItems.GOLD_TO_NETHERITE_CONVERSION_KIT);
        consumer.accept(AllItems.DIAMOND_TO_OBSIDIAN_CONVERSION_KIT);
        consumer.accept(AllItems.DIAMOND_TO_NETHERITE_CONVERSION_KIT);
        consumer.accept(AllItems.OBSIDIAN_TO_NETHERITE_CONVERSION_KIT);
    }
}

