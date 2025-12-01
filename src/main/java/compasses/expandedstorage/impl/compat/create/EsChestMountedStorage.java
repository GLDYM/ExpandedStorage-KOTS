package compasses.expandedstorage.impl.compat.create;

import com.mojang.serialization.Codec;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorage;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.contraption.storage.item.chest.ChestMountedStorage;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.foundation.item.ItemHelper;
import compasses.expandedstorage.api.EsChestType;
import compasses.expandedstorage.impl.block.AbstractChestBlock;
import compasses.expandedstorage.impl.block.entity.extendable.InventoryBlockEntity;
import compasses.expandedstorage.impl.compat.create.EsMountedStorageTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.InvWrapper;

import org.jetbrains.annotations.Nullable;

/**
 * Mounted storage for ES chests, handles double chest combination.
 */
public class EsChestMountedStorage extends ChestMountedStorage {
    public static final Codec<EsChestMountedStorage> CODEC =
            ChestMountedStorage.codec(EsChestMountedStorage::new);

    protected EsChestMountedStorage(MountedItemStorageType<?> type, IItemHandler handler) {
        super(type, handler);
    }

    public EsChestMountedStorage(IItemHandler handler) {
		this(EsMountedStorageTypes.ES_CHEST.get(), handler);
	}

	/*
    @Override
    protected IItemHandlerModifiable getHandlerForMenu(StructureBlockInfo info, Contraption contraption) {
        BlockState state = info.state();
        EsChestType type = state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE);
        if (type == EsChestType.SINGLE)
            return this;

        Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        Direction connectedDir = AbstractChestBlock.getDirectionToAttached(state);
        BlockPos otherPos = info.pos().relative(connectedDir);

        MountedItemStorage otherHalf = contraption.getStorage().getMountedItems().storages.get(otherPos);
        if (otherHalf == null)
            return this;

        if (AbstractChestBlock.getBlockType(type) == DoubleBlockCombiner.BlockType.FIRST) {
            return new CombinedInvWrapper(this, otherHalf);
        } else {
            return new CombinedInvWrapper(otherHalf, this);
        }
    }
	*/
}
