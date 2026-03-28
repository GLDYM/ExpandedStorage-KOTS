package compasses.expandedstorage.registry.content;

import com.google.common.collect.ImmutableSet;
import compasses.expandedstorage.CommonMain;
import compasses.expandedstorage.api.EsChestType;
import compasses.expandedstorage.block.AbstractChestBlock;
import compasses.expandedstorage.block.ChestBlock;
import compasses.expandedstorage.block.MossChestBlock;
import compasses.expandedstorage.block.OpenableBlock;
import compasses.expandedstorage.block.entity.ChestBlockEntity;
import compasses.expandedstorage.block.entity.OldChestBlockEntity;
import compasses.expandedstorage.block.entity.extendable.OpenableBlockEntity;
import compasses.expandedstorage.block.misc.BasicLockable;
import compasses.expandedstorage.block.misc.ChestItemAccess;
import compasses.expandedstorage.entity.ChestMinecart;
import compasses.expandedstorage.item.ChestBlockItem;
import compasses.expandedstorage.item.ChestMinecartItem;
import compasses.expandedstorage.item.ForgeChestMinecartItem;
import compasses.expandedstorage.item.MutationMode;
import compasses.expandedstorage.item.ToolUsageResult;
import compasses.expandedstorage.misc.Tier;
import compasses.expandedstorage.misc.Utils;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;

import java.util.LinkedHashMap;
import java.util.Map;

final class ChestContentModule {
    record Result(
            Map<ResourceLocation, ChestBlock> chestBlocks,
            Map<ResourceLocation, BlockItem> chestItems,
            Map<ResourceLocation, EntityType<ChestMinecart>> chestMinecartEntityTypes,
            Map<ResourceLocation, ChestMinecartItem> chestMinecartItems,
            BlockEntityType<ChestBlockEntity> chestBlockEntityType,
            Map<ResourceLocation, AbstractChestBlock> oldChestBlocks,
            Map<ResourceLocation, BlockItem> oldChestItems,
            BlockEntityType<OldChestBlockEntity> oldChestBlockEntityType
    ) {
    }

    private record ChestRegistrationContext(
            ContentContext context,
            Map<ResourceLocation, ChestBlock> blocks,
            Map<ResourceLocation, BlockItem> items,
            Map<ResourceLocation, EntityType<ChestMinecart>> entityTypes,
            Map<ResourceLocation, ChestMinecartItem> cartItems
    ) {
    }

    private record OldChestRegistrationContext(
            Map<ResourceLocation, AbstractChestBlock> blocks,
            Map<ResourceLocation, BlockItem> items
    ) {
    }

    private ChestContentModule() {
    }

    static Result create(ContentContext context) {
        Map<ResourceLocation, ChestBlock> chestBlocks = new LinkedHashMap<>(9);
        Map<ResourceLocation, BlockItem> chestItems = new LinkedHashMap<>(9);
        Map<ResourceLocation, EntityType<ChestMinecart>> chestMinecartEntityTypes = new LinkedHashMap<>(9);
        Map<ResourceLocation, ChestMinecartItem> chestMinecartItems = new LinkedHashMap<>(9);
        Map<ResourceLocation, AbstractChestBlock> oldChestBlocks = new LinkedHashMap<>(6);
        Map<ResourceLocation, BlockItem> oldChestItems = new LinkedHashMap<>(6);

        var chestContext = new ChestRegistrationContext(context, chestBlocks, chestItems, chestMinecartEntityTypes, chestMinecartItems);
        var oldChestContext = new OldChestRegistrationContext(oldChestBlocks, oldChestItems);

        registerModernChests(chestContext);
        if (context.client) {
            registerChestTextures(chestBlocks);
        }
        BlockEntityType<ChestBlockEntity> chestBlockEntityType = registerChestBlockEntityType(chestBlocks);

        registerOldChests(context, oldChestContext);
        BlockEntityType<OldChestBlockEntity> oldChestBlockEntityType = registerOldChestBlockEntityType(oldChestBlocks);

        registerMutationBehaviours();

        return new Result(
                chestBlocks,
                chestItems,
                chestMinecartEntityTypes,
                chestMinecartItems,
                chestBlockEntityType,
                oldChestBlocks,
                oldChestItems,
                oldChestBlockEntityType
        );
    }

