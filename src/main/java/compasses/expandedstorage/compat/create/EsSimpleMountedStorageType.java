package compasses.expandedstorage.compat.create;

import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorage;
import com.simibubi.create.api.contraption.storage.item.simple.SimpleMountedStorageType;
import compasses.expandedstorage.block.entity.extendable.ExposedInventoryBlockEntity;
import compasses.expandedstorage.compat.create.EsSimpleMountedStorage;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import compasses.expandedstorage.ExpandedStorage;

public class EsSimpleMountedStorageType extends SimpleMountedStorageType<EsSimpleMountedStorage> {
    public EsSimpleMountedStorageType() {
        super(EsSimpleMountedStorage.CODEC);
    }

    @Override
    protected IItemHandler getHandler(BlockEntity be) {
        if (be instanceof ExposedInventoryBlockEntity inv) {
            return new InvWrapper(inv);
        }
        return null;
    }


    @Override
    protected EsSimpleMountedStorage createStorage(IItemHandler handler) {
        return new EsSimpleMountedStorage(handler);
    }
}
