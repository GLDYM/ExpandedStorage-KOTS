package compasses.expandedstorage.registry;

import compasses.expandedstorage.block.AbstractChestBlock;
import compasses.expandedstorage.block.BarrelBlock;
import compasses.expandedstorage.block.ChestBlock;
import compasses.expandedstorage.block.CopperBarrelBlock;
import compasses.expandedstorage.block.CopperMiniStorageBlock;
import compasses.expandedstorage.block.MiniStorageBlock;
import compasses.expandedstorage.block.MossChestBlock;
import compasses.expandedstorage.block.OpenableBlock;
import compasses.expandedstorage.misc.Tier;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import compasses.expandedstorage.ExpandedStorage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class AllBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExpandedStorage.MOD_ID);
    private static final BlockBehaviour.Properties WOOD_TIER_PROPERTIES = Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5f).sound(SoundType.WOOD).ignitedByLava();
    private static final BlockBehaviour.Properties METAL_IRON_PROPERTIES = Block.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(5, 6).sound(SoundType.METAL);
    private static final BlockBehaviour.Properties METAL_GOLD_PROPERTIES = Block.Properties.of().mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.BELL).strength(3, 6).sound(SoundType.METAL);
    private static final BlockBehaviour.Properties METAL_DIAMOND_PROPERTIES = Block.Properties.of().mapColor(MapColor.DIAMOND).strength(5, 6).sound(SoundType.METAL);
    private static final BlockBehaviour.Properties OBSIDIAN_PROPERTIES = Block.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).strength(50, 1200);
    private static final BlockBehaviour.Properties NETHERITE_PROPERTIES = Block.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(50, 1200).sound(SoundType.NETHERITE_BLOCK);
    private static final BlockBehaviour.Properties WOOD_BARREL_PROPERTIES = Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3, 6).sound(SoundType.WOOD).ignitedByLava();
    private static final BlockBehaviour.Properties NETHERITE_BARREL_PROPERTIES = Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(50, 1200).sound(SoundType.WOOD);

    public static final RegistryObject<ChestBlock> WOOD_CHEST_REGISTRY = registerChest("wood_chest");
    public static final RegistryObject<ChestBlock> PUMPKIN_CHEST_REGISTRY = registerChest("pumpkin_chest");
    public static final RegistryObject<ChestBlock> PRESENT_REGISTRY = registerChest("present");
    public static final RegistryObject<ChestBlock> BAMBOO_CHEST_REGISTRY = registerChest("bamboo_chest");
    public static final RegistryObject<ChestBlock> MOSS_CHEST_REGISTRY = registerChest("moss_chest");
    public static final RegistryObject<ChestBlock> IRON_CHEST_REGISTRY = registerChest("iron_chest");
    public static final RegistryObject<ChestBlock> GOLD_CHEST_REGISTRY = registerChest("gold_chest");
    public static final RegistryObject<ChestBlock> DIAMOND_CHEST_REGISTRY = registerChest("diamond_chest");
    public static final RegistryObject<ChestBlock> OBSIDIAN_CHEST_REGISTRY = registerChest("obsidian_chest");
    public static final RegistryObject<ChestBlock> NETHERITE_CHEST_REGISTRY = registerChest("netherite_chest");

    public static final RegistryObject<AbstractChestBlock> OLD_WOOD_CHEST_REGISTRY = registerOldChest("old_wood_chest");
    public static final RegistryObject<AbstractChestBlock> OLD_IRON_CHEST_REGISTRY = registerOldChest("old_iron_chest");
    public static final RegistryObject<AbstractChestBlock> OLD_GOLD_CHEST_REGISTRY = registerOldChest("old_gold_chest");
    public static final RegistryObject<AbstractChestBlock> OLD_DIAMOND_CHEST_REGISTRY = registerOldChest("old_diamond_chest");
    public static final RegistryObject<AbstractChestBlock> OLD_OBSIDIAN_CHEST_REGISTRY = registerOldChest("old_obsidian_chest");
    public static final RegistryObject<AbstractChestBlock> OLD_NETHERITE_CHEST_REGISTRY = registerOldChest("old_netherite_chest");

    public static final RegistryObject<CopperBarrelBlock> COPPER_BARREL_REGISTRY = registerCopperBarrel("copper_barrel");
    public static final RegistryObject<CopperBarrelBlock> EXPOSED_COPPER_BARREL_REGISTRY = registerCopperBarrel("exposed_copper_barrel");
    public static final RegistryObject<CopperBarrelBlock> WEATHERED_COPPER_BARREL_REGISTRY = registerCopperBarrel("weathered_copper_barrel");
    public static final RegistryObject<CopperBarrelBlock> OXIDIZED_COPPER_BARREL_REGISTRY = registerCopperBarrel("oxidized_copper_barrel");
    public static final RegistryObject<BarrelBlock> WAXED_COPPER_BARREL_REGISTRY = registerBarrel("waxed_copper_barrel");
    public static final RegistryObject<BarrelBlock> WAXED_EXPOSED_COPPER_BARREL_REGISTRY = registerBarrel("waxed_exposed_copper_barrel");
    public static final RegistryObject<BarrelBlock> WAXED_WEATHERED_COPPER_BARREL_REGISTRY = registerBarrel("waxed_weathered_copper_barrel");
    public static final RegistryObject<BarrelBlock> WAXED_OXIDIZED_COPPER_BARREL_REGISTRY = registerBarrel("waxed_oxidized_copper_barrel");
    public static final RegistryObject<BarrelBlock> IRON_BARREL_REGISTRY = registerBarrel("iron_barrel");
    public static final RegistryObject<BarrelBlock> GOLD_BARREL_REGISTRY = registerBarrel("gold_barrel");
    public static final RegistryObject<BarrelBlock> DIAMOND_BARREL_REGISTRY = registerBarrel("diamond_barrel");
    public static final RegistryObject<BarrelBlock> OBSIDIAN_BARREL_REGISTRY = registerBarrel("obsidian_barrel");
    public static final RegistryObject<BarrelBlock> NETHERITE_BARREL_REGISTRY = registerBarrel("netherite_barrel");

    public static final RegistryObject<MiniStorageBlock> VANILLA_WOOD_MINI_CHEST_REGISTRY = registerMiniStorage("vanilla_wood_mini_chest");
    public static final RegistryObject<MiniStorageBlock> WOOD_MINI_CHEST_REGISTRY = registerMiniStorage("wood_mini_chest");
    public static final RegistryObject<MiniStorageBlock> PUMPKIN_MINI_CHEST_REGISTRY = registerMiniStorage("pumpkin_mini_chest");
    public static final RegistryObject<MiniStorageBlock> RED_MINI_PRESENT_REGISTRY = registerMiniStorage("red_mini_present");
    public static final RegistryObject<MiniStorageBlock> WHITE_MINI_PRESENT_REGISTRY = registerMiniStorage("white_mini_present");
    public static final RegistryObject<MiniStorageBlock> CANDY_CANE_MINI_PRESENT_REGISTRY = registerMiniStorage("candy_cane_mini_present");
    public static final RegistryObject<MiniStorageBlock> GREEN_MINI_PRESENT_REGISTRY = registerMiniStorage("green_mini_present");
    public static final RegistryObject<MiniStorageBlock> LAVENDER_MINI_PRESENT_REGISTRY = registerMiniStorage("lavender_mini_present");
    public static final RegistryObject<MiniStorageBlock> PINK_AMETHYST_MINI_PRESENT_REGISTRY = registerMiniStorage("pink_amethyst_mini_present");
    public static final RegistryObject<MiniStorageBlock> IRON_MINI_CHEST_REGISTRY = registerMiniStorage("iron_mini_chest");
    public static final RegistryObject<MiniStorageBlock> GOLD_MINI_CHEST_REGISTRY = registerMiniStorage("gold_mini_chest");
    public static final RegistryObject<MiniStorageBlock> DIAMOND_MINI_CHEST_REGISTRY = registerMiniStorage("diamond_mini_chest");
    public static final RegistryObject<MiniStorageBlock> OBSIDIAN_MINI_CHEST_REGISTRY = registerMiniStorage("obsidian_mini_chest");
    public static final RegistryObject<MiniStorageBlock> NETHERITE_MINI_CHEST_REGISTRY = registerMiniStorage("netherite_mini_chest");
    public static final RegistryObject<MiniStorageBlock> MINI_BARREL_REGISTRY = registerMiniStorage("mini_barrel");
    public static final RegistryObject<CopperMiniStorageBlock> COPPER_MINI_BARREL_REGISTRY = registerCopperMiniStorage("copper_mini_barrel");
    public static final RegistryObject<CopperMiniStorageBlock> EXPOSED_COPPER_MINI_BARREL_REGISTRY = registerCopperMiniStorage("exposed_copper_mini_barrel");
    public static final RegistryObject<CopperMiniStorageBlock> WEATHERED_COPPER_MINI_BARREL_REGISTRY = registerCopperMiniStorage("weathered_copper_mini_barrel");
    public static final RegistryObject<CopperMiniStorageBlock> OXIDIZED_COPPER_MINI_BARREL_REGISTRY = registerCopperMiniStorage("oxidized_copper_mini_barrel");
    public static final RegistryObject<MiniStorageBlock> WAXED_COPPER_MINI_BARREL_REGISTRY = registerMiniStorage("waxed_copper_mini_barrel");
    public static final RegistryObject<MiniStorageBlock> WAXED_EXPOSED_COPPER_MINI_BARREL_REGISTRY = registerMiniStorage("waxed_exposed_copper_mini_barrel");
    public static final RegistryObject<MiniStorageBlock> WAXED_WEATHERED_COPPER_MINI_BARREL_REGISTRY = registerMiniStorage("waxed_weathered_copper_mini_barrel");
    public static final RegistryObject<MiniStorageBlock> WAXED_OXIDIZED_COPPER_MINI_BARREL_REGISTRY = registerMiniStorage("waxed_oxidized_copper_mini_barrel");
    public static final RegistryObject<MiniStorageBlock> IRON_MINI_BARREL_REGISTRY = registerMiniStorage("iron_mini_barrel");
    public static final RegistryObject<MiniStorageBlock> GOLD_MINI_BARREL_REGISTRY = registerMiniStorage("gold_mini_barrel");
    public static final RegistryObject<MiniStorageBlock> DIAMOND_MINI_BARREL_REGISTRY = registerMiniStorage("diamond_mini_barrel");
    public static final RegistryObject<MiniStorageBlock> OBSIDIAN_MINI_BARREL_REGISTRY = registerMiniStorage("obsidian_mini_barrel");
    public static final RegistryObject<MiniStorageBlock> NETHERITE_MINI_BARREL_REGISTRY = registerMiniStorage("netherite_mini_barrel");

    private AllBlocks() {
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }

    static ChestBlock[] chestBlocksForEntityType() {
        return new ChestBlock[]{
                WOOD_CHEST_REGISTRY.get(),
                PUMPKIN_CHEST_REGISTRY.get(),
                PRESENT_REGISTRY.get(),
                BAMBOO_CHEST_REGISTRY.get(),
                MOSS_CHEST_REGISTRY.get(),
                IRON_CHEST_REGISTRY.get(),
                GOLD_CHEST_REGISTRY.get(),
                DIAMOND_CHEST_REGISTRY.get(),
                OBSIDIAN_CHEST_REGISTRY.get(),
                NETHERITE_CHEST_REGISTRY.get()
        };
    }

    static AbstractChestBlock[] oldChestBlocksForEntityType() {
        return new AbstractChestBlock[]{
                OLD_WOOD_CHEST_REGISTRY.get(),
                OLD_IRON_CHEST_REGISTRY.get(),
                OLD_GOLD_CHEST_REGISTRY.get(),
                OLD_DIAMOND_CHEST_REGISTRY.get(),
                OLD_OBSIDIAN_CHEST_REGISTRY.get(),
                OLD_NETHERITE_CHEST_REGISTRY.get()
        };
    }

    static BarrelBlock[] barrelBlocksForEntityType() {
        return new BarrelBlock[]{
                COPPER_BARREL_REGISTRY.get(),
                EXPOSED_COPPER_BARREL_REGISTRY.get(),
                WEATHERED_COPPER_BARREL_REGISTRY.get(),
                OXIDIZED_COPPER_BARREL_REGISTRY.get(),
                WAXED_COPPER_BARREL_REGISTRY.get(),
                WAXED_EXPOSED_COPPER_BARREL_REGISTRY.get(),
                WAXED_WEATHERED_COPPER_BARREL_REGISTRY.get(),
                WAXED_OXIDIZED_COPPER_BARREL_REGISTRY.get(),
                IRON_BARREL_REGISTRY.get(),
                GOLD_BARREL_REGISTRY.get(),
                DIAMOND_BARREL_REGISTRY.get(),
                OBSIDIAN_BARREL_REGISTRY.get(),
                NETHERITE_BARREL_REGISTRY.get()
        };
    }

    static MiniStorageBlock[] miniStorageBlocksForEntityType() {
        return new MiniStorageBlock[]{
                VANILLA_WOOD_MINI_CHEST_REGISTRY.get(),
                WOOD_MINI_CHEST_REGISTRY.get(),
                PUMPKIN_MINI_CHEST_REGISTRY.get(),
                RED_MINI_PRESENT_REGISTRY.get(),
                WHITE_MINI_PRESENT_REGISTRY.get(),
                CANDY_CANE_MINI_PRESENT_REGISTRY.get(),
                GREEN_MINI_PRESENT_REGISTRY.get(),
                LAVENDER_MINI_PRESENT_REGISTRY.get(),
                PINK_AMETHYST_MINI_PRESENT_REGISTRY.get(),
                IRON_MINI_CHEST_REGISTRY.get(),
                GOLD_MINI_CHEST_REGISTRY.get(),
                DIAMOND_MINI_CHEST_REGISTRY.get(),
                OBSIDIAN_MINI_CHEST_REGISTRY.get(),
                NETHERITE_MINI_CHEST_REGISTRY.get(),
                MINI_BARREL_REGISTRY.get(),
                COPPER_MINI_BARREL_REGISTRY.get(),
                EXPOSED_COPPER_MINI_BARREL_REGISTRY.get(),
                WEATHERED_COPPER_MINI_BARREL_REGISTRY.get(),
                OXIDIZED_COPPER_MINI_BARREL_REGISTRY.get(),
                WAXED_COPPER_MINI_BARREL_REGISTRY.get(),
                WAXED_EXPOSED_COPPER_MINI_BARREL_REGISTRY.get(),
                WAXED_WEATHERED_COPPER_MINI_BARREL_REGISTRY.get(),
                WAXED_OXIDIZED_COPPER_MINI_BARREL_REGISTRY.get(),
                IRON_MINI_BARREL_REGISTRY.get(),
                GOLD_MINI_BARREL_REGISTRY.get(),
                DIAMOND_MINI_BARREL_REGISTRY.get(),
                OBSIDIAN_MINI_BARREL_REGISTRY.get(),
                NETHERITE_MINI_BARREL_REGISTRY.get()
        };
    }

    public static List<OpenableBlock> all() {
        return Arrays.stream(AllBlocks.class.getFields())
                .filter(it -> RegistryObject.class.isAssignableFrom(it.getType()))
                .map(it -> {
                    try {
                        Object value = it.get(null);
                        if (value instanceof RegistryObject<?> registryObject) {
                            Object block = registryObject.get();
                            if (block instanceof OpenableBlock openableBlock) {
                                return openableBlock;
                            }
                        }
                        return null;
                    } catch (IllegalAccessException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    private static RegistryObject<ChestBlock> registerChest(String path) {
        return BLOCKS.register(path, () -> (ChestBlock) createChestBlock(path));
    }

    private static RegistryObject<AbstractChestBlock> registerOldChest(String path) {
        return BLOCKS.register(path, () -> (AbstractChestBlock) createOldChestBlock(path));
    }

    private static RegistryObject<BarrelBlock> registerBarrel(String path) {
        return BLOCKS.register(path, () -> (BarrelBlock) createBarrelBlock(path));
    }

    private static RegistryObject<CopperBarrelBlock> registerCopperBarrel(String path) {
        return BLOCKS.register(path, () -> (CopperBarrelBlock) createBarrelBlock(path));
    }

    private static RegistryObject<MiniStorageBlock> registerMiniStorage(String path) {
        return BLOCKS.register(path, () -> (MiniStorageBlock) createMiniStorageBlock(path));
    }

    private static RegistryObject<CopperMiniStorageBlock> registerCopperMiniStorage(String path) {
        return BLOCKS.register(path, () -> (CopperMiniStorageBlock) createMiniStorageBlock(path));
    }

    private static Block createChestBlock(String path) {
        Tier tier = switch (path) {
            case "iron_chest" -> Tier.IRON;
            case "gold_chest" -> Tier.GOLD;
            case "diamond_chest" -> Tier.DIAMOND;
            case "obsidian_chest" -> Tier.OBSIDIAN;
            case "netherite_chest" -> Tier.NETHERITE;
            default -> Tier.WOOD;
        };
        BlockBehaviour.Properties settings = switch (path) {
            case "pumpkin_chest" -> Block.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).sound(SoundType.WOOD);
            case "present" -> Block.Properties.of().mapColor(state -> switch (state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE)) {
                case SINGLE -> MapColor.COLOR_RED;
                case FRONT, BACK -> MapColor.PLANT;
                default -> MapColor.SNOW;
            }).strength(2.5f).sound(SoundType.WOOD);
            case "bamboo_chest" -> Block.Properties.of().mapColor(MapColor.PLANT).strength(1).sound(SoundType.BAMBOO).ignitedByLava();
            case "moss_chest" -> Block.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1F).sound(SoundType.MOSS);
            case "iron_chest" -> METAL_IRON_PROPERTIES;
            case "gold_chest" -> METAL_GOLD_PROPERTIES;
            case "diamond_chest" -> METAL_DIAMOND_PROPERTIES;
            case "obsidian_chest" -> OBSIDIAN_PROPERTIES;
            case "netherite_chest" -> NETHERITE_PROPERTIES;
            default -> WOOD_TIER_PROPERTIES;
        };
        BlockBehaviour.Properties tiered = tier.getBlockSettings().apply(settings);
        if ("moss_chest".equals(path)) {
            return new MossChestBlock(tiered, tier.getSlotCount());
        }
        return new ChestBlock(tiered, tier.getSlotCount());
    }

    private static Block createOldChestBlock(String path) {
        String base = path.substring("old_".length());
        Tier tier = switch (path) {
            case "old_iron_chest" -> Tier.IRON;
            case "old_gold_chest" -> Tier.GOLD;
            case "old_diamond_chest" -> Tier.DIAMOND;
            case "old_obsidian_chest" -> Tier.OBSIDIAN;
            case "old_netherite_chest" -> Tier.NETHERITE;
            default -> Tier.WOOD;
        };
        BlockBehaviour.Properties settings = switch (base) {
            case "iron_chest" -> METAL_IRON_PROPERTIES;
            case "gold_chest" -> METAL_GOLD_PROPERTIES;
            case "diamond_chest" -> METAL_DIAMOND_PROPERTIES;
            case "obsidian_chest" -> OBSIDIAN_PROPERTIES;
            case "netherite_chest" -> NETHERITE_PROPERTIES;
            default -> WOOD_TIER_PROPERTIES;
        };
        return new AbstractChestBlock(tier.getBlockSettings().apply(settings), tier.getSlotCount());
    }

    private static Block createBarrelBlock(String path) {
        boolean copperFamily = path.contains("copper");
        Tier tier = switch (path) {
            case "iron_barrel" -> Tier.IRON;
            case "gold_barrel" -> Tier.GOLD;
            case "diamond_barrel" -> Tier.DIAMOND;
            case "obsidian_barrel" -> Tier.OBSIDIAN;
            case "netherite_barrel" -> Tier.NETHERITE;
            default -> copperFamily ? Tier.COPPER : Tier.WOOD;
        };
        BlockBehaviour.Properties settings = switch (path) {
            case "iron_barrel" -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(5, 6).sound(SoundType.WOOD).ignitedByLava();
            case "gold_barrel" -> WOOD_BARREL_PROPERTIES;
            case "diamond_barrel" -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(5, 6).sound(SoundType.WOOD).ignitedByLava();
            case "obsidian_barrel" -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(50, 1200).sound(SoundType.WOOD).ignitedByLava();
            case "netherite_barrel" -> NETHERITE_BARREL_PROPERTIES;
            default -> WOOD_BARREL_PROPERTIES;
        };
        BlockBehaviour.Properties tiered = tier.getBlockSettings().apply(settings);
        if (path.equals("copper_barrel") || path.equals("exposed_copper_barrel") || path.equals("weathered_copper_barrel") || path.equals("oxidized_copper_barrel")) {
            WeatheringCopper.WeatherState state = switch (path) {
                case "exposed_copper_barrel" -> WeatheringCopper.WeatherState.EXPOSED;
                case "weathered_copper_barrel" -> WeatheringCopper.WeatherState.WEATHERED;
                case "oxidized_copper_barrel" -> WeatheringCopper.WeatherState.OXIDIZED;
                default -> WeatheringCopper.WeatherState.UNAFFECTED;
            };
            return new CopperBarrelBlock(tiered, tier.getSlotCount(), state);
        }
        return new BarrelBlock(tiered, tier.getSlotCount());
    }

    private static Block createMiniStorageBlock(String path) {
        boolean copperFamily = path.contains("copper_mini_barrel") || path.contains("exposed_copper_mini_barrel") || path.contains("weathered_copper_mini_barrel") || path.contains("oxidized_copper_mini_barrel") || path.contains("waxed_copper_mini_barrel") || path.contains("waxed_exposed_copper_mini_barrel") || path.contains("waxed_weathered_copper_mini_barrel") || path.contains("waxed_oxidized_copper_mini_barrel");
        Tier tier = switch (path) {
            case "iron_mini_chest", "iron_mini_barrel" -> Tier.IRON;
            case "gold_mini_chest", "gold_mini_barrel" -> Tier.GOLD;
            case "diamond_mini_chest", "diamond_mini_barrel" -> Tier.DIAMOND;
            case "obsidian_mini_chest", "obsidian_mini_barrel" -> Tier.OBSIDIAN;
            case "netherite_mini_chest", "netherite_mini_barrel" -> Tier.NETHERITE;
            default -> copperFamily ? Tier.COPPER : Tier.WOOD;
        };
        BlockBehaviour.Properties settings = miniSettings(path);
        BlockBehaviour.Properties tiered = tier.getBlockSettings().apply(settings);
        if (path.equals("copper_mini_barrel") || path.equals("exposed_copper_mini_barrel") || path.equals("weathered_copper_mini_barrel") || path.equals("oxidized_copper_mini_barrel")) {
            WeatheringCopper.WeatherState state = switch (path) {
                case "exposed_copper_mini_barrel" -> WeatheringCopper.WeatherState.EXPOSED;
                case "weathered_copper_mini_barrel" -> WeatheringCopper.WeatherState.WEATHERED;
                case "oxidized_copper_mini_barrel" -> WeatheringCopper.WeatherState.OXIDIZED;
                default -> WeatheringCopper.WeatherState.UNAFFECTED;
            };
            return new CopperMiniStorageBlock(tiered, state);
        }
        return new MiniStorageBlock(tiered, hasRibbon(path));
    }

    private static BlockBehaviour.Properties miniSettings(String path) {
        return switch (path) {
            case "red_mini_present" -> Block.Properties.of().mapColor(MapColor.COLOR_RED).strength(2.5f).sound(SoundType.WOOD);
            case "white_mini_present", "candy_cane_mini_present" -> Block.Properties.of().mapColor(MapColor.SNOW).strength(2.5f).sound(SoundType.WOOD);
            case "green_mini_present" -> Block.Properties.of().mapColor(MapColor.PLANT).strength(2.5f).sound(SoundType.WOOD);
            case "lavender_mini_present", "pink_amethyst_mini_present" -> Block.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.5f).sound(SoundType.WOOD);
            case "iron_mini_chest", "iron_mini_barrel" -> METAL_IRON_PROPERTIES;
            case "gold_mini_chest", "gold_mini_barrel" -> METAL_GOLD_PROPERTIES;
            case "diamond_mini_chest", "diamond_mini_barrel" -> METAL_DIAMOND_PROPERTIES;
            case "obsidian_mini_chest", "obsidian_mini_barrel" -> OBSIDIAN_PROPERTIES;
            case "netherite_mini_chest", "netherite_mini_barrel" -> NETHERITE_PROPERTIES;
            case "copper_mini_barrel", "exposed_copper_mini_barrel", "weathered_copper_mini_barrel", "oxidized_copper_mini_barrel",
                 "waxed_copper_mini_barrel", "waxed_exposed_copper_mini_barrel", "waxed_weathered_copper_mini_barrel", "waxed_oxidized_copper_mini_barrel" -> Block.Properties.of().mapColor(MapColor.WOOD).strength(3, 6).sound(SoundType.WOOD);
            case "mini_barrel" -> Block.Properties.of().mapColor(MapColor.WOOD).strength(2.5F).sound(SoundType.WOOD);
            case "pumpkin_mini_chest" -> Block.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).sound(SoundType.WOOD);
            default -> WOOD_TIER_PROPERTIES;
        };
    }

    private static boolean hasRibbon(String path) {
        return switch (path) {
            case "red_mini_present", "white_mini_present", "green_mini_present", "lavender_mini_present", "pink_amethyst_mini_present" -> true;
            default -> false;
        };
    }
}

