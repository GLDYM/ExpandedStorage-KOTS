package compasses.expandedstorage.registry;

import com.google.common.collect.ImmutableSet;
import compasses.expandedstorage.block.ChestBlock;
import compasses.expandedstorage.entity.ChestMinecart;
import compasses.expandedstorage.item.ChestMinecartItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Objects;

public final class AllEntityTypes {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, compasses.expandedstorage.ForgeMain.MOD_ID);
    private static final RegistryObject<EntityType<?>> WOOD_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("wood_chest_minecart"));
    private static final RegistryObject<EntityType<?>> PUMPKIN_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("pumpkin_chest_minecart"));
    private static final RegistryObject<EntityType<?>> PRESENT_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("present_minecart"));
    private static final RegistryObject<EntityType<?>> BAMBOO_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("bamboo_chest_minecart"));
    private static final RegistryObject<EntityType<?>> MOSS_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("moss_chest_minecart"));
    private static final RegistryObject<EntityType<?>> IRON_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("iron_chest_minecart"));
    private static final RegistryObject<EntityType<?>> GOLD_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("gold_chest_minecart"));
    private static final RegistryObject<EntityType<?>> DIAMOND_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("diamond_chest_minecart"));
    private static final RegistryObject<EntityType<?>> OBSIDIAN_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("obsidian_chest_minecart"));
    private static final RegistryObject<EntityType<?>> NETHERITE_CHEST_MINECART_REGISTRY = register(compasses.expandedstorage.ForgeMain.id("netherite_chest_minecart"));

    private AllEntityTypes() {
    }

    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }

    public static List<EntityType<ChestMinecart>> chestMinecartEntityTypes() {
        return List.of(
                chestMinecartEntityType(WOOD_CHEST_MINECART_REGISTRY),
                chestMinecartEntityType(PUMPKIN_CHEST_MINECART_REGISTRY),
                chestMinecartEntityType(PRESENT_MINECART_REGISTRY),
                chestMinecartEntityType(BAMBOO_CHEST_MINECART_REGISTRY),
                chestMinecartEntityType(MOSS_CHEST_MINECART_REGISTRY),
                chestMinecartEntityType(IRON_CHEST_MINECART_REGISTRY),
                chestMinecartEntityType(GOLD_CHEST_MINECART_REGISTRY),
                chestMinecartEntityType(DIAMOND_CHEST_MINECART_REGISTRY),
                chestMinecartEntityType(OBSIDIAN_CHEST_MINECART_REGISTRY),
                chestMinecartEntityType(NETHERITE_CHEST_MINECART_REGISTRY)
        );
    }

    private static RegistryObject<EntityType<?>> register(ResourceLocation id) {
        return ENTITY_TYPES.register(id.getPath(), () -> createMinecartEntityType(id));
    }

    private static EntityType<ChestMinecart> createMinecartEntityType(ResourceLocation cartId) {
        String cartPath = cartId.getPath();
        ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(cartId.getNamespace(), cartPath.substring(0, cartPath.length() - "_minecart".length()));
        ChestMinecartItem cartItem = (ChestMinecartItem) Objects.requireNonNull(BuiltInRegistries.ITEM.get(cartId), "Missing chest minecart item for: " + cartId);
        ChestBlock chestBlock = (ChestBlock) Objects.requireNonNull(BuiltInRegistries.BLOCK.get(blockId), "Missing chest block for cart: " + cartId);
        return new EntityType<>(
                (type, level) -> new ChestMinecart(type, level, cartItem, chestBlock),
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
    }

    @SuppressWarnings("unchecked")
    private static EntityType<ChestMinecart> chestMinecartEntityType(RegistryObject<EntityType<?>> registryObject) {
        return (EntityType<ChestMinecart>) registryObject.get();
    }
}

