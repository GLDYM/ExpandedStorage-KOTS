package compasses.expandedstorage;

import compasses.expandedstorage.api.EsChestType;
import compasses.expandedstorage.block.AbstractChestBlock;
import compasses.expandedstorage.block.entity.BarrelBlockEntity;
import compasses.expandedstorage.block.entity.ChestBlockEntity;
import compasses.expandedstorage.block.entity.MiniStorageBlockEntity;
import compasses.expandedstorage.block.entity.OldChestBlockEntity;
import compasses.expandedstorage.block.entity.extendable.OpenableBlockEntity;
import compasses.expandedstorage.block.misc.DoubleItemAccess;
import compasses.expandedstorage.block.strategies.ItemAccess;
import compasses.expandedstorage.item.BlockMutatorBehaviour;
import compasses.expandedstorage.item.EntityInteractableItem;
import compasses.expandedstorage.item.MutationMode;
import compasses.expandedstorage.item.StorageConversionKit;
import compasses.expandedstorage.item.ToolUsageResult;
import compasses.expandedstorage.misc.Tier;
import compasses.expandedstorage.misc.Utils;
import compasses.expandedstorage.registry.DisplayItems;
import compasses.expandedstorage.registry.content.ModContentFactory;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class CommonMain {
    public static final ResourceLocation BARREL_OBJECT_TYPE = compasses.expandedstorage.ForgeMain.id("barrel");
    public static final ResourceLocation CHEST_OBJECT_TYPE = compasses.expandedstorage.ForgeMain.id("chest");
    public static final ResourceLocation OLD_CHEST_OBJECT_TYPE = compasses.expandedstorage.ForgeMain.id("old_chest");
    public static final ResourceLocation MINI_STORAGE_OBJECT_TYPE = compasses.expandedstorage.ForgeMain.id("mini_chest");

    private static final Map<Map.Entry<Predicate<Block>, MutationMode>, BlockMutatorBehaviour> BLOCK_MUTATOR_BEHAVIOURS = new HashMap<>();
    private static final Map<ResourceLocation, ResourceLocation[]> CHEST_TEXTURES = new HashMap<>();

    private static BlockEntityType<ChestBlockEntity> chestBlockEntityType;
    private static BlockEntityType<OldChestBlockEntity> oldChestBlockEntityType;
    private static BlockEntityType<BarrelBlockEntity> barrelBlockEntityType;
    private static BlockEntityType<MiniStorageBlockEntity> miniStorageBlockEntityType;

    private CommonMain() {
    }

    public static BlockEntityType<ChestBlockEntity> getChestBlockEntityType() {
        return chestBlockEntityType;
    }

    public static BlockEntityType<OldChestBlockEntity> getOldChestBlockEntityType() {
        return oldChestBlockEntityType;
    }

    public static BlockEntityType<BarrelBlockEntity> getBarrelBlockEntityType() {
        return barrelBlockEntityType;
    }

    public static BlockEntityType<MiniStorageBlockEntity> getMiniStorageBlockEntityType() {
        return miniStorageBlockEntityType;
    }

    public static void setChestBlockEntityType(BlockEntityType<ChestBlockEntity> value) {
        chestBlockEntityType = value;
    }

    public static void setOldChestBlockEntityType(BlockEntityType<OldChestBlockEntity> value) {
        oldChestBlockEntityType = value;
    }

    public static void setBarrelBlockEntityType(BlockEntityType<BarrelBlockEntity> value) {
        barrelBlockEntityType = value;
    }

    public static void setMiniStorageBlockEntityType(BlockEntityType<MiniStorageBlockEntity> value) {
        miniStorageBlockEntityType = value;
    }

    public static void defineTierUpgradePath(Map<ResourceLocation, Item> items, boolean wrapTooltipManually, Tier... tiers) {
        int numTiers = tiers.length;
        for (int fromIndex = 0; fromIndex < numTiers - 1; fromIndex++) {
            Tier fromTier = tiers[fromIndex];
            for (int toIndex = fromIndex + 1; toIndex < numTiers; toIndex++) {
                Tier toTier = tiers[toIndex];
                ResourceLocation itemId = compasses.expandedstorage.ForgeMain.id(fromTier.getId().getPath() + "_to_" + toTier.getId().getPath() + "_conversion_kit");
                Item.Properties settings = fromTier.getItemSettings().andThen(toTier.getItemSettings()).apply(new Item.Properties().stacksTo(16));
                items.put(itemId, new StorageConversionKit(settings, fromTier.getId(), toTier.getId(), wrapTooltipManually));
            }
        }
    }

    public static void declareChestTextures(ResourceLocation block, ResourceLocation singleTexture, ResourceLocation leftTexture, ResourceLocation rightTexture, ResourceLocation topTexture, ResourceLocation bottomTexture, ResourceLocation frontTexture, ResourceLocation backTexture) {
        if (!CHEST_TEXTURES.containsKey(block)) {
            ResourceLocation[] collection = {topTexture, bottomTexture, frontTexture, backTexture, leftTexture, rightTexture, singleTexture};
            CHEST_TEXTURES.put(block, collection);
            return;
        }
        throw new IllegalArgumentException("Tried registering chest textures for \"" + block + "\" which already has textures.");
    }

    public static ResourceLocation getChestTexture(ResourceLocation block, EsChestType chestType) {
        if (CHEST_TEXTURES.containsKey(block)) {
            return CHEST_TEXTURES.get(block)[chestType.ordinal()];
        }
        return MissingTextureAtlasSprite.getLocation();
    }

    public static void registerMutationBehaviour(Predicate<Block> predicate, MutationMode mode, BlockMutatorBehaviour behaviour) {
        BLOCK_MUTATOR_BEHAVIOURS.put(Map.entry(predicate, mode), behaviour);
    }

    public static BlockMutatorBehaviour getBlockMutatorBehaviour(Block block, MutationMode mode) {
        for (Map.Entry<Map.Entry<Predicate<Block>, MutationMode>, BlockMutatorBehaviour> entry : BLOCK_MUTATOR_BEHAVIOURS.entrySet()) {
            Map.Entry<Predicate<Block>, MutationMode> pair = entry.getKey();
            if (pair.getValue() == mode && pair.getKey().test(block)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static void constructContent() {
        ModContentFactory.constructContent();
    }

    public static <T> Optional<ItemAccess<T>> getItemAccess(Level level, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (blockEntity instanceof OldChestBlockEntity entity) {
            DoubleItemAccess<T> access = (DoubleItemAccess<T>) entity.getItemAccess();
            EsChestType type = state.getValue(AbstractChestBlock.CURSED_CHEST_TYPE);
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            if (access.hasCachedAccess() || type == EsChestType.SINGLE) {
                return Optional.of(access);
            }
            if (level.getBlockEntity(pos.relative(AbstractChestBlock.getDirectionToAttached(type, facing))) instanceof OldChestBlockEntity otherEntity) {
                DoubleItemAccess<T> otherAccess = (DoubleItemAccess<T>) otherEntity.getItemAccess();
                if (otherAccess.hasCachedAccess()) {
                    return Optional.of(otherAccess);
                }
                DoubleItemAccess<T> first;
                DoubleItemAccess<T> second;
                if (AbstractChestBlock.getBlockType(type) == DoubleBlockCombiner.BlockType.FIRST) {
                    first = access;
                    second = otherAccess;
                } else {
                    first = otherAccess;
                    second = access;
                }
                first.setOther(second);
                return Optional.of(first);
            }
        } else if (blockEntity instanceof OpenableBlockEntity entity) {
            return Optional.of((ItemAccess<T>) entity.getItemAccess());
        }
        return Optional.empty();
    }

    public static InteractionResult interactWithEntity(Level level, Player player, InteractionHand hand, Entity entity) {
        if (player.isSpectator() || !player.isShiftKeyDown()) {
            return InteractionResult.PASS;
        }
        ItemStack handStack = player.getItemInHand(hand);
        if (handStack.getItem() instanceof EntityInteractableItem item) {
            if (player.getCooldowns().isOnCooldown(handStack.getItem())) {
                return InteractionResult.CONSUME;
            }
            InteractionResult result = item.es_interactEntity(level, entity, player, hand, handStack);
            if (result == InteractionResult.FAIL) {
                result = InteractionResult.CONSUME;
            }
            return result;
        }
        return InteractionResult.PASS;
    }

    @SuppressWarnings("unused")
    public static void generateDisplayItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, Consumer<ItemStack> output) {
        DisplayItems.generate(output);
    }
}

