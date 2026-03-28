package compasses.expandedstorage.registry.content;

import compasses.expandedstorage.CommonMain;
import compasses.expandedstorage.block.BarrelBlock;
import compasses.expandedstorage.block.CopperBarrelBlock;
import compasses.expandedstorage.block.OpenableBlock;
import compasses.expandedstorage.block.entity.BarrelBlockEntity;
import compasses.expandedstorage.block.misc.BasicLockable;
import compasses.expandedstorage.block.misc.GenericItemAccess;
import compasses.expandedstorage.item.MutationMode;
import compasses.expandedstorage.item.ToolUsageResult;
import compasses.expandedstorage.misc.Tier;
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
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

final class BarrelContentModule {
    record Result(
            Map<ResourceLocation, BarrelBlock> blocks,
            Map<ResourceLocation, BlockItem> items,
            BlockEntityType<BarrelBlockEntity> blockEntityType
    ) {
    }

    private record BarrelRegistrationContext(
            Map<ResourceLocation, BarrelBlock> blocks,
            Map<ResourceLocation, BlockItem> items
    ) {
    }

    private record UpgradableBarrelPredicate(ContentContext context) implements Predicate<Block> {
        @Override
        public boolean test(Block block) {
            return block instanceof BarrelBlock
                    || block instanceof net.minecraft.world.level.block.BarrelBlock
                    || block.defaultBlockState().is(context.barrelTag);
        }
    }

    private BarrelContentModule() {
    }