    private static void registerModernChests(ChestRegistrationContext context) {
        ContentContext content = context.context();
        ResourceLocation woodStat = content.stat("open_wood_chest");
        ResourceLocation pumpkinStat = content.stat("open_pumpkin_chest");
        ResourceLocation presentStat = content.stat("open_present");
        ResourceLocation bambooStat = content.stat("open_bamboo_chest");
        ResourceLocation mossStat = content.stat("open_moss_chest");
        ResourceLocation ironStat = content.stat("open_iron_chest");
        ResourceLocation goldStat = content.stat("open_gold_chest");
        ResourceLocation diamondStat = content.stat("open_diamond_chest");
        ResourceLocation obsidianStat = content.stat("open_obsidian_chest");
        ResourceLocation netheriteStat = content.stat("open_netherite_chest");

        Properties presentSettings = Block.Properties.of().mapColor(state -> {
            EsChestType type = state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE);
            if (type == EsChestType.SINGLE) {
                return MapColor.COLOR_RED;
            }
            if (type == EsChestType.FRONT || type == EsChestType.BACK) {
                return MapColor.PLANT;
            }
            return MapColor.SNOW;
        }).strength(2.5f).sound(net.minecraft.world.level.block.SoundType.WOOD);

        addChest(context, compasses.expandedstorage.ForgeMain.id("wood_chest"), woodStat, content.woodTier, content.woodSettings);
        addChest(context, compasses.expandedstorage.ForgeMain.id("pumpkin_chest"), pumpkinStat, content.woodTier, content.pumpkinSettings);
        addChest(context, compasses.expandedstorage.ForgeMain.id("present"), presentStat, content.woodTier, presentSettings);
        addChest(context, compasses.expandedstorage.ForgeMain.id("bamboo_chest"), bambooStat, content.woodTier, content.bambooSettings);
        addMossChest(context, compasses.expandedstorage.ForgeMain.id("moss_chest"), mossStat, content.woodTier, content.mossSettings);
        addChest(context, compasses.expandedstorage.ForgeMain.id("iron_chest"), ironStat, content.ironTier, content.ironSettings);
        addChest(context, compasses.expandedstorage.ForgeMain.id("gold_chest"), goldStat, content.goldTier, content.goldSettings);
        addChest(context, compasses.expandedstorage.ForgeMain.id("diamond_chest"), diamondStat, content.diamondTier, content.diamondSettings);
        addChest(context, compasses.expandedstorage.ForgeMain.id("obsidian_chest"), obsidianStat, content.obsidianTier, content.obsidianSettings);
        addChest(context, compasses.expandedstorage.ForgeMain.id("netherite_chest"), netheriteStat, content.netheriteTier, content.netheriteSettings);
    }

    private static BlockEntityType<ChestBlockEntity> registerChestBlockEntityType(Map<ResourceLocation, ChestBlock> chestBlocks) {
        BlockEntityType<ChestBlockEntity> value = BlockEntityType.Builder.of(
                (pos, state) -> new ChestBlockEntity(CommonMain.getChestBlockEntityType(), pos, state, ((OpenableBlock) state.getBlock()).getBlockId(), ChestItemAccess::new, BasicLockable::new),
                chestBlocks.values().toArray(ChestBlock[]::new)
        ).build(Util.fetchChoiceType(References.BLOCK_ENTITY, CommonMain.CHEST_OBJECT_TYPE.toString()));
        CommonMain.setChestBlockEntityType(value);
        return value;
    }

    private static void registerOldChests(ContentContext context, OldChestRegistrationContext oldChestContext) {
        ResourceLocation woodStat = context.stat("open_old_wood_chest");
        ResourceLocation ironStat = context.stat("open_old_iron_chest");
        ResourceLocation goldStat = context.stat("open_old_gold_chest");
        ResourceLocation diamondStat = context.stat("open_old_diamond_chest");
        ResourceLocation obsidianStat = context.stat("open_old_obsidian_chest");
        ResourceLocation netheriteStat = context.stat("open_old_netherite_chest");

        addOldChest(oldChestContext, compasses.expandedstorage.ForgeMain.id("old_wood_chest"), woodStat, context.woodTier, context.woodSettings);
        addOldChest(oldChestContext, compasses.expandedstorage.ForgeMain.id("old_iron_chest"), ironStat, context.ironTier, context.ironSettings);
        addOldChest(oldChestContext, compasses.expandedstorage.ForgeMain.id("old_gold_chest"), goldStat, context.goldTier, context.goldSettings);
        addOldChest(oldChestContext, compasses.expandedstorage.ForgeMain.id("old_diamond_chest"), diamondStat, context.diamondTier, context.diamondSettings);
        addOldChest(oldChestContext, compasses.expandedstorage.ForgeMain.id("old_obsidian_chest"), obsidianStat, context.obsidianTier, context.obsidianSettings);
        addOldChest(oldChestContext, compasses.expandedstorage.ForgeMain.id("old_netherite_chest"), netheriteStat, context.netheriteTier, context.netheriteSettings);
    }

    private static BlockEntityType<OldChestBlockEntity> registerOldChestBlockEntityType(Map<ResourceLocation, AbstractChestBlock> oldChestBlocks) {
        BlockEntityType<OldChestBlockEntity> value = BlockEntityType.Builder.of(
                (pos, state) -> new OldChestBlockEntity(CommonMain.getOldChestBlockEntityType(), pos, state, ((OpenableBlock) state.getBlock()).getBlockId(), ChestItemAccess::new, BasicLockable::new),
                oldChestBlocks.values().toArray(AbstractChestBlock[]::new)
        ).build(Util.fetchChoiceType(References.BLOCK_ENTITY, CommonMain.OLD_CHEST_OBJECT_TYPE.toString()));
        CommonMain.setOldChestBlockEntityType(value);
        return value;
    }

    private static void addChest(ChestRegistrationContext context, ResourceLocation id, ResourceLocation stat, Tier tier, Properties settings) {
        ChestBlock block = new ChestBlock(tier.getBlockSettings().apply(settings), stat, tier.getSlotCount());
        addChestEntry(context, id, tier, block);
    }

    private static void addMossChest(ChestRegistrationContext context, ResourceLocation id, ResourceLocation stat, Tier tier, Properties settings) {
        ChestBlock block = new MossChestBlock(tier.getBlockSettings().apply(settings), stat, tier.getSlotCount());
        addChestEntry(context, id, tier, block);
    }

    private static void addChestEntry(ChestRegistrationContext context, ResourceLocation id, Tier tier, ChestBlock block) {
        BlockItem item = new ChestBlockItem(block, tier.getItemSettings().apply(new Item.Properties()));
        ResourceLocation cartId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), id.getPath() + "_minecart");
        ChestMinecartItem cartItem = new ForgeChestMinecartItem(new Item.Properties(), cartId);
        EntityType<ChestMinecart> cartEntityType = new EntityType<>(
                (type, level) -> new ChestMinecart(type, level, cartItem, block),
                MobCategory.MISC,
                true,
                true,
                false,
                false,
                ImmutableSet.of(),
                EntityDimensions.scalable(0.98F, 0.7F),
                8,
                3,
                FeatureFlagSet.of()
        );
        context.blocks().put(id, block);
        context.items().put(id, item);
        context.entityTypes().put(cartId, cartEntityType);
        context.cartItems().put(cartId, cartItem);
    }

    private static void registerChestTextures(Map<ResourceLocation, ChestBlock> chestBlocks) {
        for (ResourceLocation id : chestBlocks.keySet()) {
            String blockId = id.getPath();
            CommonMain.declareChestTextures(
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
    }

    private static void addOldChest(OldChestRegistrationContext context, ResourceLocation id, ResourceLocation stat, Tier tier, Properties settings) {
        AbstractChestBlock block = new AbstractChestBlock(tier.getBlockSettings().apply(settings), stat, tier.getSlotCount());
        BlockItem item = new BlockItem(block, tier.getItemSettings().apply(new Item.Properties()));
        context.blocks().put(id, block);
        context.items().put(id, item);
    }

    private static void registerMutationBehaviours() {
        CommonMain.registerMutationBehaviour(ChestContentModule::isChestBlock, MutationMode.MERGE, ChestContentModule::mergeChestMutation);
        CommonMain.registerMutationBehaviour(ChestContentModule::isChestBlock, MutationMode.SPLIT, ChestContentModule::splitChestMutation);
        CommonMain.registerMutationBehaviour(ChestContentModule::isChestBlock, MutationMode.ROTATE, ChestContentModule::rotateChestMutation);
    }

    private static boolean isChestBlock(Block block) {
        return block instanceof AbstractChestBlock;
    }

    private static ToolUsageResult mergeChestMutation(UseOnContext context, Level level, BlockState state, BlockPos pos, ItemStack stack) {
        Player player = context.getPlayer();
        if (player == null) {
            return ToolUsageResult.fail();
        }
        if (state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE) != EsChestType.SINGLE) {
            return ToolUsageResult.fail();
        }

        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("pos")) {
            if (!level.isClientSide()) {
                tag.put("pos", NbtUtils.writeBlockPos(pos));
                player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_start", Utils.ALT_USE), true);
            }
            return ToolUsageResult.fastSuccess();
        }

        BlockPos otherPos = NbtUtils.readBlockPos(tag.getCompound("pos"));
        BlockState otherState = level.getBlockState(otherPos);
        BlockPos delta = otherPos.subtract(pos);
        Direction direction = Direction.fromDelta(delta.getX(), delta.getY(), delta.getZ());
        if (direction == null) {
            player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_not_adjacent"), true);
            tag.remove("pos");
            return ToolUsageResult.fail();
        }
        if (state.getBlock() != otherState.getBlock()) {
            player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_wrong_block"), true);
            tag.remove("pos");
            return ToolUsageResult.fail();
        }
        if (otherState.getValue(AbstractChestBlock.CURSED_CHEST_TYPE) != EsChestType.SINGLE) {
            player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_already_double_chest"), true);
            tag.remove("pos");
            return ToolUsageResult.fail();
        }
        if (state.getValue(BlockStateProperties.HORIZONTAL_FACING) != otherState.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
            player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_wrong_facing"), true);
            tag.remove("pos");
            return ToolUsageResult.fail();
        }

        boolean firstIsDinnerbone = level.getBlockEntity(pos) instanceof OpenableBlockEntity blockEntity && blockEntity.isDinnerbone();
        boolean secondIsDinnerbone = level.getBlockEntity(otherPos) instanceof OpenableBlockEntity blockEntity && blockEntity.isDinnerbone();
        if (firstIsDinnerbone != secondIsDinnerbone) {
            player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_wrong_block"), true);
            tag.remove("pos");
            return ToolUsageResult.fail();
        }

        if (!level.isClientSide()) {
            EsChestType chestType = AbstractChestBlock.getChestType(state.getValue(BlockStateProperties.HORIZONTAL_FACING), direction);
            level.setBlockAndUpdate(pos, state.setValue(AbstractChestBlock.CURSED_CHEST_TYPE, chestType));
            tag.remove("pos");
            player.displayClientMessage(Component.translatable("tooltip.expandedstorage.storage_mutator.merge_end"), true);
        }
        return ToolUsageResult.slowSuccess();
    }

    private static ToolUsageResult splitChestMutation(UseOnContext context, Level level, BlockState state, BlockPos pos, ItemStack stack) {
        if (state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE) == EsChestType.SINGLE) {
            return ToolUsageResult.fail();
        }
        if (!level.isClientSide()) {
            BlockPos otherPos = pos.relative(AbstractChestBlock.getDirectionToAttached(state));
            level.setBlockAndUpdate(pos, state.setValue(AbstractChestBlock.CURSED_CHEST_TYPE, EsChestType.SINGLE));
            level.setBlockAndUpdate(otherPos, state.setValue(AbstractChestBlock.CURSED_CHEST_TYPE, EsChestType.SINGLE));
        }
        return ToolUsageResult.slowSuccess();
    }

    private static ToolUsageResult rotateChestMutation(UseOnContext context, Level level, BlockState state, BlockPos pos, ItemStack stack) {
        if (level.isClientSide()) {
            return ToolUsageResult.slowSuccess();
        }

        EsChestType chestType = state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE);
        if (chestType == EsChestType.SINGLE) {
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING).getClockWise()));
            return ToolUsageResult.slowSuccess();
        }

        BlockPos otherPos = pos.relative(AbstractChestBlock.getDirectionToAttached(state));
        BlockState otherState = level.getBlockState(otherPos);
        if (chestType == EsChestType.TOP || chestType == EsChestType.BOTTOM) {
            level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING).getClockWise()));
            level.setBlockAndUpdate(otherPos, otherState.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING).getClockWise()));
            return ToolUsageResult.slowSuccess();
        }

        level.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite()).setValue(AbstractChestBlock.CURSED_CHEST_TYPE, state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE).getOpposite()));
        level.setBlockAndUpdate(otherPos, otherState.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite()).setValue(AbstractChestBlock.CURSED_CHEST_TYPE, otherState.getValue(AbstractChestBlock.CURSED_CHEST_TYPE).getOpposite()));
        return ToolUsageResult.slowSuccess();
    }
}

