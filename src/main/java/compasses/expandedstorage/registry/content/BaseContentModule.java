package compasses.expandedstorage.registry.content;

import compasses.expandedstorage.item.StorageMutator;
import compasses.expandedstorage.misc.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;

final class BaseContentModule {
    record Result(Map<ResourceLocation, Item> items) {
    }

    private BaseContentModule() {
    }

    static Result create(ContentContext context) {
        Map<ResourceLocation, Item> baseItems = new LinkedHashMap<>(22);
        baseItems.put(compasses.expandedstorage.ForgeMain.id("storage_mutator"), new StorageMutator(new Item.Properties().stacksTo(1)));
        context.defineBaseItems(baseItems);
        return new Result(baseItems);
    }
}

