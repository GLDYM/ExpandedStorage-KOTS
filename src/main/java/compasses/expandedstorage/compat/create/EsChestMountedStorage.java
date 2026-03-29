package compasses.expandedstorage.compat.create;

import compasses.expandedstorage.ForgeMain;

import com.mojang.serialization.Codec;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorage;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.contraption.storage.item.chest.ChestMountedStorage;
import com.simibubi.create.api.contraption.storage.item.menu.StorageInteractionWrapper;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.foundation.item.ItemHelper;
import compasses.expandedstorage.api.EsChestType;
import compasses.expandedstorage.block.AbstractChestBlock;
import compasses.expandedstorage.block.OpenableBlock;
import compasses.expandedstorage.block.entity.extendable.InventoryBlockEntity;
import compasses.expandedstorage.compat.create.EsMountedStorageTypes;
import compasses.expandedstorage.inventory.handler.AbstractHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;


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

    @Override
    public boolean handleInteraction(ServerPlayer player, Contraption contraption, StructureBlockInfo info) {
        IItemHandlerModifiable handler = getHandlerForMenu(info, contraption);
        if (handler == null) return false;

        Predicate<Player> stillValid = p -> {
            Vec3 local = Vec3.atCenterOf(info.pos());
            Vec3 world = contraption.entity.toGlobalVector(local, 0);
            return isMenuValid(player, contraption, world);
        };
        Container container = new StorageInteractionWrapper(handler, stillValid, p -> {
            Vec3 local = Vec3.atCenterOf(info.pos());
            Vec3 world = contraption.entity.toGlobalVector(local, 0);
            playClosingSound(player.serverLevel(), world);
        });

        Component title = getMenuName(info, contraption);
        ResourceLocation forcedScreenType = info.state().getBlock() instanceof OpenableBlock ? 
                ((OpenableBlock)info.state().getBlock()).getForcedScreenType() : null;


        ForgeMain.openScreenHandler(player, container, title, forcedScreenType);
        Vec3 worldPos = contraption.entity.toGlobalVector(Vec3.atCenterOf(info.pos()), 0);
        playOpeningSound(player.serverLevel(), worldPos);
        return true;
    }
}




