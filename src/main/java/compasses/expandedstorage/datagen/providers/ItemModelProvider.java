package compasses.expandedstorage.datagen.providers;

import compasses.expandedstorage.misc.Utils;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public final class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, compasses.expandedstorage.ForgeMain.MOD_ID, fileHelper);
    }

    @Override
    protected void registerModels() {
        ModelHelper.registerItemModels(this::simple);

        //this.chest(AllItems.WOOD_CHEST);
        //this.chest(AllItems.PUMPKIN_CHEST);
        //this.chest(AllItems.PRESENT);
        //this.chest(AllItems.IRON_CHEST);
        //this.chest(AllItems.GOLD_CHEST);
        //this.chest(AllItems.DIAMOND_CHEST);
        //this.chest(AllItems.OBSIDIAN_CHEST);
        //this.chest(AllItems.NETHERITE_CHEST);

        //this.oldChest(AllItems.OLD_WOOD_CHEST);
        //this.oldChest(AllItems.OLD_IRON_CHEST);
        //this.oldChest(AllItems.OLD_GOLD_CHEST);
        //this.oldChest(AllItems.OLD_DIAMOND_CHEST);
        //this.oldChest(AllItems.OLD_OBSIDIAN_CHEST);
        //this.oldChest(AllItems.OLD_NETHERITE_CHEST);

        //this.barrel(AllItems.IRON_BARREL);
        //this.barrel(AllItems.GOLD_BARREL);
        //this.barrel(AllItems.DIAMOND_BARREL);
        //this.barrel(AllItems.OBSIDIAN_BARREL);
        //this.barrel(AllItems.NETHERITE_BARREL);
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
//        this.getBuilder(ForgeRegistries.ITEMS.getKey(item).getPath()).parent(this.getExistingFile(compasses.expandedstorage.ForgeMain.id("block/" + ForgeRegistries.BLOCKS.getKey(item.getBlock()).getPath() + "/single")));
//    }

//    @SuppressWarnings("ConstantConditions")
//    private void barrel(BlockItem item) {
//        this.getBuilder(ForgeRegistries.ITEMS.getKey(item).getPath()).parent(this.getExistingFile(compasses.expandedstorage.ForgeMain.id("block/" + ForgeRegistries.BLOCKS.getKey(item.getBlock()).getPath())));
//    }

    @Override
    public String getName() {
        return "Expanded Storage - Item Models";
    }
}

