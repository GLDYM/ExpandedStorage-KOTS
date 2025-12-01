package compasses.expandedstorage.impl.compat.create;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorage;
import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import compasses.expandedstorage.impl.block.entity.extendable.InventoryBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import compasses.expandedstorage.impl.ForgeMain;
/**
 * MountedStorageType for ES chest blocks.
 * Registers how Create should mount ES chests into contraptions.
 */
public class EsChestMountedStorageType extends SimpleMountedStorageType<EsChestMountedStorage> {
    public EsChestMountedStorageType() {
        super(EsChestMountedStorage.CODEC);
    }

    /*
    @Override
    protected IItemHandler getHandler(BlockEntity be) {
        ForgeMain.LOGGER.info("[ES] Try Detect InventoryBlockEntity");
        if (be instanceof Container inv) {
            ForgeMain.LOGGER.info("[ES] Detect InventoryBlockEntity");
            return inv;
        }
        return null;
    }
    */

    @Override
    protected EsChestMountedStorage createStorage(IItemHandler handler) {
        return new EsChestMountedStorage(handler);
    }
}