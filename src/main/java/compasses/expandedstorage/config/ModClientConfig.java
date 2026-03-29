package compasses.expandedstorage.config;

import compasses.expandedstorage.misc.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.jetbrains.annotations.Nullable;
import compasses.expandedstorage.ExpandedStorage;

import java.util.Set;

public final class ModClientConfig {
    public static final ResourceLocation PAGINATED_SCREEN_TYPE = ResourceLocation.fromNamespaceAndPath(ExpandedStorage.MOD_ID, "paginated");
    public static final ResourceLocation SINGLE_SCREEN_TYPE = ResourceLocation.fromNamespaceAndPath(ExpandedStorage.MOD_ID, "single");
    public static final ResourceLocation SCROLLABLE_SCREEN_TYPE = ResourceLocation.fromNamespaceAndPath(ExpandedStorage.MOD_ID, "scrollable");
    public static final ResourceLocation MINI_STORAGE_SCREEN_TYPE = ResourceLocation.fromNamespaceAndPath(ExpandedStorage.MOD_ID, "mini_storage");

    private static final String AUTO_SCREEN_TYPE = "auto";
    private static final Set<String> VALID_SCREEN_TYPES = Set.of(
            PAGINATED_SCREEN_TYPE.toString(),
            SINGLE_SCREEN_TYPE.toString(),
            SCROLLABLE_SCREEN_TYPE.toString()
    );

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    private static final ForgeConfigSpec.BooleanValue RESTRICTIVE_SCROLLING;
    private static final ForgeConfigSpec.ConfigValue<String> CONTAINER_TYPE;
    private static final ForgeConfigSpec.BooleanValue PREFER_SMALLER_SCREENS;
    private static final ForgeConfigSpec.BooleanValue FIT_VANILLA_CONSTRAINTS;

    static {
        BUILDER.push("client");
        RESTRICTIVE_SCROLLING = BUILDER
                .comment("When true, scroll wheel only works while hovering the scrollbar.")
                .define("restrictive_scrolling", false);
        CONTAINER_TYPE = BUILDER
                .comment("Preferred container ui type: auto, expandedstorage:paginated, expandedstorage:single, expandedstorage:scrollable.")
                .define("container_type", AUTO_SCREEN_TYPE);
        PREFER_SMALLER_SCREENS = BUILDER
                .comment("When true, prefer layouts with fewer columns when multiple choices are valid.")
                .define("prefer_smaller_screens", true);
        FIT_VANILLA_CONSTRAINTS = BUILDER
                .comment("When true, force vanilla-like container constraints.")
                .define("fit_vanilla_constraints", false);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    private ModClientConfig() {
    }

    public static void register(ModLoadingContext context) {
        context.registerConfig(ModConfig.Type.CLIENT, SPEC, "expandedstorage-client.toml");
    }

    public static boolean isScrollingUnrestricted() {
        return !RESTRICTIVE_SCROLLING.get();
    }

    public static boolean preferSmallerScreens() {
        return PREFER_SMALLER_SCREENS.get();
    }

    public static boolean fitVanillaConstraints() {
        return FIT_VANILLA_CONSTRAINTS.get();
    }

    @Nullable
    public static ResourceLocation getPreferredScreenType() {
        String value = CONTAINER_TYPE.get();
        if (value == null || value.isBlank() || AUTO_SCREEN_TYPE.equals(value)) {
            return null;
        }
        ResourceLocation parsed = ResourceLocation.tryParse(value);
        if (parsed == null || !isValidScreenType(parsed)) {
            return null;
        }
        return parsed;
    }

    public static void setPreferredScreenType(ResourceLocation type) {
        if (!isValidScreenType(type)) {
            return;
        }
        if (!type.toString().equals(CONTAINER_TYPE.get())) {
            CONTAINER_TYPE.set(type.toString());
        }
    }

    private static boolean isValidScreenType(ResourceLocation type) {
        return VALID_SCREEN_TYPES.contains(type.toString());
    }
}

