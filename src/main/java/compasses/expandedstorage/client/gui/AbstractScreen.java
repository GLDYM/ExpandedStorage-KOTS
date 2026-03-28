package compasses.expandedstorage.client.gui;

import compasses.expandedstorage.ForgeClient;
import compasses.expandedstorage.config.ModClientConfig;

import compasses.expandedstorage.CommonClient;
import compasses.expandedstorage.client.function.ScreenSize;
import compasses.expandedstorage.misc.Utils;
import compasses.expandedstorage.inventory.handler.AbstractHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractScreen extends AbstractContainerScreen<AbstractHandler> {
    protected final int inventoryWidth, inventoryHeight, totalSlots;
    protected final ResourceLocation textureLocation;
    protected final int textureWidth, textureHeight;

    protected AbstractScreen(AbstractHandler handler, Inventory playerInventory, Component title, ScreenSize screenSize) {
        super(handler, playerInventory, title);
        totalSlots = handler.getInventory().getContainerSize();
        inventoryWidth = screenSize.width();
        inventoryHeight = screenSize.height();

        if (this instanceof MiniStorageScreen) {
            textureLocation = compasses.expandedstorage.ForgeMain.id("textures/gui/container/mini_chest_screen.png");
            textureWidth = textureHeight = 176;
        } else if (this instanceof FakePickScreen) {
            textureLocation = null;
            textureWidth = textureHeight = 0;
        }else {
            textureLocation = compasses.expandedstorage.ForgeMain.id("textures/gui/container/shared_" + inventoryWidth + "_" + inventoryHeight + ".png");

            if (inventoryHeight == 3) {
                textureWidth = 176;
            } else if (inventoryWidth == 9) {
                textureWidth = 208;
            } else if (inventoryWidth == 12) {
                textureWidth = 256;
            } else if (inventoryWidth == 15) {
                textureWidth = 320;
            } else if (inventoryWidth == 18) {
                textureWidth = 368;
            } else {
                throw new IllegalStateException("Unable to determine width for screen texture: " + textureLocation);
            }

            textureHeight = switch (inventoryHeight) {
                case 3 -> 192;
                case 5, 6 -> 240;
                case 9 -> 304;
                case 12 -> 352;
                case 15 -> 416;
                default -> throw new IllegalStateException("Unable to determine height for screen texture: " + textureLocation);
            };
        }
    }

    public static AbstractScreen createScreen(AbstractHandler handler, Inventory playerInventory, Component title) {
        ResourceLocation forcedScreenType = handler.getForcedScreenType();
        ResourceLocation preference = forcedScreenType != null ? forcedScreenType : ForgeClient.getPreferredScreenType();
        int scaledWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int scaledHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        int slots = handler.getInventory().getContainerSize();

        if (forcedScreenType == null && AbstractScreen.canSingleScreenDisplay(slots, scaledWidth, scaledHeight) && AbstractScreen.shouldPreferSingleScreen(preference)) {
            preference = ModClientConfig.SINGLE_SCREEN_TYPE;
        }

        if (preference == null) {
            return new FakePickScreen(handler, playerInventory, title);
        }

        ScreenSize screenSize = getScreenSize(preference, slots, scaledWidth, scaledHeight);

        if (screenSize == null) {
            throw new IllegalStateException("screenSize should never be null...");
        }

        if (ModClientConfig.PAGINATED_SCREEN_TYPE.equals(preference)) {
            return new PageScreen(handler, playerInventory, title, screenSize);
        } else if (ModClientConfig.SCROLLABLE_SCREEN_TYPE.equals(preference)) {
            return new ScrollScreen(handler, playerInventory, title, screenSize);
        } else if (ModClientConfig.SINGLE_SCREEN_TYPE.equals(preference)) {
            return new SingleScreen(handler, playerInventory, title, screenSize);
        } else if (ModClientConfig.MINI_STORAGE_SCREEN_TYPE.equals(preference)) {
            return new MiniStorageScreen(handler, playerInventory, title, screenSize);
        }

        throw new IllegalArgumentException("Unknown preference.");
    }

    private static boolean shouldPreferSingleScreen(ResourceLocation type) {
        return ModClientConfig.PAGINATED_SCREEN_TYPE.equals(type) || ModClientConfig.SCROLLABLE_SCREEN_TYPE.equals(type);
    }

    private static boolean canSingleScreenDisplay(int slots, int scaledWidth, int scaledHeight) {
        if (slots <= 54) {
            return true;
        }

        if (ForgeClient.fitVanillaConstraints()) {
            return false;
        }

        if (scaledHeight >= 276) {
            if (slots <= 81) {
                return true;
            }
            if (scaledWidth >= 230 && slots <= 108) {
                return true;
            }
            if (scaledWidth >= 284 && slots <= 135) {
                return true;
            }
            if (scaledWidth >= 338 && slots <= 162) {
                return true;
            }
        }
        if (scaledWidth >= 338) {
            if (scaledHeight >= 330 && slots <= 216) {
                return true;
            }
            return scaledHeight >= 384 && slots <= 270;
        }
        return false;
    }

    @Nullable
    public static ScreenSize getScreenSize(ResourceLocation type, int slots, int scaledWidth, int scaledHeight) {
        if (ModClientConfig.PAGINATED_SCREEN_TYPE.equals(type)) {
            return PageScreen.retrieveScreenSize(slots, scaledWidth, scaledHeight);
        } else if (ModClientConfig.SCROLLABLE_SCREEN_TYPE.equals(type)) {
            return ScrollScreen.retrieveScreenSize(slots, scaledWidth, scaledHeight);
        } else if (ModClientConfig.SINGLE_SCREEN_TYPE.equals(type)) {
            return SingleScreen.retrieveScreenSize(slots, scaledWidth, scaledHeight);
        } else if (ModClientConfig.MINI_STORAGE_SCREEN_TYPE.equals(type)) {
            return new ScreenSize(1, 1);
        }

        return null;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        this.renderTooltip(graphics, mouseX, mouseY);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public final boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.handleKeyPress(keyCode, scanCode, modifiers)) {
            return true;
        } else if (ForgeClient.isConfigKeyPressed(keyCode, scanCode, modifiers) && menu.getForcedScreenType() == null
                && ForgeClient.getPreferredScreenType() != null) {
            minecraft.setScreen(new PickScreen(this));
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    /**
     * @return true if a screen specific keybinding is pressed otherwise false to follow through with additional checks.
     */
    protected boolean handleKeyPress(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    @NotNull
    @ApiStatus.OverrideOnly
    public List<Rect2i> getExclusionZones() {
        return List.of();
    }

    // todo: was this api for someone?
    public int getInventoryWidth() {
        return inventoryWidth;
    }
}




