package compasses.expandedstorage.registry;

import compasses.expandedstorage.compat.create.CreateCompat;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLLoader;

public final class ModCompat {
    private ModCompat() {
    }

    public static void register(IEventBus modBus) {
        if (FMLLoader.getLoadingModList().getModFileById("create") != null) {
            CreateCompat.register();
            CreateCompat.REGISTRATE.registerEventListeners(modBus);
        }
    }
}

