package compasses.expandedstorage.registry.content;

import compasses.expandedstorage.block.CopperMiniStorageBlock;
import compasses.expandedstorage.block.MiniStorageBlock;
import compasses.expandedstorage.block.OpenableBlock;
import compasses.expandedstorage.block.entity.MiniStorageBlockEntity;
import compasses.expandedstorage.block.misc.BasicLockable;
import compasses.expandedstorage.block.misc.GenericItemAccess;
import compasses.expandedstorage.item.MiniStorageBlockItem;
import compasses.expandedstorage.item.MutationMode;
import compasses.expandedstorage.item.ToolUsageResult;
import compasses.expandedstorage.misc.Tier;
import compasses.expandedstorage.registry.AllBlockEntityTypes;
import compasses.expandedstorage.registry.BlockMutatorBehaviours;
import compasses.expandedstorage.misc.Utils;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import java.util.LinkedHashMap;
import java.util.Map;

final class MiniStorageContentModule {
    record Result(
            Map<ResourceLocation, MiniStorageBlock> blocks,
            Map<ResourceLocation, BlockItem> items,
            BlockEntityType<MiniStorageBlockEntity> blockEntityType
    ) {
    }

    private record MiniStorageRegistrationContext(
            ContentContext context,
            Map<ResourceLocation, MiniStorageBlock> blocks,
            Map<ResourceLocation, BlockItem> items
    ) {
    }

    private MiniStorageContentModule() {
    }

    static void bootstrap(ContentContext context) {
        context.stat("open_wood_mini_chest");
        context.stat("open_pumpkin_mini_chest");
        context.stat("open_red_mini_present");
        context.stat("open_white_mini_present");
        context.stat("open_candy_cane_mini_present");
        context.stat("open_green_mini_present");
        context.stat("open_lavender_mini_present");
        context.stat("open_pink_amethyst_mini_present");
        context.stat("open_iron_mini_chest");
        context.stat("open_gold_mini_chest");
        context.stat("open_diamond_mini_chest");
        context.stat("open_obsidian_mini_chest");
        context.stat("open_netherite_mini_chest");
        context.stat("open_mini_barrel");
        context.stat("open_copper_mini_barrel");
        context.stat("open_iron_mini_barrel");
        context.stat("open_gold_mini_barrel");
        context.stat("open_diamond_mini_barrel");
        context.stat("open_obsidian_mini_barrel");
        context.stat("open_netherite_mini_barrel");
        BlockMutatorBehaviours.register(MiniStorageContentModule::isMiniStorageBlock, MutationMode.ROTATE, MiniStorageContentModule::rotateMiniStorageMutation);
    }

