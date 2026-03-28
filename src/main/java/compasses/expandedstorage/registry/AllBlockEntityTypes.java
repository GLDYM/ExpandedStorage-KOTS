package compasses.expandedstorage.registry;

import compasses.expandedstorage.CommonMain;
import compasses.expandedstorage.misc.Utils;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;

public final class AllBlockEntityTypes {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, compasses.expandedstorage.ForgeMain.MOD_ID);
    private static final Map<ResourceLocation, RegistryObject<BlockEntityType<?>>> REGISTRY_OBJECTS = new LinkedHashMap<>();
    private static boolean initialized;

    private AllBlockEntityTypes() {
    }

    public static void register(IEventBus modEventBus) {
        if (!initialized) {
            REGISTRY_OBJECTS.put(CommonMain.CHEST_OBJECT_TYPE, BLOCK_ENTITY_TYPES.register(CommonMain.CHEST_OBJECT_TYPE.getPath(), ModRegistry::chestBlockEntityType));
            REGISTRY_OBJECTS.put(CommonMain.OLD_CHEST_OBJECT_TYPE, BLOCK_ENTITY_TYPES.register(CommonMain.OLD_CHEST_OBJECT_TYPE.getPath(), ModRegistry::oldChestBlockEntityType));
            REGISTRY_OBJECTS.put(CommonMain.BARREL_OBJECT_TYPE, BLOCK_ENTITY_TYPES.register(CommonMain.BARREL_OBJECT_TYPE.getPath(), ModRegistry::barrelBlockEntityType));
            REGISTRY_OBJECTS.put(CommonMain.MINI_STORAGE_OBJECT_TYPE, BLOCK_ENTITY_TYPES.register(CommonMain.MINI_STORAGE_OBJECT_TYPE.getPath(), ModRegistry::miniStorageBlockEntityType));
            initialized = true;
        }
        BLOCK_ENTITY_TYPES.register(modEventBus);
    }
}

