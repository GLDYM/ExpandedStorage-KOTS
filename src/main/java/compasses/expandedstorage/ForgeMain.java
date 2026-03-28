package compasses.expandedstorage;

import compasses.expandedstorage.inventory.handler.AbstractHandler;
import compasses.expandedstorage.network.ClientboundUpdateRecipesMessage;
import compasses.expandedstorage.recipe.BlockConversionRecipe;
import compasses.expandedstorage.recipe.EntityConversionRecipe;
import compasses.expandedstorage.registry.AllBlocks;
import compasses.expandedstorage.registry.ModCompat;
import compasses.expandedstorage.registry.ModEvents;
import compasses.expandedstorage.registry.ModRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod(ForgeMain.MOD_ID)
public final class ForgeMain {
    public static final String MOD_ID = "expandedstorage";
    public static final Logger LOGGER = LogManager.getLogger();
    public static IEventBus modBus;
    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(id("channel"), () -> "1.0", "1.0"::equals, "1.0"::equals);
    private static final MenuType<AbstractHandler> SCREEN_HANDLER_TYPE = new MenuType<>((IContainerFactory<AbstractHandler>) AbstractHandler::createClientMenu, FeatureFlags.VANILLA_SET);

    public ForgeMain(FMLJavaModLoadingContext contentBus) {
        modBus = contentBus.getModEventBus();
        CHANNEL.registerMessage(0, ClientboundUpdateRecipesMessage.class, ClientboundUpdateRecipesMessage::encode, ClientboundUpdateRecipesMessage::decode, ClientboundUpdateRecipesMessage::handle);

        ModRegistry.register(modBus);
        ModEvents.register();
        ModCompat.register(modBus);
        ForgeClient.register(modBus);
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

    public static void sendConversionRecipesToClient(@Nullable ServerPlayer target, List<BlockConversionRecipe<?>> blockRecipes, List<EntityConversionRecipe<?>> entityRecipes) {
        ClientboundUpdateRecipesMessage message = new ClientboundUpdateRecipesMessage(blockRecipes, entityRecipes);
        if (target == null) {
            CHANNEL.send(PacketDistributor.ALL.noArg(), message);
            return;
        }
        if (!CHANNEL.isRemotePresent(target.connection.connection)) {
            target.connection.disconnect(Component.translatable("text.expandedstorage.disconnect.old_version"));
            return;
        }
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> target), message);
    }

    public static boolean canDestroyBamboo(ItemStack stack) {
        return stack.canPerformAction(ToolActions.SWORD_DIG);
    }

    public static boolean isWoodenChest(BlockState state) {
        return state.is(AllBlocks.OLD_WOOD_CHEST) || state.is(Tags.Blocks.CHESTS_WOODEN);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}

