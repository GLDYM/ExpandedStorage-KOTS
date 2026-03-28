package compasses.expandedstorage.client.registry;

import compasses.expandedstorage.CommonClient;
import compasses.expandedstorage.misc.Utils;
import compasses.expandedstorage.registry.AllItems;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public final class AllClientItemProperties {
    private AllClientItemProperties() {
    }

    public static void register() {
        ForgeClientAccess.modBus().addListener((FMLClientSetupEvent event) -> {
            ItemProperties.registerGeneric(compasses.expandedstorage.ForgeMain.id("sparrow"), (ClampedItemPropertyFunction) CommonClient::hasSparrowProperty);
            ItemProperties.register(AllItems.STORAGE_MUTATOR, compasses.expandedstorage.ForgeMain.id("tool_mode"), (ClampedItemPropertyFunction) CommonClient::currentMutatorToolMode);
        });
    }
}

