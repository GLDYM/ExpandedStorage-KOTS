package compasses.expandedstorage.impl.compat.create;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import compasses.expandedstorage.impl.ForgeMain;
import compasses.expandedstorage.impl.block.AbstractChestBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;


public class EsMountedStorageTypes {
    private static final CreateRegistrate REGISTRATE = CreateRegistrate.create("expandedstorage");

    public static final RegistryEntry<EsChestMountedStorageType> ES_CHEST =
        REGISTRATE.mountedItemStorage("es_chest", EsChestMountedStorageType::new)
            .associateBlockTag(TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("expandedstorage", "es_chest_mounted_storage")))
            .register();

    public static void register() {
        ForgeMain.LOGGER.info("[ES] Try to Compat Create");
        ForgeMain.LOGGER.info("[ES] Where are u now? {}", ES_CHEST);
    }
}