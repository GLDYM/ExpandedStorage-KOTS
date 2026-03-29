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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import compasses.expandedstorage.ExpandedStorage;

import java.util.Objects;

public final class AllItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExpandedStorage.MOD_ID);
    public static final RegistryObject<Item> STORAGE_MUTATOR_REGISTRY = registerStorageMutator("storage_mutator");

    public static final RegistryObject<Item> WOOD_TO_COPPER_CONVERSION_KIT_REGISTRY = registerConversionKit("wood_to_copper_conversion_kit", Tier.WOOD, Tier.COPPER);
    public static final RegistryObject<Item> WOOD_TO_IRON_CONVERSION_KIT_REGISTRY = registerConversionKit("wood_to_iron_conversion_kit", Tier.WOOD, Tier.IRON);
    public static final RegistryObject<Item> WOOD_TO_GOLD_CONVERSION_KIT_REGISTRY = registerConversionKit("wood_to_gold_conversion_kit", Tier.WOOD, Tier.GOLD);
    public static final RegistryObject<Item> WOOD_TO_DIAMOND_CONVERSION_KIT_REGISTRY = registerConversionKit("wood_to_diamond_conversion_kit", Tier.WOOD, Tier.DIAMOND);
    public static final RegistryObject<Item> WOOD_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY = registerConversionKit("wood_to_obsidian_conversion_kit", Tier.WOOD, Tier.OBSIDIAN);
    public static final RegistryObject<Item> WOOD_TO_NETHERITE_CONVERSION_KIT_REGISTRY = registerConversionKit("wood_to_netherite_conversion_kit", Tier.WOOD, Tier.NETHERITE);
    public static final RegistryObject<Item> COPPER_TO_IRON_CONVERSION_KIT_REGISTRY = registerConversionKit("copper_to_iron_conversion_kit", Tier.COPPER, Tier.IRON);
    public static final RegistryObject<Item> COPPER_TO_GOLD_CONVERSION_KIT_REGISTRY = registerConversionKit("copper_to_gold_conversion_kit", Tier.COPPER, Tier.GOLD);
    public static final RegistryObject<Item> COPPER_TO_DIAMOND_CONVERSION_KIT_REGISTRY = registerConversionKit("copper_to_diamond_conversion_kit", Tier.COPPER, Tier.DIAMOND);
    public static final RegistryObject<Item> COPPER_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY = registerConversionKit("copper_to_obsidian_conversion_kit", Tier.COPPER, Tier.OBSIDIAN);
    public static final RegistryObject<Item> COPPER_TO_NETHERITE_CONVERSION_KIT_REGISTRY = registerConversionKit("copper_to_netherite_conversion_kit", Tier.COPPER, Tier.NETHERITE);
    public static final RegistryObject<Item> IRON_TO_GOLD_CONVERSION_KIT_REGISTRY = registerConversionKit("iron_to_gold_conversion_kit", Tier.IRON, Tier.GOLD);
    public static final RegistryObject<Item> IRON_TO_DIAMOND_CONVERSION_KIT_REGISTRY = registerConversionKit("iron_to_diamond_conversion_kit", Tier.IRON, Tier.DIAMOND);
    public static final RegistryObject<Item> IRON_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY = registerConversionKit("iron_to_obsidian_conversion_kit", Tier.IRON, Tier.OBSIDIAN);
    public static final RegistryObject<Item> IRON_TO_NETHERITE_CONVERSION_KIT_REGISTRY = registerConversionKit("iron_to_netherite_conversion_kit", Tier.IRON, Tier.NETHERITE);
    public static final RegistryObject<Item> GOLD_TO_DIAMOND_CONVERSION_KIT_REGISTRY = registerConversionKit("gold_to_diamond_conversion_kit", Tier.GOLD, Tier.DIAMOND);
    public static final RegistryObject<Item> GOLD_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY = registerConversionKit("gold_to_obsidian_conversion_kit", Tier.GOLD, Tier.OBSIDIAN);
    public static final RegistryObject<Item> GOLD_TO_NETHERITE_CONVERSION_KIT_REGISTRY = registerConversionKit("gold_to_netherite_conversion_kit", Tier.GOLD, Tier.NETHERITE);
    public static final RegistryObject<Item> DIAMOND_TO_OBSIDIAN_CONVERSION_KIT_REGISTRY = registerConversionKit("diamond_to_obsidian_conversion_kit", Tier.DIAMOND, Tier.OBSIDIAN);
    public static final RegistryObject<Item> DIAMOND_TO_NETHERITE_CONVERSION_KIT_REGISTRY = registerConversionKit("diamond_to_netherite_conversion_kit", Tier.DIAMOND, Tier.NETHERITE);
    public static final RegistryObject<Item> OBSIDIAN_TO_NETHERITE_CONVERSION_KIT_REGISTRY = registerConversionKit("obsidian_to_netherite_conversion_kit", Tier.OBSIDIAN, Tier.NETHERITE);

    public static final RegistryObject<BlockItem> WOOD_CHEST_REGISTRY = registerChestBlockItem("wood_chest", Tier.WOOD);
    public static final RegistryObject<BlockItem> PUMPKIN_CHEST_REGISTRY = registerChestBlockItem("pumpkin_chest", Tier.WOOD);
    public static final RegistryObject<BlockItem> PRESENT_REGISTRY = registerChestBlockItem("present", Tier.WOOD);
    public static final RegistryObject<BlockItem> BAMBOO_CHEST_REGISTRY = registerChestBlockItem("bamboo_chest", Tier.WOOD);
    public static final RegistryObject<BlockItem> MOSS_CHEST_REGISTRY = registerChestBlockItem("moss_chest", Tier.WOOD);
    public static final RegistryObject<BlockItem> IRON_CHEST_REGISTRY = registerChestBlockItem("iron_chest", Tier.IRON);
    public static final RegistryObject<BlockItem> GOLD_CHEST_REGISTRY = registerChestBlockItem("gold_chest", Tier.GOLD);
    public static final RegistryObject<BlockItem> DIAMOND_CHEST_REGISTRY = registerChestBlockItem("diamond_chest", Tier.DIAMOND);
    public static final RegistryObject<BlockItem> OBSIDIAN_CHEST_REGISTRY = registerChestBlockItem("obsidian_chest", Tier.OBSIDIAN);
    public static final RegistryObject<BlockItem> NETHERITE_CHEST_REGISTRY = registerChestBlockItem("netherite_chest", Tier.NETHERITE);

    public static final RegistryObject<ChestMinecartItem> WOOD_CHEST_MINECART_REGISTRY = registerMinecartItem("wood_chest_minecart");
    public static final RegistryObject<ChestMinecartItem> PUMPKIN_CHEST_MINECART_REGISTRY = registerMinecartItem("pumpkin_chest_minecart");
    public static final RegistryObject<ChestMinecartItem> PRESENT_MINECART_REGISTRY = registerMinecartItem("present_minecart");
    public static final RegistryObject<ChestMinecartItem> BAMBOO_CHEST_MINECART_REGISTRY = registerMinecartItem("bamboo_chest_minecart");
    public static final RegistryObject<ChestMinecartItem> MOSS_CHEST_MINECART_REGISTRY = registerMinecartItem("moss_chest_minecart");
    public static final RegistryObject<ChestMinecartItem> IRON_CHEST_MINECART_REGISTRY = registerMinecartItem("iron_chest_minecart");
    public static final RegistryObject<ChestMinecartItem> GOLD_CHEST_MINECART_REGISTRY = registerMinecartItem("gold_chest_minecart");
    public static final RegistryObject<ChestMinecartItem> DIAMOND_CHEST_MINECART_REGISTRY = registerMinecartItem("diamond_chest_minecart");
    public static final RegistryObject<ChestMinecartItem> OBSIDIAN_CHEST_MINECART_REGISTRY = registerMinecartItem("obsidian_chest_minecart");
    public static final RegistryObject<ChestMinecartItem> NETHERITE_CHEST_MINECART_REGISTRY = registerMinecartItem("netherite_chest_minecart");

    public static final RegistryObject<BlockItem> OLD_WOOD_CHEST_REGISTRY = registerBlockItem("old_wood_chest", Tier.WOOD);
    public static final RegistryObject<BlockItem> OLD_IRON_CHEST_REGISTRY = registerBlockItem("old_iron_chest", Tier.IRON);
    public static final RegistryObject<BlockItem> OLD_GOLD_CHEST_REGISTRY = registerBlockItem("old_gold_chest", Tier.GOLD);
    public static final RegistryObject<BlockItem> OLD_DIAMOND_CHEST_REGISTRY = registerBlockItem("old_diamond_chest", Tier.DIAMOND);
    public static final RegistryObject<BlockItem> OLD_OBSIDIAN_CHEST_REGISTRY = registerBlockItem("old_obsidian_chest", Tier.OBSIDIAN);
    public static final RegistryObject<BlockItem> OLD_NETHERITE_CHEST_REGISTRY = registerBlockItem("old_netherite_chest", Tier.NETHERITE);

    public static final RegistryObject<BlockItem> COPPER_BARREL_REGISTRY = registerBlockItem("copper_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> EXPOSED_COPPER_BARREL_REGISTRY = registerBlockItem("exposed_copper_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> WEATHERED_COPPER_BARREL_REGISTRY = registerBlockItem("weathered_copper_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> OXIDIZED_COPPER_BARREL_REGISTRY = registerBlockItem("oxidized_copper_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> WAXED_COPPER_BARREL_REGISTRY = registerBlockItem("waxed_copper_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> WAXED_EXPOSED_COPPER_BARREL_REGISTRY = registerBlockItem("waxed_exposed_copper_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> WAXED_WEATHERED_COPPER_BARREL_REGISTRY = registerBlockItem("waxed_weathered_copper_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> WAXED_OXIDIZED_COPPER_BARREL_REGISTRY = registerBlockItem("waxed_oxidized_copper_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> IRON_BARREL_REGISTRY = registerBlockItem("iron_barrel", Tier.IRON);
    public static final RegistryObject<BlockItem> GOLD_BARREL_REGISTRY = registerBlockItem("gold_barrel", Tier.GOLD);
    public static final RegistryObject<BlockItem> DIAMOND_BARREL_REGISTRY = registerBlockItem("diamond_barrel", Tier.DIAMOND);
    public static final RegistryObject<BlockItem> OBSIDIAN_BARREL_REGISTRY = registerBlockItem("obsidian_barrel", Tier.OBSIDIAN);
    public static final RegistryObject<BlockItem> NETHERITE_BARREL_REGISTRY = registerBlockItem("netherite_barrel", Tier.NETHERITE);

    public static final RegistryObject<BlockItem> VANILLA_WOOD_MINI_CHEST_REGISTRY = registerMiniStorageBlockItem("vanilla_wood_mini_chest", Tier.WOOD);
    public static final RegistryObject<BlockItem> WOOD_MINI_CHEST_REGISTRY = registerMiniStorageBlockItem("wood_mini_chest", Tier.WOOD);
    public static final RegistryObject<BlockItem> PUMPKIN_MINI_CHEST_REGISTRY = registerMiniStorageBlockItem("pumpkin_mini_chest", Tier.WOOD);
    public static final RegistryObject<BlockItem> RED_MINI_PRESENT_REGISTRY = registerMiniStorageBlockItem("red_mini_present", Tier.WOOD);
    public static final RegistryObject<BlockItem> WHITE_MINI_PRESENT_REGISTRY = registerMiniStorageBlockItem("white_mini_present", Tier.WOOD);
    public static final RegistryObject<BlockItem> CANDY_CANE_MINI_PRESENT_REGISTRY = registerMiniStorageBlockItem("candy_cane_mini_present", Tier.WOOD);
    public static final RegistryObject<BlockItem> GREEN_MINI_PRESENT_REGISTRY = registerMiniStorageBlockItem("green_mini_present", Tier.WOOD);
    public static final RegistryObject<BlockItem> LAVENDER_MINI_PRESENT_REGISTRY = registerMiniStorageBlockItem("lavender_mini_present", Tier.WOOD);
    public static final RegistryObject<BlockItem> PINK_AMETHYST_MINI_PRESENT_REGISTRY = registerMiniStorageBlockItem("pink_amethyst_mini_present", Tier.WOOD);
    public static final RegistryObject<BlockItem> IRON_MINI_CHEST_REGISTRY = registerMiniStorageBlockItem("iron_mini_chest", Tier.IRON);
    public static final RegistryObject<BlockItem> GOLD_MINI_CHEST_REGISTRY = registerMiniStorageBlockItem("gold_mini_chest", Tier.GOLD);
    public static final RegistryObject<BlockItem> DIAMOND_MINI_CHEST_REGISTRY = registerMiniStorageBlockItem("diamond_mini_chest", Tier.DIAMOND);
    public static final RegistryObject<BlockItem> OBSIDIAN_MINI_CHEST_REGISTRY = registerMiniStorageBlockItem("obsidian_mini_chest", Tier.OBSIDIAN);
    public static final RegistryObject<BlockItem> NETHERITE_MINI_CHEST_REGISTRY = registerMiniStorageBlockItem("netherite_mini_chest", Tier.NETHERITE);
    public static final RegistryObject<BlockItem> MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("mini_barrel", Tier.WOOD);
    public static final RegistryObject<BlockItem> COPPER_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("copper_mini_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> EXPOSED_COPPER_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("exposed_copper_mini_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> WEATHERED_COPPER_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("weathered_copper_mini_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> OXIDIZED_COPPER_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("oxidized_copper_mini_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> WAXED_COPPER_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("waxed_copper_mini_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> WAXED_EXPOSED_COPPER_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("waxed_exposed_copper_mini_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> WAXED_WEATHERED_COPPER_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("waxed_weathered_copper_mini_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> WAXED_OXIDIZED_COPPER_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("waxed_oxidized_copper_mini_barrel", Tier.COPPER);
    public static final RegistryObject<BlockItem> IRON_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("iron_mini_barrel", Tier.IRON);
    public static final RegistryObject<BlockItem> GOLD_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("gold_mini_barrel", Tier.GOLD);
    public static final RegistryObject<BlockItem> DIAMOND_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("diamond_mini_barrel", Tier.DIAMOND);
    public static final RegistryObject<BlockItem> OBSIDIAN_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("obsidian_mini_barrel", Tier.OBSIDIAN);
    public static final RegistryObject<BlockItem> NETHERITE_MINI_BARREL_REGISTRY = registerMiniStorageBlockItem("netherite_mini_barrel", Tier.NETHERITE);

    private AllItems() {
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    private static RegistryObject<Item> registerStorageMutator(String path) {
        return ITEMS.register(path, () -> new StorageMutator(new Item.Properties().stacksTo(1)));
    }

    private static RegistryObject<Item> registerConversionKit(String path, Tier fromTier, Tier toTier) {
        return ITEMS.register(path, () -> {
            Item.Properties settings = fromTier.getItemSettings().andThen(toTier.getItemSettings()).apply(new Item.Properties().stacksTo(16));
            return new StorageConversionKit(settings, fromTier.getId(), toTier.getId(), false);
        });
    }

    private static RegistryObject<ChestMinecartItem> registerMinecartItem(String path) {
        return ITEMS.register(path, () -> new ForgeChestMinecartItem(new Item.Properties(), ExpandedStorage.id(path)));
    }

    private static RegistryObject<BlockItem> registerChestBlockItem(String path, Tier tier) {
        return ITEMS.register(path, () -> {
            Item.Properties settings = tier.getItemSettings().apply(new Item.Properties());
            return new ChestBlockItem(requiredBlock(path), settings);
        });
    }

    private static RegistryObject<BlockItem> registerMiniStorageBlockItem(String path, Tier tier) {
        return ITEMS.register(path, () -> {
            Item.Properties settings = tier.getItemSettings().apply(new Item.Properties());
            return new MiniStorageBlockItem((MiniStorageBlock) requiredBlock(path), settings);
        });
    }

    private static RegistryObject<BlockItem> registerBlockItem(String path, Tier tier) {
        return ITEMS.register(path, () -> {
            Item.Properties settings = tier.getItemSettings().apply(new Item.Properties());
            return new BlockItem(requiredBlock(path), settings);
        });
    }

    private static Block requiredBlock(String path) {
        return Objects.requireNonNull(BuiltInRegistries.BLOCK.get(ExpandedStorage.id(path)), "Missing block for block item: " + path);
    }
}

