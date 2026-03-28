package compasses.expandedstorage.registry;

import compasses.expandedstorage.CommonMain;
import compasses.expandedstorage.block.OpenableBlock;
import compasses.expandedstorage.block.entity.BarrelBlockEntity;
import compasses.expandedstorage.block.entity.ChestBlockEntity;
import compasses.expandedstorage.block.entity.MiniStorageBlockEntity;
import compasses.expandedstorage.block.entity.OldChestBlockEntity;
import compasses.expandedstorage.entity.ChestMinecart;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ModRegistry {
    private static List<ResourceLocation> stats = List.of();
    private static Map<ResourceLocation, OpenableBlock> blocks = Map.of();
    private static Map<ResourceLocation, Item> items = Map.of();
    private static Map<ResourceLocation, EntityType<?>> entityTypes = Map.of();
    private static List<EntityType<ChestMinecart>> chestMinecartEntityTypes = List.of();
    private static BlockEntityType<ChestBlockEntity> chestBlockEntityType;
    private static BlockEntityType<OldChestBlockEntity> oldChestBlockEntityType;
    private static BlockEntityType<BarrelBlockEntity> barrelBlockEntityType;
    private static BlockEntityType<MiniStorageBlockEntity> miniStorageBlockEntityType;

    private ModRegistry() {
    }

    public static void register(IEventBus modEventBus) {
        CommonMain.constructContent();

        AllMenus.register(modEventBus);
        AllStats.register(modEventBus);
        AllBlocks.register(modEventBus);
        AllItems.register(modEventBus);
        AllBlockEntityTypes.register(modEventBus);
        AllEntityTypes.register(modEventBus);
        AllCreativeTabs.register(modEventBus);
    }

    public static void resetContent() {
        stats = List.of();
        blocks = Map.of();
        items = Map.of();
        entityTypes = Map.of();
        chestMinecartEntityTypes = List.of();
        chestBlockEntityType = null;
        oldChestBlockEntityType = null;
        barrelBlockEntityType = null;
        miniStorageBlockEntityType = null;
    }

    public static void setStats(List<ResourceLocation> values) {
        stats = List.copyOf(values);
    }

    public static void addItems(Map<ResourceLocation, ? extends Item> values) {
        items = append(items, values);
    }

    public static void addBlocks(Map<ResourceLocation, ? extends OpenableBlock> values) {
        blocks = append(blocks, values);
    }

    public static void addEntityTypes(Map<ResourceLocation, ? extends EntityType<?>> values) {
        entityTypes = append(entityTypes, values);
    }

    public static void setChestMinecartEntityTypes(List<EntityType<ChestMinecart>> values) {
        chestMinecartEntityTypes = List.copyOf(values);
    }

    public static void setChestBlockEntityType(BlockEntityType<ChestBlockEntity> value) {
        chestBlockEntityType = value;
    }

    public static void setOldChestBlockEntityType(BlockEntityType<OldChestBlockEntity> value) {
        oldChestBlockEntityType = value;
    }

    public static void setBarrelBlockEntityType(BlockEntityType<BarrelBlockEntity> value) {
        barrelBlockEntityType = value;
    }

    public static void setMiniStorageBlockEntityType(BlockEntityType<MiniStorageBlockEntity> value) {
        miniStorageBlockEntityType = value;
    }

    public static List<ResourceLocation> stats() {
        assertInitialized();
        return stats;
    }

    public static Map<ResourceLocation, OpenableBlock> blocks() {
        assertInitialized();
        return blocks;
    }

    public static Map<ResourceLocation, Item> items() {
        assertInitialized();
        return items;
    }

    public static Map<ResourceLocation, EntityType<?>> entityTypes() {
        assertInitialized();
        return entityTypes;
    }

    public static List<EntityType<ChestMinecart>> chestMinecartEntityTypes() {
        assertInitialized();
        return chestMinecartEntityTypes;
    }

    public static BlockEntityType<ChestBlockEntity> chestBlockEntityType() {
        assertInitialized();
        return chestBlockEntityType;
    }

    public static BlockEntityType<OldChestBlockEntity> oldChestBlockEntityType() {
        assertInitialized();
        return oldChestBlockEntityType;
    }

    public static BlockEntityType<BarrelBlockEntity> barrelBlockEntityType() {
        assertInitialized();
        return barrelBlockEntityType;
    }

    public static BlockEntityType<MiniStorageBlockEntity> miniStorageBlockEntityType() {
        assertInitialized();
        return miniStorageBlockEntityType;
    }

    private static void assertInitialized() {
        if (chestBlockEntityType == null) {
            throw new IllegalStateException("ModRegistry.register() must run before querying content.");
        }
    }

    private static <T> Map<ResourceLocation, T> append(Map<ResourceLocation, T> original, Map<ResourceLocation, ? extends T> values) {
        Map<ResourceLocation, T> merged = new LinkedHashMap<>(original.size() + values.size());
        merged.putAll(original);
        for (Map.Entry<ResourceLocation, ? extends T> entry : values.entrySet()) {
            merged.put(entry.getKey(), entry.getValue());
        }
        return merged;
    }
}

