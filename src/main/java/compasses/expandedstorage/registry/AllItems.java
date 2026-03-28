package compasses.expandedstorage.registry;

import compasses.expandedstorage.item.ChestMinecartItem;
import compasses.expandedstorage.misc.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.Map;

public final class AllItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, compasses.expandedstorage.ForgeMain.MOD_ID);
    private static final Map<ResourceLocation, RegistryObject<Item>> REGISTRY_OBJECTS = new LinkedHashMap<>();
    private static boolean initialized;

    public static final Item STORAGE_MUTATOR = item(compasses.expandedstorage.ForgeMain.id("storage_mutator"));

    public static final Item WOOD_TO_COPPER_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("wood_to_copper_conversion_kit"));
    public static final Item WOOD_TO_IRON_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("wood_to_iron_conversion_kit"));
    public static final Item WOOD_TO_GOLD_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("wood_to_gold_conversion_kit"));
    public static final Item WOOD_TO_DIAMOND_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("wood_to_diamond_conversion_kit"));
    public static final Item WOOD_TO_OBSIDIAN_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("wood_to_obsidian_conversion_kit"));
    public static final Item WOOD_TO_NETHERITE_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("wood_to_netherite_conversion_kit"));
    public static final Item COPPER_TO_IRON_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("copper_to_iron_conversion_kit"));
    public static final Item COPPER_TO_GOLD_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("copper_to_gold_conversion_kit"));
    public static final Item COPPER_TO_DIAMOND_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("copper_to_diamond_conversion_kit"));
    public static final Item COPPER_TO_OBSIDIAN_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("copper_to_obsidian_conversion_kit"));
    public static final Item COPPER_TO_NETHERITE_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("copper_to_netherite_conversion_kit"));
    public static final Item IRON_TO_GOLD_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("iron_to_gold_conversion_kit"));
    public static final Item IRON_TO_DIAMOND_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("iron_to_diamond_conversion_kit"));
    public static final Item IRON_TO_OBSIDIAN_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("iron_to_obsidian_conversion_kit"));
    public static final Item IRON_TO_NETHERITE_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("iron_to_netherite_conversion_kit"));
    public static final Item GOLD_TO_DIAMOND_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("gold_to_diamond_conversion_kit"));
    public static final Item GOLD_TO_OBSIDIAN_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("gold_to_obsidian_conversion_kit"));
    public static final Item GOLD_TO_NETHERITE_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("gold_to_netherite_conversion_kit"));
    public static final Item DIAMOND_TO_OBSIDIAN_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("diamond_to_obsidian_conversion_kit"));
    public static final Item DIAMOND_TO_NETHERITE_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("diamond_to_netherite_conversion_kit"));
    public static final Item OBSIDIAN_TO_NETHERITE_CONVERSION_KIT = item(compasses.expandedstorage.ForgeMain.id("obsidian_to_netherite_conversion_kit"));

    public static final BlockItem WOOD_CHEST = item(compasses.expandedstorage.ForgeMain.id("wood_chest"));
    public static final BlockItem PUMPKIN_CHEST = item(compasses.expandedstorage.ForgeMain.id("pumpkin_chest"));
    public static final BlockItem PRESENT = item(compasses.expandedstorage.ForgeMain.id("present"));
    public static final BlockItem BAMBOO_CHEST = item(compasses.expandedstorage.ForgeMain.id("bamboo_chest"));
    public static final BlockItem MOSS_CHEST = item(compasses.expandedstorage.ForgeMain.id("moss_chest"));
    public static final BlockItem IRON_CHEST = item(compasses.expandedstorage.ForgeMain.id("iron_chest"));
    public static final BlockItem GOLD_CHEST = item(compasses.expandedstorage.ForgeMain.id("gold_chest"));
    public static final BlockItem DIAMOND_CHEST = item(compasses.expandedstorage.ForgeMain.id("diamond_chest"));
    public static final BlockItem OBSIDIAN_CHEST = item(compasses.expandedstorage.ForgeMain.id("obsidian_chest"));
    public static final BlockItem NETHERITE_CHEST = item(compasses.expandedstorage.ForgeMain.id("netherite_chest"));

    public static final ChestMinecartItem WOOD_CHEST_MINECART = item(compasses.expandedstorage.ForgeMain.id("wood_chest_minecart"));
    public static final ChestMinecartItem PUMPKIN_CHEST_MINECART = item(compasses.expandedstorage.ForgeMain.id("pumpkin_chest_minecart"));
    public static final ChestMinecartItem PRESENT_MINECART = item(compasses.expandedstorage.ForgeMain.id("present_minecart"));
    public static final ChestMinecartItem BAMBOO_CHEST_MINECART = item(compasses.expandedstorage.ForgeMain.id("bamboo_chest_minecart"));
    public static final ChestMinecartItem MOSS_CHEST_MINECART = item(compasses.expandedstorage.ForgeMain.id("moss_chest_minecart"));
    public static final ChestMinecartItem IRON_CHEST_MINECART = item(compasses.expandedstorage.ForgeMain.id("iron_chest_minecart"));
    public static final ChestMinecartItem GOLD_CHEST_MINECART = item(compasses.expandedstorage.ForgeMain.id("gold_chest_minecart"));
    public static final ChestMinecartItem DIAMOND_CHEST_MINECART = item(compasses.expandedstorage.ForgeMain.id("diamond_chest_minecart"));
    public static final ChestMinecartItem OBSIDIAN_CHEST_MINECART = item(compasses.expandedstorage.ForgeMain.id("obsidian_chest_minecart"));
    public static final ChestMinecartItem NETHERITE_CHEST_MINECART = item(compasses.expandedstorage.ForgeMain.id("netherite_chest_minecart"));

    public static final BlockItem OLD_WOOD_CHEST = item(compasses.expandedstorage.ForgeMain.id("old_wood_chest"));
    public static final BlockItem OLD_IRON_CHEST = item(compasses.expandedstorage.ForgeMain.id("old_iron_chest"));
    public static final BlockItem OLD_GOLD_CHEST = item(compasses.expandedstorage.ForgeMain.id("old_gold_chest"));
    public static final BlockItem OLD_DIAMOND_CHEST = item(compasses.expandedstorage.ForgeMain.id("old_diamond_chest"));
    public static final BlockItem OLD_OBSIDIAN_CHEST = item(compasses.expandedstorage.ForgeMain.id("old_obsidian_chest"));
    public static final BlockItem OLD_NETHERITE_CHEST = item(compasses.expandedstorage.ForgeMain.id("old_netherite_chest"));

    public static final BlockItem COPPER_BARREL = item(compasses.expandedstorage.ForgeMain.id("copper_barrel"));
    public static final BlockItem EXPOSED_COPPER_BARREL = item(compasses.expandedstorage.ForgeMain.id("exposed_copper_barrel"));
    public static final BlockItem WEATHERED_COPPER_BARREL = item(compasses.expandedstorage.ForgeMain.id("weathered_copper_barrel"));
    public static final BlockItem OXIDIZED_COPPER_BARREL = item(compasses.expandedstorage.ForgeMain.id("oxidized_copper_barrel"));
    public static final BlockItem WAXED_COPPER_BARREL = item(compasses.expandedstorage.ForgeMain.id("waxed_copper_barrel"));
    public static final BlockItem WAXED_EXPOSED_COPPER_BARREL = item(compasses.expandedstorage.ForgeMain.id("waxed_exposed_copper_barrel"));
    public static final BlockItem WAXED_WEATHERED_COPPER_BARREL = item(compasses.expandedstorage.ForgeMain.id("waxed_weathered_copper_barrel"));
    public static final BlockItem WAXED_OXIDIZED_COPPER_BARREL = item(compasses.expandedstorage.ForgeMain.id("waxed_oxidized_copper_barrel"));
    public static final BlockItem IRON_BARREL = item(compasses.expandedstorage.ForgeMain.id("iron_barrel"));
    public static final BlockItem GOLD_BARREL = item(compasses.expandedstorage.ForgeMain.id("gold_barrel"));
    public static final BlockItem DIAMOND_BARREL = item(compasses.expandedstorage.ForgeMain.id("diamond_barrel"));
    public static final BlockItem OBSIDIAN_BARREL = item(compasses.expandedstorage.ForgeMain.id("obsidian_barrel"));
    public static final BlockItem NETHERITE_BARREL = item(compasses.expandedstorage.ForgeMain.id("netherite_barrel"));

    public static final BlockItem VANILLA_WOOD_MINI_CHEST = item(compasses.expandedstorage.ForgeMain.id("vanilla_wood_mini_chest"));
    public static final BlockItem WOOD_MINI_CHEST = item(compasses.expandedstorage.ForgeMain.id("wood_mini_chest"));
    public static final BlockItem PUMPKIN_MINI_CHEST = item(compasses.expandedstorage.ForgeMain.id("pumpkin_mini_chest"));
    public static final BlockItem RED_MINI_PRESENT = item(compasses.expandedstorage.ForgeMain.id("red_mini_present"));
    public static final BlockItem WHITE_MINI_PRESENT = item(compasses.expandedstorage.ForgeMain.id("white_mini_present"));
    public static final BlockItem CANDY_CANE_MINI_PRESENT = item(compasses.expandedstorage.ForgeMain.id("candy_cane_mini_present"));
    public static final BlockItem GREEN_MINI_PRESENT = item(compasses.expandedstorage.ForgeMain.id("green_mini_present"));
    public static final BlockItem LAVENDER_MINI_PRESENT = item(compasses.expandedstorage.ForgeMain.id("lavender_mini_present"));
    public static final BlockItem PINK_AMETHYST_MINI_PRESENT = item(compasses.expandedstorage.ForgeMain.id("pink_amethyst_mini_present"));
    public static final BlockItem IRON_MINI_CHEST = item(compasses.expandedstorage.ForgeMain.id("iron_mini_chest"));
    public static final BlockItem GOLD_MINI_CHEST = item(compasses.expandedstorage.ForgeMain.id("gold_mini_chest"));
    public static final BlockItem DIAMOND_MINI_CHEST = item(compasses.expandedstorage.ForgeMain.id("diamond_mini_chest"));
    public static final BlockItem OBSIDIAN_MINI_CHEST = item(compasses.expandedstorage.ForgeMain.id("obsidian_mini_chest"));
    public static final BlockItem NETHERITE_MINI_CHEST = item(compasses.expandedstorage.ForgeMain.id("netherite_mini_chest"));
    public static final BlockItem MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("mini_barrel"));
    public static final BlockItem COPPER_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("copper_mini_barrel"));
    public static final BlockItem EXPOSED_COPPER_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("exposed_copper_mini_barrel"));
    public static final BlockItem WEATHERED_COPPER_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("weathered_copper_mini_barrel"));
    public static final BlockItem OXIDIZED_COPPER_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("oxidized_copper_mini_barrel"));
    public static final BlockItem WAXED_COPPER_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("waxed_copper_mini_barrel"));
    public static final BlockItem WAXED_EXPOSED_COPPER_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("waxed_exposed_copper_mini_barrel"));
    public static final BlockItem WAXED_WEATHERED_COPPER_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("waxed_weathered_copper_mini_barrel"));
    public static final BlockItem WAXED_OXIDIZED_COPPER_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("waxed_oxidized_copper_mini_barrel"));
    public static final BlockItem IRON_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("iron_mini_barrel"));
    public static final BlockItem GOLD_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("gold_mini_barrel"));
    public static final BlockItem DIAMOND_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("diamond_mini_barrel"));
    public static final BlockItem OBSIDIAN_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("obsidian_mini_barrel"));
    public static final BlockItem NETHERITE_MINI_BARREL = item(compasses.expandedstorage.ForgeMain.id("netherite_mini_barrel"));

    private AllItems() {
    }

    public static void register(IEventBus modEventBus) {
        if (!initialized) {
            for (Map.Entry<ResourceLocation, Item> entry : ModRegistry.items().entrySet()) {
                ResourceLocation id = entry.getKey();
                Item item = entry.getValue();
                REGISTRY_OBJECTS.put(id, ITEMS.register(id.getPath(), () -> item));
            }
            initialized = true;
        }
        ITEMS.register(modEventBus);
    }

    private static <T extends Item> T item(ResourceLocation id) {
        //noinspection unchecked
        return (T) BuiltInRegistries.ITEM.get(id);
    }
}

