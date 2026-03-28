package compasses.expandedstorage.registry;

import compasses.expandedstorage.item.MutationMode;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public final class DisplayItems {
    private static final Item[] DEFAULT_ITEMS = {
            AllItems.WOOD_TO_COPPER_CONVERSION_KIT,
            AllItems.WOOD_TO_IRON_CONVERSION_KIT,
            AllItems.WOOD_TO_GOLD_CONVERSION_KIT,
            AllItems.WOOD_TO_DIAMOND_CONVERSION_KIT,
            AllItems.WOOD_TO_OBSIDIAN_CONVERSION_KIT,
            AllItems.WOOD_TO_NETHERITE_CONVERSION_KIT,
            AllItems.COPPER_TO_IRON_CONVERSION_KIT,
            AllItems.COPPER_TO_GOLD_CONVERSION_KIT,
            AllItems.COPPER_TO_DIAMOND_CONVERSION_KIT,
            AllItems.COPPER_TO_OBSIDIAN_CONVERSION_KIT,
            AllItems.COPPER_TO_NETHERITE_CONVERSION_KIT,
            AllItems.IRON_TO_GOLD_CONVERSION_KIT,
            AllItems.IRON_TO_DIAMOND_CONVERSION_KIT,
            AllItems.IRON_TO_OBSIDIAN_CONVERSION_KIT,
            AllItems.IRON_TO_NETHERITE_CONVERSION_KIT,
            AllItems.GOLD_TO_DIAMOND_CONVERSION_KIT,
            AllItems.GOLD_TO_OBSIDIAN_CONVERSION_KIT,
            AllItems.GOLD_TO_NETHERITE_CONVERSION_KIT,
            AllItems.DIAMOND_TO_OBSIDIAN_CONVERSION_KIT,
            AllItems.DIAMOND_TO_NETHERITE_CONVERSION_KIT,
            AllItems.OBSIDIAN_TO_NETHERITE_CONVERSION_KIT,
            AllItems.WOOD_CHEST,
            AllItems.PUMPKIN_CHEST,
            AllItems.PRESENT,
            AllItems.BAMBOO_CHEST,
            AllItems.MOSS_CHEST,
            AllItems.IRON_CHEST,
            AllItems.GOLD_CHEST,
            AllItems.DIAMOND_CHEST,
            AllItems.OBSIDIAN_CHEST,
            AllItems.NETHERITE_CHEST,
            AllItems.WOOD_CHEST_MINECART,
            AllItems.PUMPKIN_CHEST_MINECART,
            AllItems.PRESENT_MINECART,
            AllItems.BAMBOO_CHEST_MINECART,
            AllItems.MOSS_CHEST_MINECART,
            AllItems.IRON_CHEST_MINECART,
            AllItems.GOLD_CHEST_MINECART,
            AllItems.DIAMOND_CHEST_MINECART,
            AllItems.OBSIDIAN_CHEST_MINECART,
            AllItems.NETHERITE_CHEST_MINECART,
            AllItems.OLD_WOOD_CHEST,
            AllItems.OLD_IRON_CHEST,
            AllItems.OLD_GOLD_CHEST,
            AllItems.OLD_DIAMOND_CHEST,
            AllItems.OLD_OBSIDIAN_CHEST,
            AllItems.OLD_NETHERITE_CHEST,
            AllItems.COPPER_BARREL,
            AllItems.EXPOSED_COPPER_BARREL,
            AllItems.WEATHERED_COPPER_BARREL,
            AllItems.OXIDIZED_COPPER_BARREL,
            AllItems.WAXED_COPPER_BARREL,
            AllItems.WAXED_EXPOSED_COPPER_BARREL,
            AllItems.WAXED_WEATHERED_COPPER_BARREL,
            AllItems.WAXED_OXIDIZED_COPPER_BARREL,
            AllItems.IRON_BARREL,
            AllItems.GOLD_BARREL,
            AllItems.DIAMOND_BARREL,
            AllItems.OBSIDIAN_BARREL,
            AllItems.NETHERITE_BARREL
    };

    private static final Item[] SPARROW_ITEMS = {
            AllItems.VANILLA_WOOD_MINI_CHEST,
            AllItems.WOOD_MINI_CHEST,
            AllItems.PUMPKIN_MINI_CHEST,
            AllItems.RED_MINI_PRESENT,
            AllItems.WHITE_MINI_PRESENT,
            AllItems.CANDY_CANE_MINI_PRESENT,
            AllItems.GREEN_MINI_PRESENT,
            AllItems.LAVENDER_MINI_PRESENT,
            AllItems.PINK_AMETHYST_MINI_PRESENT,
            AllItems.IRON_MINI_CHEST,
            AllItems.GOLD_MINI_CHEST,
            AllItems.DIAMOND_MINI_CHEST,
            AllItems.OBSIDIAN_MINI_CHEST,
            AllItems.NETHERITE_MINI_CHEST,
            AllItems.MINI_BARREL,
            AllItems.COPPER_MINI_BARREL,
            AllItems.EXPOSED_COPPER_MINI_BARREL,
            AllItems.WEATHERED_COPPER_MINI_BARREL,
            AllItems.OXIDIZED_COPPER_MINI_BARREL,
            AllItems.WAXED_COPPER_MINI_BARREL,
            AllItems.WAXED_EXPOSED_COPPER_MINI_BARREL,
            AllItems.WAXED_WEATHERED_COPPER_MINI_BARREL,
            AllItems.WAXED_OXIDIZED_COPPER_MINI_BARREL,
            AllItems.IRON_MINI_BARREL,
            AllItems.GOLD_MINI_BARREL,
            AllItems.DIAMOND_MINI_BARREL,
            AllItems.OBSIDIAN_MINI_BARREL,
            AllItems.NETHERITE_MINI_BARREL
    };

    private DisplayItems() {
    }

    public static void generate(Consumer<ItemStack> output) {
        for (MutationMode mode : MutationMode.values()) {
            output.accept(createMutator(mode));
        }

        output.accept(createSparrowMutator());

        for (Item item : DEFAULT_ITEMS) {
            output.accept(item.getDefaultInstance());
        }

        for (Item item : SPARROW_ITEMS) {
            output.accept(createSparrowVariant(item));
        }
    }

    private static ItemStack createMutator(MutationMode mode) {
        ItemStack stack = new ItemStack(AllItems.STORAGE_MUTATOR);
        CompoundTag tag = new CompoundTag();
        tag.putByte("mode", mode.toByte());
        stack.setTag(tag);
        return stack;
    }

    private static ItemStack createSparrowMutator() {
        ItemStack sparrowMutator = createMutator(MutationMode.SWAP_THEME);
        sparrowMutator.setHoverName(Component.literal("Sparrow").withStyle(ChatFormatting.ITALIC));
        return sparrowMutator;
    }

    private static ItemStack createSparrowVariant(Item item) {
        ItemStack stack = item.getDefaultInstance();
        CompoundTag tag = new CompoundTag();
        CompoundTag blockStateTag = new CompoundTag();
        blockStateTag.putString("sparrow", "true");
        tag.put("BlockStateTag", blockStateTag);
        stack.setTag(tag);
        return stack;
    }
}

