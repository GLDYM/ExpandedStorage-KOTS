package compasses.expandedstorage.client.registry;

import net.minecraftforge.eventbus.api.IEventBus;

public final class ClientRegistry {
    private ClientRegistry() {
    }

    public static void register(IEventBus modBus) {
        AllClientBootstrap.register();
        AllClientEvents.register();
        AllClientKeyMappings.register(modBus);
        AllClientScreens.register(modBus);
        AllClientItemProperties.register(modBus);
        AllClientRenderers.register(modBus);
        AllClientLayers.register(modBus);
    }
}

