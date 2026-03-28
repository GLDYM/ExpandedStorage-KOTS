package compasses.expandedstorage.client.registry;

import compasses.expandedstorage.client.gui.AbstractScreen;
import compasses.expandedstorage.ForgeMain;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public final class AllClientScreens {
    private AllClientScreens() {
    }

    public static void register() {
        ForgeClientAccess.modBus().addListener((FMLClientSetupEvent event) ->
                MenuScreens.register(ForgeMain.screenHandlerType(), AbstractScreen::createScreen));
    }
}

