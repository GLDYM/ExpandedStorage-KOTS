package compasses.expandedstorage.registry.mutator;

import compasses.expandedstorage.block.MiniStorageBlock;
import compasses.expandedstorage.item.MutationMode;
import compasses.expandedstorage.item.ToolUsageResult;
import compasses.expandedstorage.registry.BlockMutatorBehaviours;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;

public final class MiniStorageMutatorBehaviourRegistry {
    private MiniStorageMutatorBehaviourRegistry() {
    }

    public static void register() {
        BlockMutatorBehaviours.register(MiniStorageMutatorBehaviourRegistry::isMiniStorageBlock, MutationMode.ROTATE, MiniStorageMutatorBehaviourRegistry::rotateMiniStorageMutation);
    }

    private static boolean isMiniStorageBlock(Block block) {
        return block instanceof MiniStorageBlock;
    }

    private static ToolUsageResult rotateMiniStorageMutation(UseOnContext context, Level level, BlockState state, BlockPos pos, ItemStack stack) {
        if (!level.isClientSide()) {
            level.setBlockAndUpdate(pos, state.rotate(level, pos, Rotation.CLOCKWISE_90));
        }
        return ToolUsageResult.slowSuccess();
    }
}

