package compasses.expandedstorage.registry;

import net.minecraftforge.eventbus.api.IEventBus;

public final class ModRegistry {
    private ModRegistry() {
    }

    public static void register(IEventBus modEventBus) {
        AllMenus.register(modEventBus);
        AllBlocks.register(modEventBus);
        AllItems.register(modEventBus);
        AllBlockEntityTypes.register(modEventBus);
        AllEntityTypes.register(modEventBus);
        AllCreativeTabs.register(modEventBus);

        BlockMutatorBehaviours.register();
    }
}

