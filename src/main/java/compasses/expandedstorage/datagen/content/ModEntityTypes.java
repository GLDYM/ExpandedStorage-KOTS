package compasses.expandedstorage.datagen.content;

import compasses.expandedstorage.entity.ChestMinecart;
import compasses.expandedstorage.misc.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import compasses.expandedstorage.ExpandedStorage;

// todo: generate these classes?
public final class ModEntityTypes {
    public static final EntityType<ChestMinecart> WOOD_CHEST_MINECART = entityType(ExpandedStorage.id("wood_chest_minecart"));
    public static final EntityType<ChestMinecart> PUMPKIN_CHEST_MINECART = entityType(ExpandedStorage.id("pumpkin_chest_minecart"));
    public static final EntityType<ChestMinecart> PRESENT_MINECART = entityType(ExpandedStorage.id("present_minecart"));
    public static final EntityType<ChestMinecart> BAMBOO_CHEST_MINECART = entityType(ExpandedStorage.id("bamboo_chest_minecart"));
    public static final EntityType<ChestMinecart> MOSS_CHEST_MINECART = entityType(ExpandedStorage.id("moss_chest_minecart"));
    public static final EntityType<ChestMinecart> IRON_CHEST_MINECART = entityType(ExpandedStorage.id("iron_chest_minecart"));
    public static final EntityType<ChestMinecart> GOLD_CHEST_MINECART = entityType(ExpandedStorage.id("gold_chest_minecart"));
    public static final EntityType<ChestMinecart> DIAMOND_CHEST_MINECART = entityType(ExpandedStorage.id("diamond_chest_minecart"));
    public static final EntityType<ChestMinecart> OBSIDIAN_CHEST_MINECART = entityType(ExpandedStorage.id("obsidian_chest_minecart"));
    public static final EntityType<ChestMinecart> NETHERITE_CHEST_MINECART = entityType(ExpandedStorage.id("netherite_chest_minecart"));

    private static <T extends Entity> EntityType<T> entityType(ResourceLocation id) {
        //noinspection unchecked
        return (EntityType<T>) BuiltInRegistries.ENTITY_TYPE.get(id);
    }
}

