package compasses.expandedstorage.client.registry;

import compasses.expandedstorage.client.gui.PickScreen;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;

public final class AllClientBootstrap {
    private AllClientBootstrap() {
    }

    public static void register() {
        ChestTextureRegistry.register();
        ModLoadingContext.get().getActiveContainer().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((client, screen) -> new PickScreen(screen)));
    }
}

