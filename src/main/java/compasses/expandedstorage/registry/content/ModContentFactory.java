package compasses.expandedstorage.registry.content;

import compasses.expandedstorage.CommonMain;
import compasses.expandedstorage.registry.ModRegistry;
import java.util.List;

public final class ModContentFactory {
    private ModContentFactory() {
    }

    public static void constructContent() {
        var context = new ContentContext();
        var base = BaseContentModule.create(context);
        var chests = ChestContentModule.create(context);
        var barrels = BarrelContentModule.create(context);
        var miniStorage = MiniStorageContentModule.create(context);

        ModRegistry.resetContent();
        ModRegistry.setStats(context.stats);
        ModRegistry.addItems(base.items());

        ModRegistry.addBlocks(chests.chestBlocks());
        ModRegistry.addBlocks(chests.oldChestBlocks());
        ModRegistry.addItems(chests.chestItems());
        ModRegistry.addItems(chests.chestMinecartItems());
        ModRegistry.addItems(chests.oldChestItems());
        ModRegistry.addEntityTypes(chests.chestMinecartEntityTypes());
        ModRegistry.setChestMinecartEntityTypes(List.copyOf(chests.chestMinecartEntityTypes().values()));
        ModRegistry.setChestBlockEntityType(chests.chestBlockEntityType());
        ModRegistry.setOldChestBlockEntityType(chests.oldChestBlockEntityType());

        ModRegistry.addBlocks(barrels.blocks());
        ModRegistry.addItems(barrels.items());
        ModRegistry.setBarrelBlockEntityType(barrels.blockEntityType());

        ModRegistry.addBlocks(miniStorage.blocks());
        ModRegistry.addItems(miniStorage.items());
        ModRegistry.setMiniStorageBlockEntityType(miniStorage.blockEntityType());

        ThemeSwapModule.register();
    }
}

