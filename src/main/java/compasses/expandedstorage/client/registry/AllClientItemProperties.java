package compasses.expandedstorage.client.registry;

import compasses.expandedstorage.block.MiniStorageBlock;
import compasses.expandedstorage.item.MutationMode;
import compasses.expandedstorage.item.StorageMutator;
import compasses.expandedstorage.misc.Utils;
import compasses.expandedstorage.registry.AllItems;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.Nullable;

public final class AllClientItemProperties {
    private AllClientItemProperties() {
    }

    public static void register(IEventBus modBus) {
        modBus.addListener((FMLClientSetupEvent event) -> {
            ItemProperties.registerGeneric(compasses.expandedstorage.ForgeMain.id("sparrow"), (ClampedItemPropertyFunction) AllClientItemProperties::hasSparrowProperty);
            ItemProperties.register(AllItems.STORAGE_MUTATOR_REGISTRY.get(), compasses.expandedstorage.ForgeMain.id("tool_mode"), (ClampedItemPropertyFunction) AllClientItemProperties::currentMutatorToolMode);
        });
    }

    private static float hasSparrowProperty(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int i) {
        return MiniStorageBlock.hasSparrowProperty(stack) ? 1.0f : 0.0f;
    }

    private static float currentMutatorToolMode(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int i) {
        MutationMode mode = StorageMutator.getMode(stack);
        boolean isSparrow = stack.hasCustomHoverName() && stack.getHoverName().getString().equalsIgnoreCase("sparrow");
        if (mode == MutationMode.SWAP_THEME) {
            if (isSparrow) {
                return 1.0F;
            }
            return 0.8F;
        } else if (mode == MutationMode.ROTATE) {
            return 0.6F;
        } else if (mode == MutationMode.SPLIT) {
            return 0.4F;
        } else if (mode == MutationMode.MERGE) {
            return 0.2F;
        }
        return 0.0F;
    }
}

