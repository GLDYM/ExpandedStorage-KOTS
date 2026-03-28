package compasses.expandedstorage;

import compasses.expandedstorage.client.registry.ClientRegistry;
import compasses.expandedstorage.config.ModClientConfig;
import compasses.expandedstorage.misc.Utils;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraft.client.KeyMapping;

public final class ForgeClient {
    private ForgeClient() {
    }

    public static void register(IEventBus modBus) {
        ClientRegistry.register();
        if (!FMLLoader.getDist().isClient()) {
            return;
        }
        ModClientConfig.register(ModLoadingContext.get());
        ClientState.register(modBus);
    }

    public static boolean isConfigKeyPressed(int keyCode, int scanCode, int modifiers) {
        return ClientState.isConfigKeyPressed(keyCode, scanCode, modifiers);
    }

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    public static boolean isScrollingUnrestricted() {
        return ModClientConfig.isScrollingUnrestricted();
    }

    public static boolean preferSmallerScreens() {
        return ModClientConfig.preferSmallerScreens();
    }

    public static boolean fitVanillaConstraints() {
        return ModClientConfig.fitVanillaConstraints();
    }

    public static ResourceLocation getPreferredScreenType() {
        return ModClientConfig.getPreferredScreenType();
    }

    public static void setPreferredScreenType(ResourceLocation type) {
        ModClientConfig.setPreferredScreenType(type);
    }

    private static final class ClientState {
        private static final KeyMapping CONFIG_KEY_BINDING = new KeyMapping("key.expandedstorage.config", KeyConflictContext.GUI, KeyModifier.SHIFT, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, "key.categories.inventory");
        private ClientState() {
        }

        private static void register(IEventBus modBus) {
            modBus.addListener((RegisterKeyMappingsEvent event) -> event.register(CONFIG_KEY_BINDING));
        }

        private static boolean isConfigKeyPressed(int keyCode, int scanCode, int modifiers) {
            return CONFIG_KEY_BINDING.matches(keyCode, scanCode);
        }

    }
}

