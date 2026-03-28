package compasses.expandedstorage.mixin.client;

import compasses.expandedstorage.client.gui.PageScreen;
import compasses.expandedstorage.client.gui.ScrollScreen;
import compasses.expandedstorage.client.gui.SingleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.violetmoon.quark.api.IQuarkButtonAllowed;

@Mixin(value = {PageScreen.class, SingleScreen.class, ScrollScreen.class}, remap = false)
public class QuarkButtonAllowedMixin implements IQuarkButtonAllowed {

}

