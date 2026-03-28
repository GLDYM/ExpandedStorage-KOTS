package compasses.expandedstorage.registry.content;

import compasses.expandedstorage.CommonMain;
import compasses.expandedstorage.misc.Tier;
import compasses.expandedstorage.misc.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class ContentContext {
    final Tier woodTier = Tier.WOOD;
    final Tier copperTier = Tier.COPPER;
    final Tier ironTier = Tier.IRON;
    final Tier goldTier = Tier.GOLD;
    final Tier diamondTier = Tier.DIAMOND;
    final Tier obsidianTier = Tier.OBSIDIAN;
    final Tier netheriteTier = Tier.NETHERITE;

    final Properties woodSettings = Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5f).sound(SoundType.WOOD).ignitedByLava();
    final Properties pumpkinSettings = Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).sound(SoundType.WOOD);
    final Properties bambooSettings = Properties.of().mapColor(MapColor.PLANT).strength(1).sound(SoundType.BAMBOO).ignitedByLava();
    final Properties mossSettings = Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.1F).sound(SoundType.MOSS);
    final Properties ironSettings = Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(5, 6).sound(SoundType.METAL);
    final Properties goldSettings = Properties.of().mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.BELL).strength(3, 6).sound(SoundType.METAL);
    final Properties diamondSettings = Properties.of().mapColor(MapColor.DIAMOND).strength(5, 6).sound(SoundType.METAL);
    final Properties obsidianSettings = Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).strength(50, 1200);
    final Properties netheriteSettings = Properties.of().mapColor(MapColor.COLOR_BLACK).strength(50, 1200).sound(SoundType.NETHERITE_BLOCK);

    final List<ResourceLocation> stats = new ArrayList<>();
    final boolean client = FMLLoader.getDist().isClient();
    final boolean manuallyWrapTooltips = false;
    final TagKey<Block> barrelTag = TagKey.create(ForgeRegistries.Keys.BLOCKS, ResourceLocation.fromNamespaceAndPath("forge", "barrels/wooden"));

    ResourceLocation stat(String id) {
        ResourceLocation statId = compasses.expandedstorage.ForgeMain.id(id);
        stats.add(statId);
        return statId;
    }

    void defineBaseItems(Map<ResourceLocation, Item> baseItems) {
        CommonMain.defineTierUpgradePath(baseItems, manuallyWrapTooltips, woodTier, copperTier, ironTier, goldTier, diamondTier, obsidianTier, netheriteTier);
    }
}

