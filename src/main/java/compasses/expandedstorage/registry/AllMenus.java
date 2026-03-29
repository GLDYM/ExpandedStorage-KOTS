package compasses.expandedstorage.registry;

import compasses.expandedstorage.ExpandedStorage;
import compasses.expandedstorage.client.helpers.InventoryOpeningApi;
import compasses.expandedstorage.misc.Utils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public final class AllMenus {
    private AllMenus() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener((RegisterEvent event) ->
                event.register(ForgeRegistries.Keys.MENU_TYPES, helper ->
                helper.register(ExpandedStorage.id("handler_type"), InventoryOpeningApi.screenHandlerType())));
    }
}

