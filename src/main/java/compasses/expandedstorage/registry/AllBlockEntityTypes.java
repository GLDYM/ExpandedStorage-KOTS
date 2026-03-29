package compasses.expandedstorage.registry;

import compasses.expandedstorage.block.OpenableBlock;
import compasses.expandedstorage.block.entity.BarrelBlockEntity;
import compasses.expandedstorage.block.entity.ChestBlockEntity;
import compasses.expandedstorage.block.entity.MiniStorageBlockEntity;
import compasses.expandedstorage.block.entity.OldChestBlockEntity;
import compasses.expandedstorage.block.misc.BasicLockable;
import compasses.expandedstorage.block.misc.ChestItemAccess;
import compasses.expandedstorage.block.misc.GenericItemAccess;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import compasses.expandedstorage.ExpandedStorage;

public final class AllBlockEntityTypes {
    public static final ResourceLocation BARREL_OBJECT_TYPE = ExpandedStorage.id("barrel");
    public static final ResourceLocation CHEST_OBJECT_TYPE = ExpandedStorage.id("chest");
    public static final ResourceLocation OLD_CHEST_OBJECT_TYPE = ExpandedStorage.id("old_chest");
    public static final ResourceLocation MINI_STORAGE_OBJECT_TYPE = ExpandedStorage.id("mini_chest");

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ExpandedStorage.MOD_ID);
    private static final RegistryObject<BlockEntityType<ChestBlockEntity>> CHEST_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register(CHEST_OBJECT_TYPE.getPath(), AllBlockEntityTypes::createChestBlockEntityType);
    private static final RegistryObject<BlockEntityType<OldChestBlockEntity>> OLD_CHEST_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register(OLD_CHEST_OBJECT_TYPE.getPath(), AllBlockEntityTypes::createOldChestBlockEntityType);
    private static final RegistryObject<BlockEntityType<BarrelBlockEntity>> BARREL_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register(BARREL_OBJECT_TYPE.getPath(), AllBlockEntityTypes::createBarrelBlockEntityType);
    private static final RegistryObject<BlockEntityType<MiniStorageBlockEntity>> MINI_STORAGE_BLOCK_ENTITY_TYPE = BLOCK_ENTITY_TYPES.register(MINI_STORAGE_OBJECT_TYPE.getPath(), AllBlockEntityTypes::createMiniStorageBlockEntityType);

    private AllBlockEntityTypes() {
    }

    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITY_TYPES.register(modEventBus);
    }

    public static BlockEntityType<ChestBlockEntity> chestBlockEntityType() {
        return CHEST_BLOCK_ENTITY_TYPE.get();
    }

    public static BlockEntityType<OldChestBlockEntity> oldChestBlockEntityType() {
        return OLD_CHEST_BLOCK_ENTITY_TYPE.get();
    }

    public static BlockEntityType<BarrelBlockEntity> barrelBlockEntityType() {
        return BARREL_BLOCK_ENTITY_TYPE.get();
    }

    public static BlockEntityType<MiniStorageBlockEntity> miniStorageBlockEntityType() {
        return MINI_STORAGE_BLOCK_ENTITY_TYPE.get();
    }

    private static BlockEntityType<ChestBlockEntity> createChestBlockEntityType() {
        return BlockEntityType.Builder.of(
            (pos, state) -> new ChestBlockEntity(chestBlockEntityType(), pos, state, ((OpenableBlock) state.getBlock()).getBlockId(), ChestItemAccess::new, BasicLockable::new),
                AllBlocks.chestBlocksForEntityType()
        ).build(Util.fetchChoiceType(References.BLOCK_ENTITY, CHEST_OBJECT_TYPE.toString()));
    }

    private static BlockEntityType<OldChestBlockEntity> createOldChestBlockEntityType() {
        return BlockEntityType.Builder.of(
            (pos, state) -> new OldChestBlockEntity(oldChestBlockEntityType(), pos, state, ((OpenableBlock) state.getBlock()).getBlockId(), ChestItemAccess::new, BasicLockable::new),
                AllBlocks.oldChestBlocksForEntityType()
        ).build(Util.fetchChoiceType(References.BLOCK_ENTITY, OLD_CHEST_OBJECT_TYPE.toString()));
    }

    private static BlockEntityType<BarrelBlockEntity> createBarrelBlockEntityType() {
        return BlockEntityType.Builder.of(
            (pos, state) -> new BarrelBlockEntity(barrelBlockEntityType(), pos, state, ((OpenableBlock) state.getBlock()).getBlockId(), GenericItemAccess::new, BasicLockable::new),
                AllBlocks.barrelBlocksForEntityType()
        ).build(Util.fetchChoiceType(References.BLOCK_ENTITY, BARREL_OBJECT_TYPE.toString()));
    }

    private static BlockEntityType<MiniStorageBlockEntity> createMiniStorageBlockEntityType() {
        return BlockEntityType.Builder.of(
            (pos, state) -> new MiniStorageBlockEntity(miniStorageBlockEntityType(), pos, state, ((OpenableBlock) state.getBlock()).getBlockId(), GenericItemAccess::new, BasicLockable::new),
                AllBlocks.miniStorageBlocksForEntityType()
        ).build(Util.fetchChoiceType(References.BLOCK_ENTITY, MINI_STORAGE_OBJECT_TYPE.toString()));
    }
}

