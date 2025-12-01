package compasses.expandedstorage.impl.block.entity;

import compasses.expandedstorage.api.EsChestType;
import compasses.expandedstorage.impl.block.AbstractChestBlock;
import compasses.expandedstorage.impl.block.OpenableBlock;
import compasses.expandedstorage.impl.block.entity.extendable.InventoryBlockEntity;
import compasses.expandedstorage.impl.block.entity.extendable.OpenableBlockEntity;
import compasses.expandedstorage.impl.block.misc.DoubleItemAccess;
import compasses.expandedstorage.impl.block.strategies.ItemAccess;
import compasses.expandedstorage.impl.block.strategies.Lockable;
import compasses.expandedstorage.impl.inventory.VariableSidedInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;

public class OldChestBlockEntity extends InventoryBlockEntity {
    private OldChestBlockEntity otherChest = null;
    private WorldlyContainer cachedDoubleInventory = null;
    private final LazyOptional<IItemHandlerModifiable> singleHandler;

    public OldChestBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, ResourceLocation blockId,
                               Function<OpenableBlockEntity, ItemAccess> access, Supplier<Lockable> lockable) {
        super(type, pos, state, blockId, ((OpenableBlock) state.getBlock()).getInventoryTitle(), ((OpenableBlock) state.getBlock()).getSlotCount());
        this.setItemAccess(access.apply(this));
        this.setLockable(lockable.get());
        this.singleHandler = LazyOptional.of(this::getItemHandler);
    }

    public void invalidateDoubleBlockCache() {
        otherChest = null;
        cachedDoubleInventory = null;
        this.getItemAccess().setOther(null);
    }

    public WorldlyContainer getCachedDoubleInventory() {
        return cachedDoubleInventory;
    }

    public void setCachedDoubleInventory(OldChestBlockEntity other) {
        this.cachedDoubleInventory = VariableSidedInventory.of(this.getInventory(), other.getInventory());
    }

    public OldChestBlockEntity getOtherChest() {
        return otherChest;
    }

    public void setOtherChest(@Nullable OldChestBlockEntity otherChest) {
        this.otherChest = otherChest;
    }

    public void updateOtherChest() {
        BlockState state = this.getBlockState();
        if (state.getBlock() instanceof AbstractChestBlock) {
            if (state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE) != EsChestType.SINGLE) {
                Direction dir = AbstractChestBlock.getDirectionToAttached(state);
                BlockPos otherPos = worldPosition.relative(dir);
                BlockEntity be = level.getBlockEntity(otherPos);
                if (be instanceof OldChestBlockEntity other) {
                    this.setOtherChest(other);
                    other.setOtherChest(this);
                    this.getItemAccess().setOther(other.getItemAccess());
                } else {
                    this.invalidateDoubleBlockCache();
                }
            } else {
                this.invalidateDoubleBlockCache();
            }
        }
    }

    @Override
    public DoubleItemAccess getItemAccess() {
        return (DoubleItemAccess) super.getItemAccess();
    }

    public IItemHandlerModifiable getCombinedHandler() {
        if (otherChest == null) {
            return this.getItemHandler();
        }
        BlockState state = this.getBlockState();
        if (!(state.getBlock() instanceof AbstractChestBlock)) {
            return new CombinedInvWrapper(this.getItemHandler(), otherChest.getItemHandler());
        }

        EsChestType type = state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE);
        if (AbstractChestBlock.getBlockType(type) == DoubleBlockCombiner.BlockType.FIRST) {
            return new CombinedInvWrapper(this.getItemHandler(), otherChest.getItemHandler());
        } else {
            return new CombinedInvWrapper(otherChest.getItemHandler(), this.getItemHandler());
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (otherChest == null) {
                this.updateOtherChest();
            }
            if (otherChest != null) {
                return LazyOptional.of(() -> getCombinedHandler()).cast();
            }
            return singleHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        this.updateOtherChest();
    }
}
