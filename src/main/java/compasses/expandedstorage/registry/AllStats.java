package compasses.expandedstorage.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.List;

public final class AllStats {
    private static List<ResourceLocation> stats = List.of();

    private AllStats() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener((RegisterEvent event) ->
                event.register(ForgeRegistries.Keys.STAT_TYPES, helper ->
                        stats().forEach(it -> Registry.register(BuiltInRegistries.CUSTOM_STAT, it, it))));
    }

    public static void reset() {
        stats = List.of();
    }

    public static void set(List<ResourceLocation> values) {
        stats = List.copyOf(values);
    }

    public static List<ResourceLocation> stats() {
        return stats;
    }
}

