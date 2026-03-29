package compasses.expandedstorage.registry;

import compasses.expandedstorage.misc.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public final class AllCreativeTabs {
    private AllCreativeTabs() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener((RegisterEvent event) ->
                event.register(Registries.CREATIVE_MODE_TAB, helper ->
                        helper.register(compasses.expandedstorage.ForgeMain.id("tab"), CreativeModeTab.builder()
                                .icon(() -> ForgeRegistries.ITEMS.getValue(compasses.expandedstorage.ForgeMain.id("netherite_chest")).getDefaultInstance())
                                .displayItems((itemDisplayParameters, output) ->
                                        DisplayItems.generate(stack ->
                                                output.accept(stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS)))
                                .title(Component.translatable("itemGroup.expandedstorage.tab"))
                                .build())));
    }
}

