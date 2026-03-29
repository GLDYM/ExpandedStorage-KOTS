package compasses.expandedstorage.registry.mutator;

import compasses.expandedstorage.api.EsChestType;
import compasses.expandedstorage.block.AbstractChestBlock;
import compasses.expandedstorage.block.entity.ChestBlockEntity;
import compasses.expandedstorage.block.entity.extendable.OpenableBlockEntity;
import compasses.expandedstorage.item.MutationMode;
import compasses.expandedstorage.item.ToolUsageResult;
import compasses.expandedstorage.registry.BlockMutatorBehaviours;
import compasses.expandedstorage.misc.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public final class ChestMutatorBehaviourRegistry {
    private ChestMutatorBehaviourRegistry() {
    }

    public static void register() {
        registerMutationBehaviours();
    }

    private static void registerMutationBehaviours() {
        BlockMutatorBehaviours.register(ChestMutatorBehaviourRegistry::isChestBlock, MutationMode.MERGE, ChestMutatorBehaviourRegistry::mergeChestMutation);
        BlockMutatorBehaviours.register(ChestMutatorBehaviourRegistry::isChestBlock, MutationMode.SPLIT, ChestMutatorBehaviourRegistry::splitChestMutation);
        BlockMutatorBehaviours.register(ChestMutatorBehaviourRegistry::isChestBlock, MutationMode.ROTATE, ChestMutatorBehaviourRegistry::rotateChestMutation);
    }

    private static boolean isChestBlock(Block block) {
        return block instanceof AbstractChestBlock;
    }

    private static ToolUsageResult mergeChestMutation(UseOnContext context, Level level, BlockState state, BlockPos pos, ItemStack stack) {
        Player player = context.getPlayer();
        if (player == null) {
            return ToolUsageResult.fail();
        }
        if (state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE) != EsChestType.SINGLE) {
            return ToolUsageResult.fail();
        }

        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("pos")) {
            if (!level.isClientSide()) {
                tag.put("pos", NbtUtils.writeBlockPos(pos));
                player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_start", Utils.ALT_USE), true);
            }
            return ToolUsageResult.fastSuccess();
        }

        BlockPos otherPos = NbtUtils.readBlockPos(tag.getCompound("pos"));
        BlockState otherState = level.getBlockState(otherPos);
        BlockPos delta = otherPos.subtract(pos);
        Direction direction = Direction.fromDelta(delta.getX(), delta.getY(), delta.getZ());
        if (direction == null) {
            player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_not_adjacent"), true);
            tag.remove("pos");
            return ToolUsageResult.fail();
        }
        if (state.getBlock() != otherState.getBlock()) {
            player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_wrong_block"), true);
            tag.remove("pos");
            return ToolUsageResult.fail();
        }
        if (otherState.getValue(AbstractChestBlock.CURSED_CHEST_TYPE) != EsChestType.SINGLE) {
            player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_already_double_chest"), true);
            tag.remove("pos");
            return ToolUsageResult.fail();
        }
        if (state.getValue(BlockStateProperties.HORIZONTAL_FACING) != otherState.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
            player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_wrong_facing"), true);
            tag.remove("pos");
            return ToolUsageResult.fail();
        }

        boolean firstIsDinnerbone = level.getBlockEntity(pos) instanceof OpenableBlockEntity blockEntity && blockEntity.isDinnerbone();
        boolean secondIsDinnerbone = level.getBlockEntity(otherPos) instanceof OpenableBlockEntity blockEntity && blockEntity.isDinnerbone();
        if (firstIsDinnerbone != secondIsDinnerbone) {
            player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_wrong_block"), true);
            tag.remove("pos");
            return ToolUsageResult.fail();
        }

        if (!level.isClientSide()) {
            EsChestType chestType = AbstractChestBlock.getChestType(state.getValue(BlockStateProperties.HORIZONTAL_FACING), direction);
            level.setBlockAndUpdate(pos, state.setValue(AbstractChestBlock.CURSED_CHEST_TYPE, chestType));
            tag.remove("pos");
            player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_end"), true);
        }
        return ToolUsageResult.slowSuccess();
    }

    private static ToolUsageResult splitChestMutation(UseOnContext context, Level level, BlockState state, BlockPos pos, ItemStack stack) {
        if (state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE) == EsChestType.SINGLE) {
            return ToolUsageResult.fail();
        }
        if (!level.isClientSide()) {
            BlockPos otherPos = pos.relative(AbstractChestBlock.getDirectionToAttached(state));
            level.setBlockAndUpdate(pos, state.setValue(AbstractChestBlock.CURSED_CHEST_TYPE, EsChestType.SINGLE));
            level.setBlockAndUpdate(otherPos, state.setValue(AbstractChestBlock.CURSED_CHEST_TYPE, EsChestType.SINGLE));
        }
        return ToolUsageResult.slowSuccess();
    }

    private static ToolUsageResult rotateChestMutation(UseOnContext context, Level level, BlockState state, BlockPos pos, ItemStack stack) {
        if (level.isClientSide()) {
            return ToolUsageResult.slowSuccess();
        }

        EsChestType chestType = state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE);
        if (chestType == EsChestType.SINGLE) {
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING).getClockWise()));
            return ToolUsageResult.slowSuccess();
        }

        BlockPos otherPos = pos.relative(AbstractChestBlock.getDirectionToAttached(state));
        BlockState otherState = level.getBlockState(otherPos);
        if (chestType == EsChestType.TOP || chestType == EsChestType.BOTTOM) {
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING).getClockWise()));
            level.setBlockAndUpdate(otherPos, otherState.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING).getClockWise()));
            return ToolUsageResult.slowSuccess();
        }

        level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite()).setValue(AbstractChestBlock.CURSED_CHEST_TYPE, state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE).getOpposite()));
        level.setBlockAndUpdate(otherPos, otherState.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite()).setValue(AbstractChestBlock.CURSED_CHEST_TYPE, otherState.getValue(AbstractChestBlock.CURSED_CHEST_TYPE).getOpposite()));
        return ToolUsageResult.slowSuccess();
    }
}

