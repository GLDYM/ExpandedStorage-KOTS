package compasses.expandedstorage.datagen.providers;

import compasses.expandedstorage.misc.Utils;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import compasses.expandedstorage.ExpandedStorage;

public final class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, ExpandedStorage.MOD_ID, fileHelper);
    }

    @Override
    protected void registerModels() {
        ModelHelper.registerItemModels(this::simple);

        //this.chest(AllItems.WOOD_CHEST_REGISTRY.get());
        //this.chest(AllItems.PUMPKIN_CHEST_REGISTRY.get());
        //this.chest(AllItems.PRESENT_REGISTRY.get());
        //this.chest(AllItems.IRON_CHEST_REGISTRY.get());
        //this.chest(AllItems.GOLD_CHEST_REGISTRY.get());
        //this.chest(AllItems.DIAMOND_CHEST_REGISTRY.get());
        //this.chest(AllItems.OBSIDIAN_CHEST_REGISTRY.get());
        //this.chest(AllItems.NETHERITE_CHEST_REGISTRY.get());

        //this.oldChest(AllItems.OLD_WOOD_CHEST_REGISTRY.get());
        //this.oldChest(AllItems.OLD_IRON_CHEST_REGISTRY.get());
        //this.oldChest(AllItems.OLD_GOLD_CHEST_REGISTRY.get());
        //this.oldChest(AllItems.OLD_DIAMOND_CHEST_REGISTRY.get());
        //this.oldChest(AllItems.OLD_OBSIDIAN_CHEST_REGISTRY.get());
        //this.oldChest(AllItems.OLD_NETHERITE_CHEST_REGISTRY.get());

        //this.barrel(AllItems.IRON_BARREL_REGISTRY.get());
        //this.barrel(AllItems.GOLD_BARREL_REGISTRY.get());
        //this.barrel(AllItems.DIAMOND_BARREL_REGISTRY.get());
        //this.barrel(AllItems.OBSIDIAN_BARREL_REGISTRY.get());
        //this.barrel(AllItems.NETHERITE_BARREL_REGISTRY.get());
    }

    @SuppressWarnings("ConstantConditions")
    private void simple(Item item) {
        String itemId = ForgeRegistries.ITEMS.getKey(item).getPath();
        this.withExistingParent(itemId, mcLoc("item/generated")).texture("layer0", "item/" + itemId);
    }

//    @SuppressWarnings("ConstantConditions")
//    private void chest(Item item) {
//        this.withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(), mcLoc("item/chest"));
//    }

//    @SuppressWarnings("ConstantConditions")
//    private void oldChest(BlockItem item) {
//        this.getBuilder(ForgeRegistries.ITEMS.getKey(item).getPath()).parent(this.getExistingFile(ExpandedStorage.id("block/" + ForgeRegistries.BLOCKS.getKey(item.getBlock()).getPath() + "/single")));
//    }

//    @SuppressWarnings("ConstantConditions")
//    private void barrel(BlockItem item) {
//        this.getBuilder(ForgeRegistries.ITEMS.getKey(item).getPath()).parent(this.getExistingFile(ExpandedStorage.id("block/" + ForgeRegistries.BLOCKS.getKey(item.getBlock()).getPath())));
//    }

    @Override
    public String getName() {
        return "Expanded Storage - Item Models";
    }
}

