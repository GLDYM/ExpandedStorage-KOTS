package compasses.expandedstorage.registry;

import compasses.expandedstorage.block.MiniStorageBlock;
import compasses.expandedstorage.item.ChestMinecartItem;
import compasses.expandedstorage.item.ChestBlockItem;
import compasses.expandedstorage.item.ForgeChestMinecartItem;
import compasses.expandedstorage.item.MiniStorageBlockItem;
import compasses.expandedstorage.item.StorageConversionKit;
import compasses.expandedstorage.item.StorageMutator;
import compasses.expandedstorage.misc.Tier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public final class AllItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, compasses.expandedstorage.ForgeMain.MOD_ID);
    public static final RegistryObject<Item> STORAGE_MUTATOR_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("storage_mutator"));

    public static final RegistryObject<Item> WOOD_TO_COPPER_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("wood_to_copper_conversion_kit"));
    public static final RegistryObject<Item> WOOD_TO_IRON_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("wood_to_iron_conversion_kit"));
    public static final RegistryObject<Item> WOOD_TO_GOLD_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("wood_to_gold_conversion_kit"));
    public static final RegistryObject<Item> WOOD_TO_DIAMOND_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("wood_to_diamond_conversion_kit"));
    public static final RegistryObject<Item> WOOD_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("wood_to_obsidian_conversion_kit"));
    public static final RegistryObject<Item> WOOD_TO_NETHERITE_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("wood_to_netherite_conversion_kit"));
    public static final RegistryObject<Item> COPPER_TO_IRON_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("copper_to_iron_conversion_kit"));
    public static final RegistryObject<Item> COPPER_TO_GOLD_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("copper_to_gold_conversion_kit"));
    public static final RegistryObject<Item> COPPER_TO_DIAMOND_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("copper_to_diamond_conversion_kit"));
    public static final RegistryObject<Item> COPPER_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("copper_to_obsidian_conversion_kit"));
    public static final RegistryObject<Item> COPPER_TO_NETHERITE_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("copper_to_netherite_conversion_kit"));
    public static final RegistryObject<Item> IRON_TO_GOLD_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_to_gold_conversion_kit"));
    public static final RegistryObject<Item> IRON_TO_DIAMOND_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_to_diamond_conversion_kit"));
    public static final RegistryObject<Item> IRON_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_to_obsidian_conversion_kit"));
    public static final RegistryObject<Item> IRON_TO_NETHERITE_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_to_netherite_conversion_kit"));
    public static final RegistryObject<Item> GOLD_TO_DIAMOND_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_to_diamond_conversion_kit"));
    public static final RegistryObject<Item> GOLD_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_to_obsidian_conversion_kit"));
    public static final RegistryObject<Item> GOLD_TO_NETHERITE_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_to_netherite_conversion_kit"));
    public static final RegistryObject<Item> DIAMOND_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("diamond_to_obsidian_conversion_kit"));
    public static final RegistryObject<Item> DIAMOND_TO_NETHERITE_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("diamond_to_netherite_conversion_kit"));
    public static final RegistryObject<Item> OBSIDIAN_TO_NETHERITE_CONVERSION_KIT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("obsidian_to_netherite_conversion_kit"));

    public static final RegistryObject<BlockItem> WOOD_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("wood_chest"));
    public static final RegistryObject<BlockItem> PUMPKIN_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("pumpkin_chest"));
    public static final RegistryObject<BlockItem> PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("present"));
    public static final RegistryObject<BlockItem> BAMBOO_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("bamboo_chest"));
    public static final RegistryObject<BlockItem> MOSS_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("moss_chest"));
    public static final RegistryObject<BlockItem> IRON_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_chest"));
    public static final RegistryObject<BlockItem> GOLD_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_chest"));
    public static final RegistryObject<BlockItem> DIAMOND_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("diamond_chest"));
    public static final RegistryObject<BlockItem> OBSIDIAN_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("obsidian_chest"));
    public static final RegistryObject<BlockItem> NETHERITE_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("netherite_chest"));

    public static final RegistryObject<ChestMinecartItem> WOOD_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("wood_chest_minecart"));
    public static final RegistryObject<ChestMinecartItem> PUMPKIN_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("pumpkin_chest_minecart"));
    public static final RegistryObject<ChestMinecartItem> PRESENT_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("present_minecart"));
    public static final RegistryObject<ChestMinecartItem> BAMBOO_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("bamboo_chest_minecart"));
    public static final RegistryObject<ChestMinecartItem> MOSS_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("moss_chest_minecart"));
    public static final RegistryObject<ChestMinecartItem> IRON_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_chest_minecart"));
    public static final RegistryObject<ChestMinecartItem> GOLD_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_chest_minecart"));
    public static final RegistryObject<ChestMinecartItem> DIAMOND_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("diamond_chest_minecart"));
    public static final RegistryObject<ChestMinecartItem> OBSIDIAN_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("obsidian_chest_minecart"));
    public static final RegistryObject<ChestMinecartItem> NETHERITE_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("netherite_chest_minecart"));

    public static final RegistryObject<BlockItem> OLD_WOOD_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("old_wood_chest"));
    public static final RegistryObject<BlockItem> OLD_IRON_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("old_iron_chest"));
    public static final RegistryObject<BlockItem> OLD_GOLD_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("old_gold_chest"));
    public static final RegistryObject<BlockItem> OLD_DIAMOND_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("old_diamond_chest"));
    public static final RegistryObject<BlockItem> OLD_OBSIDIAN_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("old_obsidian_chest"));
    public static final RegistryObject<BlockItem> OLD_NETHERITE_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("old_netherite_chest"));

    public static final RegistryObject<BlockItem> COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("copper_barrel"));
    public static final RegistryObject<BlockItem> EXPOSED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("exposed_copper_barrel"));
    public static final RegistryObject<BlockItem> WEATHERED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("weathered_copper_barrel"));
    public static final RegistryObject<BlockItem> OXIDIZED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("oxidized_copper_barrel"));
    public static final RegistryObject<BlockItem> WAXED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_copper_barrel"));
    public static final RegistryObject<BlockItem> WAXED_EXPOSED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_exposed_copper_barrel"));
    public static final RegistryObject<BlockItem> WAXED_WEATHERED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_weathered_copper_barrel"));
    public static final RegistryObject<BlockItem> WAXED_OXIDIZED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_oxidized_copper_barrel"));
    public static final RegistryObject<BlockItem> IRON_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_barrel"));
    public static final RegistryObject<BlockItem> GOLD_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_barrel"));
    public static final RegistryObject<BlockItem> DIAMOND_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("diamond_barrel"));
    public static final RegistryObject<BlockItem> OBSIDIAN_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("obsidian_barrel"));
    public static final RegistryObject<BlockItem> NETHERITE_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("netherite_barrel"));

    public static final RegistryObject<BlockItem> VANILLA_WOOD_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("vanilla_wood_mini_chest"));
    public static final RegistryObject<BlockItem> WOOD_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("wood_mini_chest"));
    public static final RegistryObject<BlockItem> PUMPKIN_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("pumpkin_mini_chest"));
    public static final RegistryObject<BlockItem> RED_MINI_PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("red_mini_present"));
    public static final RegistryObject<BlockItem> WHITE_MINI_PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("white_mini_present"));
    public static final RegistryObject<BlockItem> CANDY_CANE_MINI_PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("candy_cane_mini_present"));
    public static final RegistryObject<BlockItem> GREEN_MINI_PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("green_mini_present"));
    public static final RegistryObject<BlockItem> LAVENDER_MINI_PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("lavender_mini_present"));
    public static final RegistryObject<BlockItem> PINK_AMETHYST_MINI_PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("pink_amethyst_mini_present"));
    public static final RegistryObject<BlockItem> IRON_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_mini_chest"));
    public static final RegistryObject<BlockItem> GOLD_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_mini_chest"));
    public static final RegistryObject<BlockItem> DIAMOND_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("diamond_mini_chest"));
    public static final RegistryObject<BlockItem> OBSIDIAN_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("obsidian_mini_chest"));
    public static final RegistryObject<BlockItem> NETHERITE_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("netherite_mini_chest"));
    public static final RegistryObject<BlockItem> MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("mini_barrel"));
    public static final RegistryObject<BlockItem> COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("copper_mini_barrel"));
    public static final RegistryObject<BlockItem> EXPOSED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("exposed_copper_mini_barrel"));
    public static final RegistryObject<BlockItem> WEATHERED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("weathered_copper_mini_barrel"));
    public static final RegistryObject<BlockItem> OXIDIZED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("oxidized_copper_mini_barrel"));
    public static final RegistryObject<BlockItem> WAXED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_copper_mini_barrel"));
    public static final RegistryObject<BlockItem> WAXED_EXPOSED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_exposed_copper_mini_barrel"));
    public static final RegistryObject<BlockItem> WAXED_WEATHERED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_weathered_copper_mini_barrel"));
    public static final RegistryObject<BlockItem> WAXED_OXIDIZED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_oxidized_copper_mini_barrel"));
    public static final RegistryObject<BlockItem> IRON_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_mini_barrel"));
    public static final RegistryObject<BlockItem> GOLD_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_mini_barrel"));
    public static final RegistryObject<BlockItem> DIAMOND_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("diamond_mini_barrel"));
    public static final RegistryObject<BlockItem> OBSIDIAN_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("obsidian_mini_barrel"));
    public static final RegistryObject<BlockItem> NETHERITE_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("netherite_mini_barrel"));

    private AllItems() {
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    private static <T extends Item> RegistryObject<T> register(ResourceLocation id) {
        return ITEMS.register(id.getPath(), () -> {
            //noinspection unchecked
            return (T) createItem(id);
        });
    }

    private static Item createItem(ResourceLocation id) {
        String path = id.getPath();
        if ("storage_mutator".equals(path)) {
            return new StorageMutator(new Item.Properties().stacksTo(1));
        }
        if (path.endsWith("_conversion_kit")) {
            return createConversionKit(path);
        }
        if (path.endsWith("_minecart")) {
            return new ForgeChestMinecartItem(new Item.Properties(), id);
        }
        return createBlockItem(id);
    }

    private static Item createConversionKit(String path) {
        String suffix = "_conversion_kit";
        String conversionPath = path.substring(0, path.length() - suffix.length());
        int separator = conversionPath.indexOf("_to_");
        if (separator <= 0) {
            throw new IllegalArgumentException("Invalid conversion kit id: " + path);
        }
        String from = conversionPath.substring(0, separator);
        String to = conversionPath.substring(separator + 4);
        Tier fromTier = tierByName(from);
        Tier toTier = tierByName(to);
        Item.Properties settings = fromTier.getItemSettings().andThen(toTier.getItemSettings()).apply(new Item.Properties().stacksTo(16));
        return new StorageConversionKit(settings, fromTier.getId(), toTier.getId(), false);
    }

    private static BlockItem createBlockItem(ResourceLocation id) {
        String path = id.getPath();
        Item.Properties settings = tierForBlockItem(path).getItemSettings().apply(new Item.Properties());
        Block block = Objects.requireNonNull(BuiltInRegistries.BLOCK.get(id), "Missing block for block item: " + id);
        if (isChestBlockItem(path)) {
            return new ChestBlockItem(block, settings);
        }
        if (isMiniStorageBlockItem(path)) {
            return new MiniStorageBlockItem((MiniStorageBlock) block, settings);
        }
        return new BlockItem(block, settings);
    }

    private static boolean isChestBlockItem(String path) {
        return switch (path) {
            case "wood_chest", "pumpkin_chest", "present", "bamboo_chest", "moss_chest",
                 "iron_chest", "gold_chest", "diamond_chest", "obsidian_chest", "netherite_chest" -> true;
            default -> false;
        };
    }

    private static boolean isMiniStorageBlockItem(String path) {
        return path.contains("mini_") || "mini_barrel".equals(path);
    }

    private static Tier tierForBlockItem(String path) {
        return switch (path) {
            case "copper_barrel", "exposed_copper_barrel", "weathered_copper_barrel", "oxidized_copper_barrel",
                 "waxed_copper_barrel", "waxed_exposed_copper_barrel", "waxed_weathered_copper_barrel", "waxed_oxidized_copper_barrel",
                 "copper_mini_barrel", "exposed_copper_mini_barrel", "weathered_copper_mini_barrel", "oxidized_copper_mini_barrel",
                 "waxed_copper_mini_barrel", "waxed_exposed_copper_mini_barrel", "waxed_weathered_copper_mini_barrel", "waxed_oxidized_copper_mini_barrel" -> Tier.COPPER;
            case "iron_chest", "old_iron_chest", "iron_barrel", "iron_mini_chest", "iron_mini_barrel" -> Tier.IRON;
            case "gold_chest", "old_gold_chest", "gold_barrel", "gold_mini_chest", "gold_mini_barrel" -> Tier.GOLD;
            case "diamond_chest", "old_diamond_chest", "diamond_barrel", "diamond_mini_chest", "diamond_mini_barrel" -> Tier.DIAMOND;
            case "obsidian_chest", "old_obsidian_chest", "obsidian_barrel", "obsidian_mini_chest", "obsidian_mini_barrel" -> Tier.OBSIDIAN;
            case "netherite_chest", "old_netherite_chest", "netherite_barrel", "netherite_mini_chest", "netherite_mini_barrel" -> Tier.NETHERITE;
            default -> Tier.WOOD;
        };
    }

    private static Tier tierByName(String tierName) {
        return switch (tierName) {
            case "wood" -> Tier.WOOD;
            case "copper" -> Tier.COPPER;
            case "iron" -> Tier.IRON;
            case "gold" -> Tier.GOLD;
            case "diamond" -> Tier.DIAMOND;
            case "obsidian" -> Tier.OBSIDIAN;
            case "netherite" -> Tier.NETHERITE;
            default -> throw new IllegalArgumentException("Unknown tier in id: " + tierName);
        };
    }
}

