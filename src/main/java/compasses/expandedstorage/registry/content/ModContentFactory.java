package compasses.expandedstorage.registry.content;

import compasses.expandedstorage.registry.AllStats;

public final class ModContentFactory {
    private ModContentFactory() {
    }

    public static void constructContent() {
        var context = new ContentContext();
        ChestContentModule.bootstrap(context);
        BarrelContentModule.bootstrap(context);
        MiniStorageContentModule.bootstrap(context);

        AllStats.reset();
        AllStats.set(context.stats);

        ThemeSwapModule.register();
    }
}

