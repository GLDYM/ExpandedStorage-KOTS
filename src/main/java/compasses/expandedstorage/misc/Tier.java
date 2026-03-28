package compasses.expandedstorage.misc;

import compasses.expandedstorage.ForgeMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.UnaryOperator;

public enum Tier {
    WOOD(ForgeMain.id("wood"), 27, UnaryOperator.identity(), UnaryOperator.identity()),
    COPPER(ForgeMain.id("copper"), 45, BlockBehaviour.Properties::requiresCorrectToolForDrops, UnaryOperator.identity()),
    IRON(ForgeMain.id("iron"), 54, BlockBehaviour.Properties::requiresCorrectToolForDrops, UnaryOperator.identity()),
    GOLD(ForgeMain.id("gold"), 81, BlockBehaviour.Properties::requiresCorrectToolForDrops, UnaryOperator.identity()),
    DIAMOND(ForgeMain.id("diamond"), 108, BlockBehaviour.Properties::requiresCorrectToolForDrops, UnaryOperator.identity()),
    OBSIDIAN(ForgeMain.id("obsidian"), 108, BlockBehaviour.Properties::requiresCorrectToolForDrops, UnaryOperator.identity()),
    NETHERITE(ForgeMain.id("netherite"), 135, BlockBehaviour.Properties::requiresCorrectToolForDrops, Item.Properties::fireResistant);

    private final ResourceLocation id;
    private final UnaryOperator<Item.Properties> itemSettings;
    private final UnaryOperator<BlockBehaviour.Properties> blockSettings;
    private final int slots;

    Tier(ResourceLocation id, int slots, UnaryOperator<BlockBehaviour.Properties> blockSettings, UnaryOperator<Item.Properties> itemSettings) {
        this.id = id;
        this.slots = slots;
        this.blockSettings = blockSettings;
        this.itemSettings = itemSettings;
    }

    public ResourceLocation getId() {
        return id;
    }

    public UnaryOperator<Item.Properties> getItemSettings() {
        return itemSettings;
    }

    public UnaryOperator<BlockBehaviour.Properties> getBlockSettings() {
        return blockSettings;
    }

    public int getSlotCount() {
        return slots;
    }
}



