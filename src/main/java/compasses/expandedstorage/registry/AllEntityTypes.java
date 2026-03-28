package compasses.expandedstorage.registry;

import compasses.expandedstorage.misc.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;

public final class AllEntityTypes {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, compasses.expandedstorage.ForgeMain.MOD_ID);
    private static final Map<ResourceLocation, RegistryObject<EntityType<?>>> REGISTRY_OBJECTS = new LinkedHashMap<>();
    private static boolean initialized;

    private AllEntityTypes() {
    }

    public static void register(IEventBus modEventBus) {
        if (!initialized) {
            for (Map.Entry<ResourceLocation, EntityType<?>> entry : ModRegistry.entityTypes().entrySet()) {
                ResourceLocation id = entry.getKey();
                EntityType<?> entityType = entry.getValue();
                REGISTRY_OBJECTS.put(id, ENTITY_TYPES.register(id.getPath(), () -> entityType));
            }
            initialized = true;
        }
        ENTITY_TYPES.register(modEventBus);
    }
}

