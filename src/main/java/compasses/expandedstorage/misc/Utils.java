package compasses.expandedstorage.misc;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public final class Utils {
    public static final Component ALT_USE = Component.translatable("tooltip.expandedstorage.alt_use",
            Component.keybind("key.sneak").withStyle(ChatFormatting.GOLD),
            Component.keybind("key.use").withStyle(ChatFormatting.GOLD));
    public static final int TOOL_USAGE_QUICK_DELAY = 5; // In ticks...
    public static final int TOOL_USAGE_DELAY = 20; // In ticks...
    // Gui Element Sizes
    public static final int SLOT_SIZE = 18;
    public static final int CONTAINER_HEADER_HEIGHT = 17;
    public static final int CONTAINER_PADDING_LDR = 7;

    private Utils() {
        throw new IllegalStateException("Should not instantiate this class.");
    }
}

