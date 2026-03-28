package compasses.expandedstorage.registry;

import compasses.expandedstorage.CommonMain;
import compasses.expandedstorage.block.AbstractChestBlock;
import compasses.expandedstorage.block.BarrelBlock;
import compasses.expandedstorage.block.ChestBlock;
import compasses.expandedstorage.block.CopperBarrelBlock;
import compasses.expandedstorage.block.CopperMiniStorageBlock;
import compasses.expandedstorage.block.MiniStorageBlock;
import compasses.expandedstorage.block.OpenableBlock;
import compasses.expandedstorage.misc.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class AllBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, compasses.expandedstorage.ForgeMain.MOD_ID);
    private static final Map<ResourceLocation, RegistryObject<Block>> REGISTRY_OBJECTS = new LinkedHashMap<>();
    private static boolean initialized;

    public static final ChestBlock WOOD_CHEST = block(compasses.expandedstorage.ForgeMain.id("wood_chest"));
    public static final ChestBlock PUMPKIN_CHEST = block(compasses.expandedstorage.ForgeMain.id("pumpkin_chest"));
    public static final ChestBlock PRESENT = block(compasses.expandedstorage.ForgeMain.id("present"));
    public static final ChestBlock BAMBOO_CHEST = block(compasses.expandedstorage.ForgeMain.id("bamboo_chest"));
    public static final ChestBlock MOSS_CHEST = block(compasses.expandedstorage.ForgeMain.id("moss_chest"));
    public static final ChestBlock IRON_CHEST = block(compasses.expandedstorage.ForgeMain.id("iron_chest"));
    public static final ChestBlock GOLD_CHEST = block(compasses.expandedstorage.ForgeMain.id("gold_chest"));
    public static final ChestBlock DIAMOND_CHEST = block(compasses.expandedstorage.ForgeMain.id("diamond_chest"));
    public static final ChestBlock OBSIDIAN_CHEST = block(compasses.expandedstorage.ForgeMain.id("obsidian_chest"));
    public static final ChestBlock NETHERITE_CHEST = block(compasses.expandedstorage.ForgeMain.id("netherite_chest"));

    public static final AbstractChestBlock OLD_WOOD_CHEST = block(compasses.expandedstorage.ForgeMain.id("old_wood_chest"));
    public static final AbstractChestBlock OLD_IRON_CHEST = block(compasses.expandedstorage.ForgeMain.id("old_iron_chest"));
    public static final AbstractChestBlock OLD_GOLD_CHEST = block(compasses.expandedstorage.ForgeMain.id("old_gold_chest"));
    public static final AbstractChestBlock OLD_DIAMOND_CHEST = block(compasses.expandedstorage.ForgeMain.id("old_diamond_chest"));
    public static final AbstractChestBlock OLD_OBSIDIAN_CHEST = block(compasses.expandedstorage.ForgeMain.id("old_obsidian_chest"));
    public static final AbstractChestBlock OLD_NETHERITE_CHEST = block(compasses.expandedstorage.ForgeMain.id("old_netherite_chest"));

    public static final CopperBarrelBlock COPPER_BARREL = block(compasses.expandedstorage.ForgeMain.id("copper_barrel"));
    public static final CopperBarrelBlock EXPOSED_COPPER_BARREL = block(compasses.expandedstorage.ForgeMain.id("exposed_copper_barrel"));
    public static final CopperBarrelBlock WEATHERED_COPPER_BARREL = block(compasses.expandedstorage.ForgeMain.id("weathered_copper_barrel"));
    public static final CopperBarrelBlock OXIDIZED_COPPER_BARREL = block(compasses.expandedstorage.ForgeMain.id("oxidized_copper_barrel"));
    public static final BarrelBlock WAXED_COPPER_BARREL = block(compasses.expandedstorage.ForgeMain.id("waxed_copper_barrel"));
    public static final BarrelBlock WAXED_EXPOSED_COPPER_BARREL = block(compasses.expandedstorage.ForgeMain.id("waxed_exposed_copper_barrel"));
    public static final BarrelBlock WAXED_WEATHERED_COPPER_BARREL = block(compasses.expandedstorage.ForgeMain.id("waxed_weathered_copper_barrel"));
    public static final BarrelBlock WAXED_OXIDIZED_COPPER_BARREL = block(compasses.expandedstorage.ForgeMain.id("waxed_oxidized_copper_barrel"));
    public static final BarrelBlock IRON_BARREL = block(compasses.expandedstorage.ForgeMain.id("iron_barrel"));
    public static final BarrelBlock GOLD_BARREL = block(compasses.expandedstorage.ForgeMain.id("gold_barrel"));
    public static final BarrelBlock DIAMOND_BARREL = block(compasses.expandedstorage.ForgeMain.id("diamond_barrel"));
    public static final BarrelBlock OBSIDIAN_BARREL = block(compasses.expandedstorage.ForgeMain.id("obsidian_barrel"));
    public static final BarrelBlock NETHERITE_BARREL = block(compasses.expandedstorage.ForgeMain.id("netherite_barrel"));

    public static final MiniStorageBlock VANILLA_WOOD_MINI_CHEST = block(compasses.expandedstorage.ForgeMain.id("vanilla_wood_mini_chest"));
    public static final MiniStorageBlock WOOD_MINI_CHEST = block(compasses.expandedstorage.ForgeMain.id("wood_mini_chest"));
    public static final MiniStorageBlock PUMPKIN_MINI_CHEST = block(compasses.expandedstorage.ForgeMain.id("pumpkin_mini_chest"));
    public static final MiniStorageBlock RED_MINI_PRESENT = block(compasses.expandedstorage.ForgeMain.id("red_mini_present"));
    public static final MiniStorageBlock WHITE_MINI_PRESENT = block(compasses.expandedstorage.ForgeMain.id("white_mini_present"));
    public static final MiniStorageBlock CANDY_CANE_MINI_PRESENT = block(compasses.expandedstorage.ForgeMain.id("candy_cane_mini_present"));
    public static final MiniStorageBlock GREEN_MINI_PRESENT = block(compasses.expandedstorage.ForgeMain.id("green_mini_present"));
    public static final MiniStorageBlock LAVENDER_MINI_PRESENT = block(compasses.expandedstorage.ForgeMain.id("lavender_mini_present"));
    public static final MiniStorageBlock PINK_AMETHYST_MINI_PRESENT = block(compasses.expandedstorage.ForgeMain.id("pink_amethyst_mini_present"));
    public static final MiniStorageBlock IRON_MINI_CHEST = block(compasses.expandedstorage.ForgeMain.id("iron_mini_chest"));
    public static final MiniStorageBlock GOLD_MINI_CHEST = block(compasses.expandedstorage.ForgeMain.id("gold_mini_chest"));
    public static final MiniStorageBlock DIAMOND_MINI_CHEST = block(compasses.expandedstorage.ForgeMain.id("diamond_mini_chest"));
    public static final MiniStorageBlock OBSIDIAN_MINI_CHEST = block(compasses.expandedstorage.ForgeMain.id("obsidian_mini_chest"));
    public static final MiniStorageBlock NETHERITE_MINI_CHEST = block(compasses.expandedstorage.ForgeMain.id("netherite_mini_chest"));
    public static final MiniStorageBlock MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("mini_barrel"));
    public static final CopperMiniStorageBlock COPPER_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("copper_mini_barrel"));
    public static final CopperMiniStorageBlock EXPOSED_COPPER_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("exposed_copper_mini_barrel"));
    public static final CopperMiniStorageBlock WEATHERED_COPPER_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("weathered_copper_mini_barrel"));
    public static final CopperMiniStorageBlock OXIDIZED_COPPER_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("oxidized_copper_mini_barrel"));
    public static final MiniStorageBlock WAXED_COPPER_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("waxed_copper_mini_barrel"));
    public static final MiniStorageBlock WAXED_EXPOSED_COPPER_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("waxed_exposed_copper_mini_barrel"));
    public static final MiniStorageBlock WAXED_WEATHERED_COPPER_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("waxed_weathered_copper_mini_barrel"));
    public static final MiniStorageBlock WAXED_OXIDIZED_COPPER_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("waxed_oxidized_copper_mini_barrel"));
    public static final MiniStorageBlock IRON_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("iron_mini_barrel"));
    public static final MiniStorageBlock GOLD_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("gold_mini_barrel"));
    public static final MiniStorageBlock DIAMOND_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("diamond_mini_barrel"));
    public static final MiniStorageBlock OBSIDIAN_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("obsidian_mini_barrel"));
    public static final MiniStorageBlock NETHERITE_MINI_BARREL = block(compasses.expandedstorage.ForgeMain.id("netherite_mini_barrel"));

    private AllBlocks() {
    }

    public static void register(IEventBus modEventBus) {
        if (!initialized) {
            for (Map.Entry<ResourceLocation, OpenableBlock> entry : ModRegistry.blocks().entrySet()) {
                ResourceLocation id = entry.getKey();
                OpenableBlock block = entry.getValue();
                REGISTRY_OBJECTS.put(id, BLOCKS.register(id.getPath(), () -> block));
            }
            initialized = true;
        }
        BLOCKS.register(modEventBus);
    }

    public static List<OpenableBlock> all() {
        return Arrays.stream(AllBlocks.class.getFields())
                .filter(it -> OpenableBlock.class.isAssignableFrom(it.getType()))
                .map(it -> {
                    try {
                        return (OpenableBlock) it.get(null);
                    } catch (IllegalAccessException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    private static <T extends Block> T block(ResourceLocation id) {
        //noinspection unchecked
        return (T) BuiltInRegistries.BLOCK.get(id);
    }
}

