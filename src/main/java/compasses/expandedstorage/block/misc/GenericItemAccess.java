package compasses.expandedstorage.block.misc;

import compasses.expandedstorage.block.entity.extendable.OpenableBlockEntity;
import compasses.expandedstorage.block.strategies.ItemAccess;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

public class GenericItemAccess implements ItemAccess<IItemHandlerModifiable> {
    private final OpenableBlockEntity entity;
    private IItemHandlerModifiable handler = null;

    public GenericItemAccess(OpenableBlockEntity entity) {
        this.entity = entity;
    }

    @Override
    public IItemHandlerModifiable get() {
        if (handler == null) {
            handler = new InvWrapper(entity.getInventory());
        }

        return handler;
    }
}

