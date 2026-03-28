package compasses.expandedstorage.registry;

import compasses.expandedstorage.CommonMain;
import compasses.expandedstorage.block.entity.extendable.OpenableBlockEntity;
import compasses.expandedstorage.ForgeMain;
import compasses.expandedstorage.misc.Utils;
import compasses.expandedstorage.recipe.ConversionRecipeManager;
import compasses.expandedstorage.recipe.ConversionRecipeReloadListener;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ModEvents {
    private ModEvents() {
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.addListener((AddReloadListenerEvent event) -> event.addListener(new ConversionRecipeReloadListener()));
        MinecraftForge.EVENT_BUS.addListener((OnDatapackSyncEvent event) -> ForgeMain.sendConversionRecipesToClient(event.getPlayer(), ConversionRecipeManager.INSTANCE.getBlockRecipes(), ConversionRecipeManager.INSTANCE.getEntityRecipes()));

        MinecraftForge.EVENT_BUS.addGenericListener(BlockEntity.class, (AttachCapabilitiesEvent<BlockEntity> event) -> {
            if (event.getObject() instanceof OpenableBlockEntity entity) {
                event.addCapability(compasses.expandedstorage.ForgeMain.id("item_access"), new ICapabilityProvider() {
                    @NotNull
                    @Override
                    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
                        if (capability == ForgeCapabilities.ITEM_HANDLER) {
                            return CommonMain.getItemAccess(entity.getLevel(), entity.getBlockPos(), entity.getBlockState(), entity)
                                    .map(access -> LazyOptional.of(() -> (T) access.get()))
                                    .orElse(LazyOptional.empty());
                        }
                        return LazyOptional.empty();
                    }
                });
            }
        });

        MinecraftForge.EVENT_BUS.addListener((PlayerInteractEvent.EntityInteractSpecific event) -> {
            InteractionResult result = CommonMain.interactWithEntity(event.getLevel(), event.getEntity(), event.getHand(), event.getTarget());
            if (result != InteractionResult.PASS) {
                event.setCancellationResult(result);
                event.setCanceled(true);
            }
        });
    }
}

