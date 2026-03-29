package compasses.expandedstorage.client.registry;

import compasses.expandedstorage.client.gui.AbstractScreen;
import compasses.expandedstorage.client.helpers.InventoryOpeningApi;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public final class AllClientScreens {
    private AllClientScreens() {
    }

    public static void register(IEventBus modBus) {
        modBus.addListener((FMLClientSetupEvent event) ->
                MenuScreens.register(InventoryOpeningApi.screenHandlerType(), AbstractScreen::createScreen));
    }
}

