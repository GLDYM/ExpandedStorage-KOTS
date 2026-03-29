package compasses.expandedstorage.compat.create;

import com.simibubi.create.foundation.data.CreateRegistrate;
import compasses.expandedstorage.ExpandedStorage;
import compasses.expandedstorage.compat.create.EsChestAttachedCheck;
import compasses.expandedstorage.compat.create.EsMountedStorageTypes;

public class CreateCompat {
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create("expandedstorage");

    public static void register() {
        ExpandedStorage.LOGGER.info("[ExpandedStorage] Loading Create Compat");
        EsChestAttachedCheck.register();
        EsMountedStorageTypes.register();
    }
}
