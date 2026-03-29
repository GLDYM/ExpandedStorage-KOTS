package compasses.expandedstorage.client.registry;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import org.lwjgl.glfw.GLFW;

public final class AllClientKeyMappings {
    private static final KeyMapping CONFIG_KEY_BINDING = new KeyMapping(
            "key.expandedstorage.config",
            KeyConflictContext.GUI,
            KeyModifier.SHIFT,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "key.categories.inventory"
    );

    private AllClientKeyMappings() {
    }

    public static void register(IEventBus modBus) {
        modBus.addListener((RegisterKeyMappingsEvent event) -> event.register(CONFIG_KEY_BINDING));
    }

    public static boolean isConfigKeyPressed(int keyCode, int scanCode, int modifiers) {
        return CONFIG_KEY_BINDING.matches(keyCode, scanCode);
    }
}