    static Result create(ContentContext context) {
        Map<ResourceLocation, BarrelBlock> barrelBlocks = new LinkedHashMap<>(13);
        Map<ResourceLocation, BlockItem> barrelItems = new LinkedHashMap<>(13);
        var barrelContext = new BarrelRegistrationContext(barrelBlocks, barrelItems);

        ResourceLocation copperStat = context.stat("open_copper_barrel");
        ResourceLocation ironStat = context.stat("open_iron_barrel");
        ResourceLocation goldStat = context.stat("open_gold_barrel");
        ResourceLocation diamondStat = context.stat("open_diamond_barrel");
        ResourceLocation obsidianStat = context.stat("open_obsidian_barrel");
        ResourceLocation netheriteStat = context.stat("open_netherite_barrel");

        Properties copperBarrelSettings = Block.Properties.of().mapColor(net.minecraft.world.level.material.MapColor.WOOD).instrument(net.minecraft.world.level.block.state.properties.NoteBlockInstrument.BASS).strength(3, 6).sound(net.minecraft.world.level.block.SoundType.WOOD).ignitedByLava();
        Properties ironBarrelSettings = Block.Properties.of().mapColor(net.minecraft.world.level.material.MapColor.WOOD).instrument(net.minecraft.world.level.block.state.properties.NoteBlockInstrument.BASS).strength(5, 6).sound(net.minecraft.world.level.block.SoundType.WOOD).ignitedByLava();
        Properties goldBarrelSettings = Block.Properties.of().mapColor(net.minecraft.world.level.material.MapColor.WOOD).instrument(net.minecraft.world.level.block.state.properties.NoteBlockInstrument.BASS).strength(3, 6).sound(net.minecraft.world.level.block.SoundType.WOOD).ignitedByLava();
        Properties diamondBarrelSettings = Block.Properties.of().mapColor(net.minecraft.world.level.material.MapColor.WOOD).instrument(net.minecraft.world.level.block.state.properties.NoteBlockInstrument.BASS).strength(5, 6).sound(net.minecraft.world.level.block.SoundType.WOOD).ignitedByLava();
        Properties obsidianBarrelSettings = Block.Properties.of().mapColor(net.minecraft.world.level.material.MapColor.WOOD).instrument(net.minecraft.world.level.block.state.properties.NoteBlockInstrument.BASS).strength(50, 1200).sound(net.minecraft.world.level.block.SoundType.WOOD).ignitedByLava();
        Properties netheriteBarrelSettings = Block.Properties.of().mapColor(net.minecraft.world.level.material.MapColor.WOOD).instrument(net.minecraft.world.level.block.state.properties.NoteBlockInstrument.BASS).strength(50, 1200).sound(net.minecraft.world.level.block.SoundType.WOOD);

        addCopperBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("copper_barrel"), copperStat, context.copperTier, copperBarrelSettings, WeatheringCopper.WeatherState.UNAFFECTED);
        addCopperBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("exposed_copper_barrel"), copperStat, context.copperTier, copperBarrelSettings, WeatheringCopper.WeatherState.EXPOSED);
        addCopperBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("weathered_copper_barrel"), copperStat, context.copperTier, copperBarrelSettings, WeatheringCopper.WeatherState.WEATHERED);
        addCopperBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("oxidized_copper_barrel"), copperStat, context.copperTier, copperBarrelSettings, WeatheringCopper.WeatherState.OXIDIZED);
        addBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("waxed_copper_barrel"), copperStat, context.copperTier, copperBarrelSettings);
        addBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("waxed_exposed_copper_barrel"), copperStat, context.copperTier, copperBarrelSettings);
        addBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("waxed_weathered_copper_barrel"), copperStat, context.copperTier, copperBarrelSettings);
        addBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("waxed_oxidized_copper_barrel"), copperStat, context.copperTier, copperBarrelSettings);
        addBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("iron_barrel"), ironStat, context.ironTier, ironBarrelSettings);
        addBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("gold_barrel"), goldStat, context.goldTier, goldBarrelSettings);
        addBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("diamond_barrel"), diamondStat, context.diamondTier, diamondBarrelSettings);
        addBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("obsidian_barrel"), obsidianStat, context.obsidianTier, obsidianBarrelSettings);
        addBarrel(barrelContext, compasses.expandedstorage.ForgeMain.id("netherite_barrel"), netheriteStat, context.netheriteTier, netheriteBarrelSettings);

        BlockEntityType<BarrelBlockEntity> blockEntityType = BlockEntityType.Builder.of(
                (pos, state) -> new BarrelBlockEntity(CommonMain.getBarrelBlockEntityType(), pos, state, ((OpenableBlock) state.getBlock()).getBlockId(), GenericItemAccess::new, BasicLockable::new),
                barrelBlocks.values().toArray(BarrelBlock[]::new)
        ).build(Util.fetchChoiceType(References.BLOCK_ENTITY, CommonMain.BARREL_OBJECT_TYPE.toString()));
        CommonMain.setBarrelBlockEntityType(blockEntityType);
        CommonMain.registerMutationBehaviour(new UpgradableBarrelPredicate(context), MutationMode.ROTATE, BarrelContentModule::rotateBarrelMutation);
        return new Result(barrelBlocks, barrelItems, blockEntityType);
    }

    private static void addBarrel(BarrelRegistrationContext context, ResourceLocation id, ResourceLocation stat, Tier tier, Properties settings) {
        BarrelBlock block = new BarrelBlock(tier.getBlockSettings().apply(settings), stat, tier.getSlotCount());
        BlockItem item = new BlockItem(block, tier.getItemSettings().apply(new Item.Properties()));
        context.blocks().put(id, block);
        context.items().put(id, item);
    }

    private static void addCopperBarrel(BarrelRegistrationContext context, ResourceLocation id, ResourceLocation stat, Tier tier, Properties settings, WeatheringCopper.WeatherState weatherState) {
        BarrelBlock block = new CopperBarrelBlock(tier.getBlockSettings().apply(settings), stat, tier.getSlotCount(), weatherState);
        BlockItem item = new BlockItem(block, tier.getItemSettings().apply(new Item.Properties()));
        context.blocks().put(id, block);
        context.items().put(id, item);
    }

    private static ToolUsageResult rotateBarrelMutation(UseOnContext context, Level level, BlockState state, BlockPos pos, ItemStack stack) {
        if (!state.hasProperty(BlockStateProperties.FACING)) {
            return ToolUsageResult.fail();
        }
        if (!level.isClientSide()) {
            level.setBlockAndUpdate(pos, state.cycle(BlockStateProperties.FACING));
        }
        return ToolUsageResult.slowSuccess();
    }
}

