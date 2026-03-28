package compasses.expandedstorage.client.registry;

import compasses.expandedstorage.client.ChestBlockEntityRenderer;
import compasses.expandedstorage.entity.ChestMinecart;
import compasses.expandedstorage.registry.ModRegistry;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;

public final class AllClientRenderers {
    private AllClientRenderers() {
    }

    public static void register() {
        ForgeClientAccess.modBus().addListener((EntityRenderersEvent.RegisterRenderers event) ->
                event.registerBlockEntityRenderer(ModRegistry.chestBlockEntityType(), ChestBlockEntityRenderer::new));

        ForgeClientAccess.modBus().addListener((EntityRenderersEvent.RegisterRenderers event) -> {
            for (var type : ModRegistry.chestMinecartEntityTypes()) {
                event.registerEntityRenderer(type, context -> new MinecartRenderer<>(context, ModelLayers.CHEST_MINECART));
            }
        });
    }
}

