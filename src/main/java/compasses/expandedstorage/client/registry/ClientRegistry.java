package compasses.expandedstorage.client.registry;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLLoader;

public final class ClientRegistry {
    private ClientRegistry() {
    }

    public static void register() {
        if (!FMLLoader.getDist().equals(Dist.CLIENT)) {
            return;
        }

        AllClientBootstrap.register();
        AllClientEvents.register();
        AllClientScreens.register();
        AllClientItemProperties.register();
        AllClientRenderers.register();
        AllClientLayers.register();
    }
}