    static Result create(ContentContext context) {
        Map<ResourceLocation, MiniStorageBlock> miniStorageBlocks = new LinkedHashMap<>();
        Map<ResourceLocation, BlockItem> miniStorageItems = new LinkedHashMap<>();
        var miniContext = new MiniStorageRegistrationContext(context, miniStorageBlocks, miniStorageItems);

        ResourceLocation woodChestStat = context.stat("open_wood_mini_chest");
        ResourceLocation pumpkinChestStat = context.stat("open_pumpkin_mini_chest");
        ResourceLocation redPresentStat = context.stat("open_red_mini_present");
        ResourceLocation whitePresentStat = context.stat("open_white_mini_present");
        ResourceLocation candyCanePresentStat = context.stat("open_candy_cane_mini_present");
        ResourceLocation greenPresentStat = context.stat("open_green_mini_present");
        ResourceLocation lavenderPresentStat = context.stat("open_lavender_mini_present");
        ResourceLocation pinkAmethystPresentStat = context.stat("open_pink_amethyst_mini_present");
        ResourceLocation ironChestStat = context.stat("open_iron_mini_chest");
        ResourceLocation goldChestStat = context.stat("open_gold_mini_chest");
        ResourceLocation diamondChestStat = context.stat("open_diamond_mini_chest");
        ResourceLocation obsidianChestStat = context.stat("open_obsidian_mini_chest");
        ResourceLocation netheriteChestStat = context.stat("open_netherite_mini_chest");
        ResourceLocation barrelStat = context.stat("open_mini_barrel");
        ResourceLocation copperBarrelStat = context.stat("open_copper_mini_barrel");
        ResourceLocation ironBarrelStat = context.stat("open_iron_mini_barrel");
        ResourceLocation goldBarrelStat = context.stat("open_gold_mini_barrel");
        ResourceLocation diamondBarrelStat = context.stat("open_diamond_mini_barrel");
        ResourceLocation obsidianBarrelStat = context.stat("open_obsidian_mini_barrel");
        ResourceLocation netheriteBarrelStat = context.stat("open_netherite_mini_barrel");

        Properties redPresentSettings = Properties.of().mapColor(MapColor.COLOR_RED).strength(2.5f).sound(net.minecraft.world.level.block.SoundType.WOOD);
        Properties whitePresentSettings = Properties.of().mapColor(MapColor.SNOW).strength(2.5f).sound(net.minecraft.world.level.block.SoundType.WOOD);
        Properties candyCanePresentSettings = Properties.of().mapColor(MapColor.SNOW).strength(2.5f).sound(net.minecraft.world.level.block.SoundType.WOOD);
        Properties greenPresentSettings = Properties.of().mapColor(MapColor.PLANT).strength(2.5f).sound(net.minecraft.world.level.block.SoundType.WOOD);
        Properties lavenderPresentSettings = Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.5f).sound(net.minecraft.world.level.block.SoundType.WOOD);
        Properties pinkAmethystPresentSettings = Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.5f).sound(net.minecraft.world.level.block.SoundType.WOOD);
        Properties woodBarrelSettings = Properties.of().mapColor(MapColor.WOOD).strength(2.5F).sound(net.minecraft.world.level.block.SoundType.WOOD);
        Properties copperBarrelSettings = Properties.of().mapColor(MapColor.WOOD).strength(3, 6).sound(net.minecraft.world.level.block.SoundType.WOOD);
        Properties ironBarrelSettings = Properties.of().mapColor(MapColor.WOOD).strength(5, 6).sound(net.minecraft.world.level.block.SoundType.WOOD);
        Properties goldBarrelSettings = Properties.of().mapColor(MapColor.WOOD).strength(3, 6).sound(net.minecraft.world.level.block.SoundType.WOOD);
        Properties diamondBarrelSettings = Properties.of().mapColor(MapColor.WOOD).strength(5, 6).sound(net.minecraft.world.level.block.SoundType.WOOD);
        Properties obsidianBarrelSettings = Properties.of().mapColor(MapColor.WOOD).strength(50, 1200).sound(net.minecraft.world.level.block.SoundType.WOOD);
        Properties netheriteBarrelSettings = Properties.of().mapColor(MapColor.WOOD).strength(50, 1200).sound(net.minecraft.world.level.block.SoundType.WOOD);

        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("vanilla_wood_mini_chest"), woodChestStat, context.woodTier, context.woodSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("wood_mini_chest"), woodChestStat, context.woodTier, context.woodSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("pumpkin_mini_chest"), pumpkinChestStat, context.woodTier, context.pumpkinSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("red_mini_present"), redPresentStat, context.woodTier, redPresentSettings, true);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("white_mini_present"), whitePresentStat, context.woodTier, whitePresentSettings, true);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("candy_cane_mini_present"), candyCanePresentStat, context.woodTier, candyCanePresentSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("green_mini_present"), greenPresentStat, context.woodTier, greenPresentSettings, true);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("lavender_mini_present"), lavenderPresentStat, context.woodTier, lavenderPresentSettings, true);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("pink_amethyst_mini_present"), pinkAmethystPresentStat, context.woodTier, pinkAmethystPresentSettings, true);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("iron_mini_chest"), ironChestStat, context.ironTier, context.ironSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("gold_mini_chest"), goldChestStat, context.goldTier, context.goldSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("diamond_mini_chest"), diamondChestStat, context.diamondTier, context.diamondSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("obsidian_mini_chest"), obsidianChestStat, context.obsidianTier, context.obsidianSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("netherite_mini_chest"), netheriteChestStat, context.netheriteTier, context.netheriteSettings, false);

        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("mini_barrel"), barrelStat, context.woodTier, woodBarrelSettings, false);
        addCopperMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("copper_mini_barrel"), copperBarrelStat, context.copperTier, copperBarrelSettings, WeatheringCopper.WeatherState.UNAFFECTED);
        addCopperMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("exposed_copper_mini_barrel"), copperBarrelStat, context.copperTier, copperBarrelSettings, WeatheringCopper.WeatherState.EXPOSED);
        addCopperMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("weathered_copper_mini_barrel"), copperBarrelStat, context.copperTier, copperBarrelSettings, WeatheringCopper.WeatherState.WEATHERED);
        addCopperMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("oxidized_copper_mini_barrel"), copperBarrelStat, context.copperTier, copperBarrelSettings, WeatheringCopper.WeatherState.OXIDIZED);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("waxed_copper_mini_barrel"), copperBarrelStat, context.copperTier, copperBarrelSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("waxed_exposed_copper_mini_barrel"), copperBarrelStat, context.copperTier, copperBarrelSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("waxed_weathered_copper_mini_barrel"), copperBarrelStat, context.copperTier, copperBarrelSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("waxed_oxidized_copper_mini_barrel"), copperBarrelStat, context.copperTier, copperBarrelSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("iron_mini_barrel"), ironBarrelStat, context.ironTier, ironBarrelSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("gold_mini_barrel"), goldBarrelStat, context.goldTier, goldBarrelSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("diamond_mini_barrel"), diamondBarrelStat, context.diamondTier, diamondBarrelSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("obsidian_mini_barrel"), obsidianBarrelStat, context.obsidianTier, obsidianBarrelSettings, false);
        addMiniStorage(miniContext, compasses.expandedstorage.ForgeMain.id("netherite_mini_barrel"), netheriteBarrelStat, context.netheriteTier, netheriteBarrelSettings, false);

        BlockEntityType<MiniStorageBlockEntity> blockEntityType = BlockEntityType.Builder.of(
            (pos, state) -> new MiniStorageBlockEntity(AllBlockEntityTypes.miniStorageBlockEntityType(), pos, state, ((OpenableBlock) state.getBlock()).getBlockId(), GenericItemAccess::new, BasicLockable::new),
                miniStorageBlocks.values().toArray(MiniStorageBlock[]::new)
        ).build(Util.fetchChoiceType(References.BLOCK_ENTITY, AllBlockEntityTypes.MINI_STORAGE_OBJECT_TYPE.toString()));
        BlockMutatorBehaviours.register(MiniStorageContentModule::isMiniStorageBlock, MutationMode.ROTATE, MiniStorageContentModule::rotateMiniStorageMutation);
        return new Result(miniStorageBlocks, miniStorageItems, blockEntityType);
    }

    private static void addMiniStorage(MiniStorageRegistrationContext context, ResourceLocation id, ResourceLocation stat, Tier tier, Properties settings, boolean hasRibbon) {
        MiniStorageBlock block = new MiniStorageBlock(tier.getBlockSettings().apply(settings), stat, hasRibbon);
        BlockItem item = new MiniStorageBlockItem(block, tier.getItemSettings().apply(new Item.Properties()));
        context.blocks().put(id, block);
        context.items().put(id, item);
    }

    private static void addCopperMiniStorage(MiniStorageRegistrationContext context, ResourceLocation id, ResourceLocation stat, Tier tier, Properties settings, WeatheringCopper.WeatherState weatherState) {
        MiniStorageBlock block = new CopperMiniStorageBlock(tier.getBlockSettings().apply(settings), stat, weatherState);
        BlockItem item = new MiniStorageBlockItem(block, tier.getItemSettings().apply(new Item.Properties()));
        context.blocks().put(id, block);
        context.items().put(id, item);
    }

    private static boolean isMiniStorageBlock(Block block) {
        return block instanceof MiniStorageBlock;
    }

    private static ToolUsageResult rotateMiniStorageMutation(UseOnContext context, Level level, BlockState state, BlockPos pos, ItemStack stack) {
        if (!level.isClientSide()) {
            level.setBlockAndUpdate(pos, state.rotate(Rotation.CLOCKWISE_90));
        }
        return ToolUsageResult.slowSuccess();
    }
}

