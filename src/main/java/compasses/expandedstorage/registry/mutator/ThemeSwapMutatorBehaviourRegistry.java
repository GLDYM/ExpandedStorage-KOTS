package compasses.expandedstorage.registry.mutator;

import compasses.expandedstorage.item.MutationMode;
import compasses.expandedstorage.item.ToolUsageResult;
import compasses.expandedstorage.recipe.BlockConversionRecipe;
import compasses.expandedstorage.recipe.ConversionRecipeManager;
import compasses.expandedstorage.registry.BlockMutatorBehaviours;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public final class ThemeSwapMutatorBehaviourRegistry {
    private ThemeSwapMutatorBehaviourRegistry() {
    }

    public static void register() {
        BlockMutatorBehaviours.register(ThemeSwapMutatorBehaviourRegistry::alwaysTrue, MutationMode.SWAP_THEME, ThemeSwapMutatorBehaviourRegistry::swapThemeMutation);
    }

    private static boolean alwaysTrue(Block block) {
        return true;
    }

    private static ToolUsageResult swapThemeMutation(UseOnContext context, Level level, BlockState state, BlockPos pos, ItemStack stack) {
        BlockConversionRecipe<?> recipe = ConversionRecipeManager.INSTANCE.getBlockRecipe(state, stack);
        if (recipe != null) {
            return recipe.process(level, context.getPlayer(), stack, state, pos);
        }
        return ToolUsageResult.fail();
    }
}

