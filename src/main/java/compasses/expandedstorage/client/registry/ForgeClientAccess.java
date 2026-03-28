package compasses.expandedstorage.client.registry;

import compasses.expandedstorage.ForgeMain;
import net.minecraftforge.eventbus.api.IEventBus;

final class ForgeClientAccess {
    private ForgeClientAccess() {
    }

    static IEventBus modBus() {
        if (ForgeMain.modBus == null) {
            throw new IllegalStateException("ForgeMain.modBus is not initialized yet.");
        }
        return ForgeMain.modBus;
    }
}

