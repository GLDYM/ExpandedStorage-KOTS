package compasses.expandedstorage.registry;

import compasses.expandedstorage.registry.content.ModContentFactory;
import net.minecraftforge.eventbus.api.IEventBus;

public final class ModRegistry {
    private ModRegistry() {
    }

    public static void register(IEventBus modEventBus) {
        ModContentFactory.constructContent();

        AllMenus.register(modEventBus);
        AllStats.register(modEventBus);
        AllBlocks.register(modEventBus);
        AllItems.register(modEventBus);
        AllBlockEntityTypes.register(modEventBus);
        AllEntityTypes.register(modEventBus);
        AllCreativeTabs.register(modEventBus);
    }
}

