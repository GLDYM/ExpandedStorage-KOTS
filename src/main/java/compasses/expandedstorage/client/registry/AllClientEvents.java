package compasses.expandedstorage.client.registry;

import compasses.expandedstorage.client.gui.PageScreen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;

public final class AllClientEvents {
    private AllClientEvents() {
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOW, (ScreenEvent.Init.Post event) -> {
            if (event.getScreen() instanceof PageScreen screen) {
                screen.addPageButtons();
            }
        });
    }
}

