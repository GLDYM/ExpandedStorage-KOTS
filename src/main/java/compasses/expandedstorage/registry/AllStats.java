package compasses.expandedstorage.registry;

import compasses.expandedstorage.CommonMain;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public final class AllStats {
    private AllStats() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener((RegisterEvent event) ->
                event.register(ForgeRegistries.Keys.STAT_TYPES, helper ->
                        ModRegistry.stats().forEach(it -> Registry.register(BuiltInRegistries.CUSTOM_STAT, it, it))));
    }
}

