package compasses.expandedstorage.compat.create;

import com.simibubi.create.foundation.data.CreateRegistrate;
import compasses.expandedstorage.ForgeMain;
import compasses.expandedstorage.compat.create.EsChestAttachedCheck;
import compasses.expandedstorage.compat.create.EsMountedStorageTypes;

public class CreateCompat {
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create("expandedstorage");

    public static void register() {
        ForgeMain.LOGGER.info("[ExpandedStorage] Loading Create Compat");
        EsChestAttachedCheck.register();
        EsMountedStorageTypes.register();
    }
}
