package de.cjdev.blahajPaperized;

import de.cjdev.papermodapi.api.component.CustomDataComponent;
import de.cjdev.papermodapi.api.item.CustomItem;
import de.cjdev.papermodapi.api.item.CustomItems;
import de.cjdev.papermodapi.api.itemgroup.CustomItemGroup;
import de.cjdev.papermodapi.api.itemgroup.CustomItemGroups;
import net.kyori.adventure.text.Component;
import net.minecraft.util.ExtraCodecs;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class BlahajPaperized extends JavaPlugin implements Listener {

    public static final CustomDataComponent<String> OWNER = new CustomDataComponent<>(key("owner"), ExtraCodecs.PLAYER_NAME);

    public static final List<String> PRIDE_NAMES = List.of(
            "ace", "agender", "aro", "aroace", "bi", "demiboy", "demigirl",
            "demi_r", "demi_s", "enby", "gay", "genderfluid", "genderqueer", "greyrose",
            "grey_r", "grey_s", "intersex", "lesbian", "pan", "poly", "pride", "trans");

    @Override
    public void onEnable() {

        BlahajSounds.init();

        final CustomItem GRAY_SHARK = CustomItems.register(key("gray_shark"), settings -> new CuddlyItem(settings, "item.blahaj.gray_shark.tooltip"));
        final CustomItem BLUE_SHARK = CustomItems.register(key("blue_shark"), settings -> new CuddlyItem(settings, "item.blahaj.blue_shark.tooltip"));
        final CustomItem BLUE_WHALE = CustomItems.register(key("blue_whale"), settings -> new CuddlyItem(settings, "item.blahaj.blue_whale.tooltip"));
        final CustomItem BREAD = CustomItems.register(key("bread"), settings -> new CuddlyItem(settings, null));
        final CustomItem BROWN_BEAR = CustomItems.register(key("brown_bear"), settings -> new CuddlyItem(settings, "item.blahaj.brown_bear.tooltip"));

        final List<CustomItem> PRIDE_BLAHAJS = PRIDE_NAMES.stream().map(prideName -> CustomItems.register(key(prideName + "_shark"), settings -> new CuddlyItem(settings, "item.blahaj.blue_shark.tooltip"))).toList();

        CustomItemGroups.register(key("itemgroup"), CustomItemGroup.builder()
                .icon(BLUE_SHARK::getDisplayStack)
                .displayName(Component.translatable("lol.idk.what.this.should.be"))
                .entries((b, entries) -> {
                    entries.add(GRAY_SHARK);
                    entries.add(BLUE_SHARK);
                    entries.add(BLUE_WHALE);
                    entries.add(BREAD);
                    entries.add(BROWN_BEAR);
                    entries.addAll(PRIDE_BLAHAJS);
                })
                .build());

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public static NamespacedKey key(String id) {
        return new NamespacedKey("blahaj", id);
    }
}
