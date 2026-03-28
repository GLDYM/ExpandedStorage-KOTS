package compasses.expandedstorage.registry.content;

import compasses.expandedstorage.CommonMain;
import compasses.expandedstorage.item.MutationMode;
import compasses.expandedstorage.item.ToolUsageResult;
import compasses.expandedstorage.recipe.BlockConversionRecipe;
import compasses.expandedstorage.recipe.ConversionRecipeManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

final class ThemeSwapModule {
    private ThemeSwapModule() {
    }

    static void register() {
        CommonMain.registerMutationBehaviour(ThemeSwapModule::alwaysTrue, MutationMode.SWAP_THEME, ThemeSwapModule::swapThemeMutation);
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

