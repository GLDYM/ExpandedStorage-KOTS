package compasses.expandedstorage.client.registry;

import compasses.expandedstorage.api.EsChestType;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public final class ChestTextureRegistry {
    private static final Map<ResourceLocation, ResourceLocation[]> CHEST_TEXTURES = new HashMap<>();

    private ChestTextureRegistry() {
    }

    public static void register() {
        registerChestTextures("wood_chest");
        registerChestTextures("pumpkin_chest");
        registerChestTextures("present");
        registerChestTextures("bamboo_chest");
        registerChestTextures("moss_chest");
        registerChestTextures("iron_chest");
        registerChestTextures("gold_chest");
        registerChestTextures("diamond_chest");
        registerChestTextures("obsidian_chest");
        registerChestTextures("netherite_chest");
    }

    private static void registerChestTextures(String blockId) {
        ResourceLocation id = compasses.expandedstorage.ForgeMain.id(blockId);
        registerChestTextures(
                id,
                compasses.expandedstorage.ForgeMain.id("entity/chest/" + blockId + "_single"),
                compasses.expandedstorage.ForgeMain.id("entity/chest/" + blockId + "_left"),
                compasses.expandedstorage.ForgeMain.id("entity/chest/" + blockId + "_right"),
                compasses.expandedstorage.ForgeMain.id("entity/chest/" + blockId + "_top"),
                compasses.expandedstorage.ForgeMain.id("entity/chest/" + blockId + "_bottom"),
                compasses.expandedstorage.ForgeMain.id("entity/chest/" + blockId + "_front"),
                compasses.expandedstorage.ForgeMain.id("entity/chest/" + blockId + "_back")
        );
    }

    public static void registerChestTextures(ResourceLocation block, ResourceLocation singleTexture, ResourceLocation leftTexture, ResourceLocation rightTexture, ResourceLocation topTexture, ResourceLocation bottomTexture, ResourceLocation frontTexture, ResourceLocation backTexture) {
        if (!CHEST_TEXTURES.containsKey(block)) {
            ResourceLocation[] collection = {topTexture, bottomTexture, frontTexture, backTexture, leftTexture, rightTexture, singleTexture};
            CHEST_TEXTURES.put(block, collection);
            return;
        }
        throw new IllegalArgumentException("Tried registering chest textures for \"" + block + "\" which already has textures.");
    }

    public static ResourceLocation getChestTexture(ResourceLocation block, EsChestType chestType) {
        if (CHEST_TEXTURES.containsKey(block)) {
            return CHEST_TEXTURES.get(block)[chestType.ordinal()];
        }
        return MissingTextureAtlasSprite.getLocation();
    }
}
