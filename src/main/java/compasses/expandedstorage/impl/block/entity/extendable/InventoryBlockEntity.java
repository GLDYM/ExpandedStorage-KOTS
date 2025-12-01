package compasses.expandedstorage.impl.block.entity.extendable;

import compasses.expandedstorage.impl.block.strategies.Observable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;


public abstract class InventoryBlockEntity extends OpenableBlockEntity implements WorldlyContainer {
    private int[] availableSlots;
    private ItemStackHandler itemHandler;
    private LazyOptional<IItemHandlerModifiable> lazyItemHandler;
    private Observable observable;

    public InventoryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, ResourceLocation blockId, Component defaultName, int inventorySize) {
        super(type, pos, state, blockId, defaultName);
        this.itemHandler = new ItemStackHandler(inventorySize) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
        this.lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    public int @NotNull [] getSlotsForFace(Direction side) {
        if (availableSlots == null) {
            availableSlots = IntStream.range(0, this.getContainerSize()).toArray();
        }
        return availableSlots;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction dir) {
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    @Override
    public int getContainerSize() {
        return itemHandler.getSlots();
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            if (!itemHandler.getStackInSlot(i).isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return itemHandler.getStackInSlot(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack stack = itemHandler.extractItem(slot, amount, false);
        if (!stack.isEmpty()) setChanged();
        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack current = itemHandler.getStackInSlot(slot);
        itemHandler.setStackInSlot(slot, ItemStack.EMPTY);
        return current;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        itemHandler.setStackInSlot(slot, stack);
        setChanged();
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return super.isValidAndPlayerInRange(player);
    }

    @Override
    public void clearContent() {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            itemHandler.setStackInSlot(i, ItemStack.EMPTY);
        }
    }
    
    @Override
    public void startOpen(Player player) {
        if (player.isSpectator() || observable == null) return;
        observable.playerStartViewing(player);
    }

    @Override
    public void stopOpen(Player player) {
        if (player.isSpectator() || observable == null) return;
        observable.playerStopViewing(player);
    }

    @Override
    public final WorldlyContainer getInventory() {
        return (WorldlyContainer) this;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        NonNullList<ItemStack> list = NonNullList.withSize(itemHandler.getSlots(), ItemStack.EMPTY);
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            list.set(i, itemHandler.getStackInSlot(i));
        }
        return list;
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Items", itemHandler.serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("Items"));
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }


    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    protected void setObservable(Observable observable) {
        if (this.observable == null) this.observable = observable;
    }
}

