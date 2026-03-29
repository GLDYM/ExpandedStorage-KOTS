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
            AllItems.WOOD_TO_COPPER_CONVERSION_KIT_REGISTRY.get(),
            AllItems.WOOD_TO_IRON_CONVERSION_KIT_REGISTRY.get(),
            AllItems.WOOD_TO_GOLD_CONVERSION_KIT_REGISTRY.get(),
            AllItems.WOOD_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get(),
            AllItems.WOOD_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(),
            AllItems.WOOD_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get(),
            AllItems.COPPER_TO_IRON_CONVERSION_KIT_REGISTRY.get(),
            AllItems.COPPER_TO_GOLD_CONVERSION_KIT_REGISTRY.get(),
            AllItems.COPPER_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get(),
            AllItems.COPPER_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(),
            AllItems.COPPER_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get(),
            AllItems.IRON_TO_GOLD_CONVERSION_KIT_REGISTRY.get(),
            AllItems.IRON_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get(),
            AllItems.IRON_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(),
            AllItems.IRON_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get(),
            AllItems.GOLD_TO_DIAMOND_CONVERSION_KIT_REGISTRY.get(),
            AllItems.GOLD_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(),
            AllItems.GOLD_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get(),
            AllItems.DIAMOND_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY.get(),
            AllItems.DIAMOND_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get(),
            AllItems.OBSIDIAN_TO_NETHERITE_CONVERSION_KIT_REGISTRY.get(),
            AllItems.WOOD_CHEST_REGISTRY.get(),
            AllItems.PUMPKIN_CHEST_REGISTRY.get(),
            AllItems.PRESENT_REGISTRY.get(),
            AllItems.BAMBOO_CHEST_REGISTRY.get(),
            AllItems.MOSS_CHEST_REGISTRY.get(),
            AllItems.IRON_CHEST_REGISTRY.get(),
            AllItems.GOLD_CHEST_REGISTRY.get(),
            AllItems.DIAMOND_CHEST_REGISTRY.get(),
            AllItems.OBSIDIAN_CHEST_REGISTRY.get(),
            AllItems.NETHERITE_CHEST_REGISTRY.get(),
            AllItems.WOOD_CHEST_MINECART_REGISTRY.get(),
            AllItems.PUMPKIN_CHEST_MINECART_REGISTRY.get(),
            AllItems.PRESENT_MINECART_REGISTRY.get(),
            AllItems.BAMBOO_CHEST_MINECART_REGISTRY.get(),
            AllItems.MOSS_CHEST_MINECART_REGISTRY.get(),
            AllItems.IRON_CHEST_MINECART_REGISTRY.get(),
            AllItems.GOLD_CHEST_MINECART_REGISTRY.get(),
            AllItems.DIAMOND_CHEST_MINECART_REGISTRY.get(),
            AllItems.OBSIDIAN_CHEST_MINECART_REGISTRY.get(),
            AllItems.NETHERITE_CHEST_MINECART_REGISTRY.get(),
            AllItems.OLD_WOOD_CHEST_REGISTRY.get(),
            AllItems.OLD_IRON_CHEST_REGISTRY.get(),
            AllItems.OLD_GOLD_CHEST_REGISTRY.get(),
            AllItems.OLD_DIAMOND_CHEST_REGISTRY.get(),
            AllItems.OLD_OBSIDIAN_CHEST_REGISTRY.get(),
            AllItems.OLD_NETHERITE_CHEST_REGISTRY.get(),
            AllItems.COPPER_BARREL_REGISTRY.get(),
            AllItems.EXPOSED_COPPER_BARREL_REGISTRY.get(),
            AllItems.WEATHERED_COPPER_BARREL_REGISTRY.get(),
            AllItems.OXIDIZED_COPPER_BARREL_REGISTRY.get(),
            AllItems.WAXED_COPPER_BARREL_REGISTRY.get(),
            AllItems.WAXED_EXPOSED_COPPER_BARREL_REGISTRY.get(),
            AllItems.WAXED_WEATHERED_COPPER_BARREL_REGISTRY.get(),
            AllItems.WAXED_OXIDIZED_COPPER_BARREL_REGISTRY.get(),
            AllItems.IRON_BARREL_REGISTRY.get(),
            AllItems.GOLD_BARREL_REGISTRY.get(),
            AllItems.DIAMOND_BARREL_REGISTRY.get(),
            AllItems.OBSIDIAN_BARREL_REGISTRY.get(),
            AllItems.NETHERITE_BARREL_REGISTRY.get()
    };

    private static final Item[] SPARROW_ITEMS = {
            AllItems.VANILLA_WOOD_MINI_CHEST_REGISTRY.get(),
            AllItems.WOOD_MINI_CHEST_REGISTRY.get(),
            AllItems.PUMPKIN_MINI_CHEST_REGISTRY.get(),
            AllItems.RED_MINI_PRESENT_REGISTRY.get(),
            AllItems.WHITE_MINI_PRESENT_REGISTRY.get(),
            AllItems.CANDY_CANE_MINI_PRESENT_REGISTRY.get(),
            AllItems.GREEN_MINI_PRESENT_REGISTRY.get(),
            AllItems.LAVENDER_MINI_PRESENT_REGISTRY.get(),
            AllItems.PINK_AMETHYST_MINI_PRESENT_REGISTRY.get(),
            AllItems.IRON_MINI_CHEST_REGISTRY.get(),
            AllItems.GOLD_MINI_CHEST_REGISTRY.get(),
            AllItems.DIAMOND_MINI_CHEST_REGISTRY.get(),
            AllItems.OBSIDIAN_MINI_CHEST_REGISTRY.get(),
            AllItems.NETHERITE_MINI_CHEST_REGISTRY.get(),
            AllItems.MINI_BARREL_REGISTRY.get(),
            AllItems.COPPER_MINI_BARREL_REGISTRY.get(),
            AllItems.EXPOSED_COPPER_MINI_BARREL_REGISTRY.get(),
            AllItems.WEATHERED_COPPER_MINI_BARREL_REGISTRY.get(),
            AllItems.OXIDIZED_COPPER_MINI_BARREL_REGISTRY.get(),
            AllItems.WAXED_COPPER_MINI_BARREL_REGISTRY.get(),
            AllItems.WAXED_EXPOSED_COPPER_MINI_BARREL_REGISTRY.get(),
            AllItems.WAXED_WEATHERED_COPPER_MINI_BARREL_REGISTRY.get(),
            AllItems.WAXED_OXIDIZED_COPPER_MINI_BARREL_REGISTRY.get(),
            AllItems.IRON_MINI_BARREL_REGISTRY.get(),
            AllItems.GOLD_MINI_BARREL_REGISTRY.get(),
            AllItems.DIAMOND_MINI_BARREL_REGISTRY.get(),
            AllItems.OBSIDIAN_MINI_BARREL_REGISTRY.get(),
            AllItems.NETHERITE_MINI_BARREL_REGISTRY.get()
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
        ItemStack stack = new ItemStack(AllItems.STORAGE_MUTATOR_REGISTRY.get());
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

