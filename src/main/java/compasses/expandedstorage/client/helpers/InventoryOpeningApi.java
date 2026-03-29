package compasses.expandedstorage.client.helpers;

import compasses.expandedstorage.inventory.handler.AbstractHandler;
import compasses.expandedstorage.inventory.OpenableInventory;
import compasses.expandedstorage.inventory.OpenableInventoryProvider;
import compasses.expandedstorage.inventory.context.BaseContext;
import compasses.expandedstorage.inventory.context.BlockContext;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class InventoryOpeningApi {
    private static final MenuType<AbstractHandler> SCREEN_HANDLER_TYPE = new MenuType<>((IContainerFactory<AbstractHandler>) AbstractHandler::createClientMenu, FeatureFlags.VANILLA_SET);

    private InventoryOpeningApi() {
        throw new IllegalStateException("InventoryOpeningApi should not be instantiated.");
    }

    public static MenuType<AbstractHandler> screenHandlerType() {
        return SCREEN_HANDLER_TYPE;
    }

    public static void openScreenHandler(ServerPlayer player, Container inventory, Component title, ResourceLocation forcedScreenType) {
        NetworkHooks.openScreen(player, new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return title;
            }

            @NotNull
            @Override
            public AbstractContainerMenu createMenu(int syncId, Inventory playerInventory, Player innerPlayer) {
                return new AbstractHandler(syncId, inventory, playerInventory, forcedScreenType);
            }
        }, buffer -> {
            buffer.writeInt(inventory.getContainerSize());
            if (forcedScreenType != null) {
                buffer.writeResourceLocation(forcedScreenType);
            }
        });
    }

    public static void openBlockInventory(ServerPlayer player, BlockPos pos, OpenableInventoryProvider<BlockContext> inventory) {
        InventoryOpeningApi.s_openInventory(player, inventory.getOpenableInventory(new BlockContext(player.serverLevel(), player, pos)), inventory::onInitialOpen, inventory.getForcedScreenType());
    }

    public static void openEntityInventory(ServerPlayer player, OpenableInventoryProvider<BaseContext> inventory) {
        InventoryOpeningApi.s_openInventory(player, inventory.getOpenableInventory(new BaseContext(player.serverLevel(), player)), inventory::onInitialOpen, inventory.getForcedScreenType());
    }

    private static void s_openInventory(ServerPlayer player, OpenableInventory inventory, Consumer<ServerPlayer> onInitialOpen, ResourceLocation forcedScreenType) {
        Component title = inventory.getInventoryTitle();

        if (!inventory.canBeUsedBy(player)) {
            player.displayClientMessage(Component.translatable("container.isLocked", title), true);
            player.playNotifySound(SoundEvents.CHEST_LOCKED, SoundSource.BLOCKS, 1.0F, 1.0F);
            return;
        }

        if (!player.isSpectator()) {
            onInitialOpen.accept(player);
        }

        InventoryOpeningApi.openScreenHandler(player, inventory.getInventory(), title, forcedScreenType);
    }
}




