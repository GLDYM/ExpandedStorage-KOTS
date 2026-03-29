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
import net.minecraft.resources.ResourceLocation;
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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class AllBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, compasses.expandedstorage.ForgeMain.MOD_ID);
    public static final RegistryObject<ChestBlock> WOOD_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("wood_chest"));
    public static final RegistryObject<ChestBlock> PUMPKIN_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("pumpkin_chest"));
    public static final RegistryObject<ChestBlock> PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("present"));
    public static final RegistryObject<ChestBlock> BAMBOO_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("bamboo_chest"));
    public static final RegistryObject<ChestBlock> MOSS_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("moss_chest"));
    public static final RegistryObject<ChestBlock> IRON_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_chest"));
    public static final RegistryObject<ChestBlock> GOLD_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_chest"));
    public static final RegistryObject<ChestBlock> DIAMOND_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("diamond_chest"));
    public static final RegistryObject<ChestBlock> OBSIDIAN_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("obsidian_chest"));
    public static final RegistryObject<ChestBlock> NETHERITE_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("netherite_chest"));

    public static final RegistryObject<AbstractChestBlock> OLD_WOOD_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("old_wood_chest"));
    public static final RegistryObject<AbstractChestBlock> OLD_IRON_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("old_iron_chest"));
    public static final RegistryObject<AbstractChestBlock> OLD_GOLD_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("old_gold_chest"));
    public static final RegistryObject<AbstractChestBlock> OLD_DIAMOND_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("old_diamond_chest"));
    public static final RegistryObject<AbstractChestBlock> OLD_OBSIDIAN_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("old_obsidian_chest"));
    public static final RegistryObject<AbstractChestBlock> OLD_NETHERITE_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("old_netherite_chest"));

    public static final RegistryObject<CopperBarrelBlock> COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("copper_barrel"));
    public static final RegistryObject<CopperBarrelBlock> EXPOSED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("exposed_copper_barrel"));
    public static final RegistryObject<CopperBarrelBlock> WEATHERED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("weathered_copper_barrel"));
    public static final RegistryObject<CopperBarrelBlock> OXIDIZED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("oxidized_copper_barrel"));
    public static final RegistryObject<BarrelBlock> WAXED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_copper_barrel"));
    public static final RegistryObject<BarrelBlock> WAXED_EXPOSED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_exposed_copper_barrel"));
    public static final RegistryObject<BarrelBlock> WAXED_WEATHERED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_weathered_copper_barrel"));
    public static final RegistryObject<BarrelBlock> WAXED_OXIDIZED_COPPER_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_oxidized_copper_barrel"));
    public static final RegistryObject<BarrelBlock> IRON_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_barrel"));
    public static final RegistryObject<BarrelBlock> GOLD_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_barrel"));
    public static final RegistryObject<BarrelBlock> DIAMOND_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("diamond_barrel"));
    public static final RegistryObject<BarrelBlock> OBSIDIAN_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("obsidian_barrel"));
    public static final RegistryObject<BarrelBlock> NETHERITE_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("netherite_barrel"));

    public static final RegistryObject<MiniStorageBlock> VANILLA_WOOD_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("vanilla_wood_mini_chest"));
    public static final RegistryObject<MiniStorageBlock> WOOD_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("wood_mini_chest"));
    public static final RegistryObject<MiniStorageBlock> PUMPKIN_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("pumpkin_mini_chest"));
    public static final RegistryObject<MiniStorageBlock> RED_MINI_PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("red_mini_present"));
    public static final RegistryObject<MiniStorageBlock> WHITE_MINI_PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("white_mini_present"));
    public static final RegistryObject<MiniStorageBlock> CANDY_CANE_MINI_PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("candy_cane_mini_present"));
    public static final RegistryObject<MiniStorageBlock> GREEN_MINI_PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("green_mini_present"));
    public static final RegistryObject<MiniStorageBlock> LAVENDER_MINI_PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("lavender_mini_present"));
    public static final RegistryObject<MiniStorageBlock> PINK_AMETHYST_MINI_PRESENT_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("pink_amethyst_mini_present"));
    public static final RegistryObject<MiniStorageBlock> IRON_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_mini_chest"));
    public static final RegistryObject<MiniStorageBlock> GOLD_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_mini_chest"));
    public static final RegistryObject<MiniStorageBlock> DIAMOND_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("diamond_mini_chest"));
    public static final RegistryObject<MiniStorageBlock> OBSIDIAN_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("obsidian_mini_chest"));
    public static final RegistryObject<MiniStorageBlock> NETHERITE_MINI_CHEST_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("netherite_mini_chest"));
    public static final RegistryObject<MiniStorageBlock> MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("mini_barrel"));
    public static final RegistryObject<CopperMiniStorageBlock> COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("copper_mini_barrel"));
    public static final RegistryObject<CopperMiniStorageBlock> EXPOSED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("exposed_copper_mini_barrel"));
    public static final RegistryObject<CopperMiniStorageBlock> WEATHERED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("weathered_copper_mini_barrel"));
    public static final RegistryObject<CopperMiniStorageBlock> OXIDIZED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("oxidized_copper_mini_barrel"));
    public static final RegistryObject<MiniStorageBlock> WAXED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_copper_mini_barrel"));
    public static final RegistryObject<MiniStorageBlock> WAXED_EXPOSED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_exposed_copper_mini_barrel"));
    public static final RegistryObject<MiniStorageBlock> WAXED_WEATHERED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_weathered_copper_mini_barrel"));
    public static final RegistryObject<MiniStorageBlock> WAXED_OXIDIZED_COPPER_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("waxed_oxidized_copper_mini_barrel"));
    public static final RegistryObject<MiniStorageBlock> IRON_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_mini_barrel"));
    public static final RegistryObject<MiniStorageBlock> GOLD_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_mini_barrel"));
    public static final RegistryObject<MiniStorageBlock> DIAMOND_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("diamond_mini_barrel"));
    public static final RegistryObject<MiniStorageBlock> OBSIDIAN_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("obsidian_mini_barrel"));
    public static final RegistryObject<MiniStorageBlock> NETHERITE_MINI_BARREL_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("netherite_mini_barrel"));

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

    private static <T extends Block> RegistryObject<T> register(ResourceLocation id) {
        return BLOCKS.register(id.getPath(), () -> {
            //noinspection unchecked
            return (T) createBlock(id);
        });
    }

    private static Block createBlock(ResourceLocation id) {
        String path = id.getPath();
        if (path.contains("mini_")) {
            return createMiniStorageBlock(path);
        }
        if (path.endsWith("_barrel")) {
            return createBarrelBlock(path);
        }
        if (path.startsWith("old_")) {
            return createOldChestBlock(path);
        }
        return createChestBlock(path);
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
            case "iron_chest" -> Block.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(5, 6).sound(SoundType.METAL);
            case "gold_chest" -> Block.Properties.of().mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.BELL).strength(3, 6).sound(SoundType.METAL);
            case "diamond_chest" -> Block.Properties.of().mapColor(MapColor.DIAMOND).strength(5, 6).sound(SoundType.METAL);
            case "obsidian_chest" -> Block.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).strength(50, 1200);
            case "netherite_chest" -> Block.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(50, 1200).sound(SoundType.NETHERITE_BLOCK);
            default -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5f).sound(SoundType.WOOD).ignitedByLava();
        };
        ResourceLocation stat = compasses.expandedstorage.ForgeMain.id("open_" + path);
        BlockBehaviour.Properties tiered = tier.getBlockSettings().apply(settings);
        if ("moss_chest".equals(path)) {
            return new MossChestBlock(tiered, stat, tier.getSlotCount());
        }
        return new ChestBlock(tiered, stat, tier.getSlotCount());
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
            case "iron_chest" -> Block.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(5, 6).sound(SoundType.METAL);
            case "gold_chest" -> Block.Properties.of().mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.BELL).strength(3, 6).sound(SoundType.METAL);
            case "diamond_chest" -> Block.Properties.of().mapColor(MapColor.DIAMOND).strength(5, 6).sound(SoundType.METAL);
            case "obsidian_chest" -> Block.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).strength(50, 1200);
            case "netherite_chest" -> Block.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(50, 1200).sound(SoundType.NETHERITE_BLOCK);
            default -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5f).sound(SoundType.WOOD).ignitedByLava();
        };
        ResourceLocation stat = compasses.expandedstorage.ForgeMain.id("open_" + path);
        return new AbstractChestBlock(tier.getBlockSettings().apply(settings), stat, tier.getSlotCount());
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
        String statName = copperFamily ? "open_copper_barrel" : "open_" + path;
        ResourceLocation stat = compasses.expandedstorage.ForgeMain.id(statName);
        BlockBehaviour.Properties settings = switch (path) {
            case "iron_barrel" -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(5, 6).sound(SoundType.WOOD).ignitedByLava();
            case "gold_barrel" -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3, 6).sound(SoundType.WOOD).ignitedByLava();
            case "diamond_barrel" -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(5, 6).sound(SoundType.WOOD).ignitedByLava();
            case "obsidian_barrel" -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(50, 1200).sound(SoundType.WOOD).ignitedByLava();
            case "netherite_barrel" -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(50, 1200).sound(SoundType.WOOD);
            default -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(3, 6).sound(SoundType.WOOD).ignitedByLava();
        };
        BlockBehaviour.Properties tiered = tier.getBlockSettings().apply(settings);
        if (path.equals("copper_barrel") || path.equals("exposed_copper_barrel") || path.equals("weathered_copper_barrel") || path.equals("oxidized_copper_barrel")) {
            WeatheringCopper.WeatherState state = switch (path) {
                case "exposed_copper_barrel" -> WeatheringCopper.WeatherState.EXPOSED;
                case "weathered_copper_barrel" -> WeatheringCopper.WeatherState.WEATHERED;
                case "oxidized_copper_barrel" -> WeatheringCopper.WeatherState.OXIDIZED;
                default -> WeatheringCopper.WeatherState.UNAFFECTED;
            };
            return new CopperBarrelBlock(tiered, stat, tier.getSlotCount(), state);
        }
        return new BarrelBlock(tiered, stat, tier.getSlotCount());
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
        ResourceLocation stat = compasses.expandedstorage.ForgeMain.id(openingStatForMiniStorage(path));
        BlockBehaviour.Properties settings = miniSettings(path);
        BlockBehaviour.Properties tiered = tier.getBlockSettings().apply(settings);
        if (path.equals("copper_mini_barrel") || path.equals("exposed_copper_mini_barrel") || path.equals("weathered_copper_mini_barrel") || path.equals("oxidized_copper_mini_barrel")) {
            WeatheringCopper.WeatherState state = switch (path) {
                case "exposed_copper_mini_barrel" -> WeatheringCopper.WeatherState.EXPOSED;
                case "weathered_copper_mini_barrel" -> WeatheringCopper.WeatherState.WEATHERED;
                case "oxidized_copper_mini_barrel" -> WeatheringCopper.WeatherState.OXIDIZED;
                default -> WeatheringCopper.WeatherState.UNAFFECTED;
            };
            return new CopperMiniStorageBlock(tiered, stat, state);
        }
        return new MiniStorageBlock(tiered, stat, hasRibbon(path));
    }

    private static String openingStatForMiniStorage(String path) {
        return switch (path) {
            case "vanilla_wood_mini_chest", "wood_mini_chest" -> "open_wood_mini_chest";
            case "pumpkin_mini_chest" -> "open_pumpkin_mini_chest";
            case "red_mini_present" -> "open_red_mini_present";
            case "white_mini_present" -> "open_white_mini_present";
            case "candy_cane_mini_present" -> "open_candy_cane_mini_present";
            case "green_mini_present" -> "open_green_mini_present";
            case "lavender_mini_present" -> "open_lavender_mini_present";
            case "pink_amethyst_mini_present" -> "open_pink_amethyst_mini_present";
            case "iron_mini_chest" -> "open_iron_mini_chest";
            case "gold_mini_chest" -> "open_gold_mini_chest";
            case "diamond_mini_chest" -> "open_diamond_mini_chest";
            case "obsidian_mini_chest" -> "open_obsidian_mini_chest";
            case "netherite_mini_chest" -> "open_netherite_mini_chest";
            case "mini_barrel" -> "open_mini_barrel";
            case "iron_mini_barrel" -> "open_iron_mini_barrel";
            case "gold_mini_barrel" -> "open_gold_mini_barrel";
            case "diamond_mini_barrel" -> "open_diamond_mini_barrel";
            case "obsidian_mini_barrel" -> "open_obsidian_mini_barrel";
            case "netherite_mini_barrel" -> "open_netherite_mini_barrel";
            default -> "open_copper_mini_barrel";
        };
    }

    private static BlockBehaviour.Properties miniSettings(String path) {
        return switch (path) {
            case "red_mini_present" -> Block.Properties.of().mapColor(MapColor.COLOR_RED).strength(2.5f).sound(SoundType.WOOD);
            case "white_mini_present", "candy_cane_mini_present" -> Block.Properties.of().mapColor(MapColor.SNOW).strength(2.5f).sound(SoundType.WOOD);
            case "green_mini_present" -> Block.Properties.of().mapColor(MapColor.PLANT).strength(2.5f).sound(SoundType.WOOD);
            case "lavender_mini_present", "pink_amethyst_mini_present" -> Block.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.5f).sound(SoundType.WOOD);
            case "iron_mini_chest", "iron_mini_barrel" -> Block.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(5, 6).sound(SoundType.METAL);
            case "gold_mini_chest", "gold_mini_barrel" -> Block.Properties.of().mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.BELL).strength(3, 6).sound(SoundType.METAL);
            case "diamond_mini_chest", "diamond_mini_barrel" -> Block.Properties.of().mapColor(MapColor.DIAMOND).strength(5, 6).sound(SoundType.METAL);
            case "obsidian_mini_chest", "obsidian_mini_barrel" -> Block.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).strength(50, 1200);
            case "netherite_mini_chest", "netherite_mini_barrel" -> Block.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(50, 1200).sound(SoundType.NETHERITE_BLOCK);
            case "copper_mini_barrel", "exposed_copper_mini_barrel", "weathered_copper_mini_barrel", "oxidized_copper_mini_barrel",
                 "waxed_copper_mini_barrel", "waxed_exposed_copper_mini_barrel", "waxed_weathered_copper_mini_barrel", "waxed_oxidized_copper_mini_barrel" -> Block.Properties.of().mapColor(MapColor.WOOD).strength(3, 6).sound(SoundType.WOOD);
            case "mini_barrel" -> Block.Properties.of().mapColor(MapColor.WOOD).strength(2.5F).sound(SoundType.WOOD);
            case "pumpkin_mini_chest" -> Block.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).sound(SoundType.WOOD);
            default -> Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5f).sound(SoundType.WOOD).ignitedByLava();
        };
    }

    private static boolean hasRibbon(String path) {
        return switch (path) {
            case "red_mini_present", "white_mini_present", "green_mini_present", "lavender_mini_present", "pink_amethyst_mini_present" -> true;
            default -> false;
        };
    }
}

