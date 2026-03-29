package compasses.expandedstorage;

import compasses.expandedstorage.config.ModClientConfig;
import compasses.expandedstorage.network.ClientboundUpdateRecipesMessage;
import compasses.expandedstorage.registry.ModCompat;
import compasses.expandedstorage.registry.ModEvents;
import compasses.expandedstorage.registry.ModRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import compasses.expandedstorage.client.registry.ClientRegistry;

@Mod(ExpandedStorage.MOD_ID)
public final class ExpandedStorage {
    public static final String MOD_ID = "expandedstorage";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(id("channel"), () -> "1.0", "1.0"::equals, "1.0"::equals);

    public ExpandedStorage(FMLJavaModLoadingContext contentBus) {
        IEventBus modBus = contentBus.getModEventBus();
        CHANNEL.registerMessage(0, ClientboundUpdateRecipesMessage.class, ClientboundUpdateRecipesMessage::encode, ClientboundUpdateRecipesMessage::decode, ClientboundUpdateRecipesMessage::handle);

        ModRegistry.register(modBus);
        ModEvents.register();
        ModCompat.register(modBus);

        if (FMLLoader.getDist() == Dist.CLIENT) {
            contentBus.registerConfig(ModConfig.Type.CLIENT, ModClientConfig.SPEC, "expandedstorage-client.toml");
            ClientRegistry.register(modBus);
        }
    }

    public static ResourceLocation id(@Nonnull String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}

