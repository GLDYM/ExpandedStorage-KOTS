package compasses.expandedstorage.client.registry;

import compasses.expandedstorage.client.ChestBlockEntityRenderer;
import compasses.expandedstorage.registry.AllBlockEntityTypes;
import compasses.expandedstorage.registry.AllEntityTypes;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public final class AllClientRenderers {
    private AllClientRenderers() {
    }

    public static void register(IEventBus modBus) {
        modBus.addListener((EntityRenderersEvent.RegisterRenderers event) ->
                event.registerBlockEntityRenderer(AllBlockEntityTypes.chestBlockEntityType(), ChestBlockEntityRenderer::new));

        modBus.addListener((EntityRenderersEvent.RegisterRenderers event) -> {
            for (var type : AllEntityTypes.chestMinecartEntityTypes()) {
                event.registerEntityRenderer(type, context -> new MinecartRenderer<>(context, ModelLayers.CHEST_MINECART));
            }
        });
    }
}

