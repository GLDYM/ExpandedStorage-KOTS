package compasses.expandedstorage.registry.mutator;

import compasses.expandedstorage.block.BarrelBlock;
import compasses.expandedstorage.item.MutationMode;
import compasses.expandedstorage.item.ToolUsageResult;
import compasses.expandedstorage.registry.BlockMutatorBehaviours;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.ForgeRegistries;

public final class BarrelMutatorBehaviourRegistry {
    private BarrelMutatorBehaviourRegistry() {
    }

    public static void register() {
        BlockMutatorBehaviours.register(BarrelMutatorBehaviourRegistry::isBarrelBlock, MutationMode.ROTATE, BarrelMutatorBehaviourRegistry::rotateBarrelMutation);
    }

    private static boolean isBarrelBlock(Block block) {
        return block instanceof BarrelBlock
                || block instanceof net.minecraft.world.level.block.BarrelBlock
                || block.defaultBlockState().is(
                    TagKey.create(
                        ForgeRegistries.Keys.BLOCKS, 
                        ResourceLocation.fromNamespaceAndPath("forge", "barrels/wooden")
                    )
                );
    }

    private static ToolUsageResult rotateBarrelMutation(UseOnContext context, Level level, BlockState state, BlockPos pos, ItemStack stack) {
        if (!state.hasProperty(BlockStateProperties.FACING)) {
            return ToolUsageResult.fail();
        }
        if (!level.isClientSide()) {
            level.setBlockAndUpdate(pos, state.cycle(BlockStateProperties.FACING));
        }
        return ToolUsageResult.slowSuccess();
    }
}

