package compasses.expandedstorage.registry;

import compasses.expandedstorage.api.EsChestType;
import compasses.expandedstorage.block.AbstractChestBlock;
import compasses.expandedstorage.block.entity.OldChestBlockEntity;
import compasses.expandedstorage.block.entity.extendable.OpenableBlockEntity;
import compasses.expandedstorage.block.misc.DoubleItemAccess;
import compasses.expandedstorage.block.strategies.ItemAccess;
import compasses.expandedstorage.ExpandedStorage;
import compasses.expandedstorage.item.EntityInteractableItem;
import compasses.expandedstorage.misc.Utils;
import compasses.expandedstorage.network.ClientboundUpdateRecipesMessage;
import compasses.expandedstorage.recipe.BlockConversionRecipe;
import compasses.expandedstorage.recipe.ConversionRecipeManager;
import compasses.expandedstorage.recipe.ConversionRecipeReloadListener;
import compasses.expandedstorage.recipe.EntityConversionRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public final class ModEvents {

    private ModEvents() {
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.addListener((AddReloadListenerEvent event) -> event.addListener(new ConversionRecipeReloadListener()));
        MinecraftForge.EVENT_BUS.addListener((OnDatapackSyncEvent event) -> sendConversionRecipesToClient(event.getPlayer(), ConversionRecipeManager.INSTANCE.getBlockRecipes(), ConversionRecipeManager.INSTANCE.getEntityRecipes()));

        MinecraftForge.EVENT_BUS.addGenericListener(BlockEntity.class, (AttachCapabilitiesEvent<BlockEntity> event) -> {
            if (event.getObject() instanceof OpenableBlockEntity entity) {
                event.addCapability(ExpandedStorage.id("item_access"), new ICapabilityProvider() {
                    @NotNull
                    @Override
                    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
                        if (capability == ForgeCapabilities.ITEM_HANDLER) {
                            return getItemAccess(entity.getLevel(), entity.getBlockPos(), entity.getBlockState(), entity)
                                    .map(access -> LazyOptional.of(() -> (T) access.get()))
                                    .orElse(LazyOptional.empty());
                        }
                        return LazyOptional.empty();
                    }
                });
            }
        });

        MinecraftForge.EVENT_BUS.addListener((PlayerInteractEvent.EntityInteractSpecific event) -> {
            InteractionResult result = interactWithEntity(event.getLevel(), event.getEntity(), event.getHand(), event.getTarget());
            if (result != InteractionResult.PASS) {
                event.setCancellationResult(result);
                event.setCanceled(true);
            }
        });
    }

    private static <T> Optional<ItemAccess<T>> getItemAccess(Level level, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
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

    private static InteractionResult interactWithEntity(Level level, Player player, InteractionHand hand, Entity entity) {
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

    public static void sendConversionRecipesToClient(@Nullable ServerPlayer target, List<BlockConversionRecipe<?>> blockRecipes, List<EntityConversionRecipe<?>> entityRecipes) {
        ClientboundUpdateRecipesMessage message = new ClientboundUpdateRecipesMessage(blockRecipes, entityRecipes);
        if (target == null) {
            ExpandedStorage.CHANNEL.send(PacketDistributor.ALL.noArg(), message);
            return;
        }
        if (!ExpandedStorage.CHANNEL.isRemotePresent(target.connection.connection)) {
            target.connection.disconnect(Component.translatable("text.expandedstorage.disconnect.old_version"));
            return;
        }
        ExpandedStorage.CHANNEL.send(PacketDistributor.PLAYER.with(() -> target), message);
    }
}

