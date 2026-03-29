package compasses.expandedstorage.client.gui;

import compasses.expandedstorage.config.ModClientConfig;
import compasses.expandedstorage.ExpandedStorage;

import com.google.common.collect.ImmutableSortedSet;
import compasses.expandedstorage.client.gui.widget.PickButton;
import compasses.expandedstorage.client.gui.widget.ScreenPickButton;
import compasses.expandedstorage.misc.Utils;
import compasses.expandedstorage.inventory.handler.AbstractHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class PickScreen extends Screen {
    public static final Component CURRENT_OPTION_TEXT = Component.translatable("screen.expandedstorage.current_option_notice").withStyle(ChatFormatting.GOLD);
    public static final Map<ResourceLocation, PickButton> BUTTON_SETTINGS = Map.of(
            ModClientConfig.PAGINATED_SCREEN_TYPE, new PickButton(
                    ExpandedStorage.id("textures/gui/page_button.png"),
                    Component.translatable("screen.expandedstorage.page_screen")
            ),
            ModClientConfig.SCROLLABLE_SCREEN_TYPE, new PickButton(
                    ExpandedStorage.id("textures/gui/scroll_button.png"),
                    Component.translatable("screen.expandedstorage.scroll_screen")
            ),
            ModClientConfig.SINGLE_SCREEN_TYPE, new PickButton(
                    ExpandedStorage.id("textures/gui/single_button.png"),
                    Component.translatable("screen.expandedstorage.single_screen"),

                    Component.translatable("screen.expandedstorage.off_screen_warning_1").withStyle(ChatFormatting.GRAY),
                    Component.translatable("screen.expandedstorage.off_screen_warning_2").withStyle(ChatFormatting.GRAY)
            ) {
                @Override
                public boolean shouldShowWarning(int scaledWidth, int scaledHeight) {
                    return scaledWidth < 370 || scaledHeight < 386; // Smallest possible resolution a double netherite chest fits on.
                }
            }
    );
    private final Set<ResourceLocation> options = ImmutableSortedSet.copyOf(PickScreen.BUTTON_SETTINGS.keySet());
    private final Supplier<Screen> returnToScreen;
    private final AbstractHandler handler;
    private int topPadding;

    public PickScreen(AbstractScreen currentScreen) {
        this(currentScreen.getMenu(), () -> {
            return AbstractScreen.createScreen(currentScreen.getMenu(), Minecraft.getInstance().player.getInventory(), currentScreen.getTitle());
        });
    }

    public PickScreen(Screen returnToScreen) {
        this(null, () -> returnToScreen);
    }

    private PickScreen(@Nullable AbstractHandler handler, Supplier<Screen> returnToScreen) {
        super(Component.translatable("screen.expandedstorage.screen_picker_title"));
        this.handler = handler;
        this.returnToScreen = returnToScreen;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onClose() {
        if (handler != null) {
            ResourceLocation preference = ModClientConfig.getPreferredScreenType();
            if (preference == null) {
                minecraft.player.closeContainer();
                return;
            }
            else if (AbstractScreen.getScreenSize(preference, handler.getInventory().getContainerSize(), minecraft.getWindow().getGuiScaledWidth(), minecraft.getWindow().getGuiScaledHeight()) == null) {
                minecraft.player.displayClientMessage(Component.translatable("text.expandedstorage.short_prefix").withStyle(ChatFormatting.GOLD).append(Component.translatable("chat.expandedstorage.cannot_display_screen", Component.translatable("screen." + preference.getNamespace() + "." + preference.getPath() + "_screen")).withStyle(ChatFormatting.WHITE)), false);
                minecraft.player.closeContainer();
                return;
            }
            handler.clearSlots();
        }
        minecraft.setScreen(returnToScreen.get());
    }

    @Override
    public boolean isPauseScreen() {
        //noinspection ConstantConditions
        return minecraft.level == null;
    }

    @Override
    protected void init() {
        super.init();
        ResourceLocation preference = ModClientConfig.getPreferredScreenType();
        int choices = options.size();
        int columns = Math.min(Math.floorDiv(width, 96), choices);
        int innerPadding = Math.min((width - columns * 96) / (columns + 1), 20); // 20 is smallest gap for any screen.
        int outerPadding = (width - (((columns - 1) * innerPadding) + (columns * 96))) / 2;
        int x = 0;
        int topPadding = (height - 96) / 2;
        this.topPadding = topPadding;
        for (ResourceLocation option : options) {
            PickButton settings = PickScreen.BUTTON_SETTINGS.get(option);
            boolean isWarn = settings.shouldShowWarning(width, height);
            boolean isCurrent = option.equals(preference);
            MutableComponent tooltipMessage = Component.literal("").append(settings.getTitle());
            if (isCurrent) {
                tooltipMessage.append("\n");
                tooltipMessage.append(CURRENT_OPTION_TEXT);
            }
            if (isWarn) {
                tooltipMessage.append("\n");
                settings.getWarningText().forEach(tooltipMessage::append);
            }
            this.addRenderableWidget(new ScreenPickButton(outerPadding + (innerPadding + 96) * x, topPadding, 96, 96,
                    settings.getTexture(), settings.getTitle(), isWarn, isCurrent, __ -> this.updatePlayerPreference(option), Tooltip.create(tooltipMessage, tooltipMessage)));
            x++;
        }
    }

    private void updatePlayerPreference(ResourceLocation selection) {
        ModClientConfig.setPreferredScreenType(selection);
        this.onClose();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        graphics.drawCenteredString(font, title, width / 2, Math.max(topPadding / 2, 0), 0xFFFFFFFF);
    }
}




