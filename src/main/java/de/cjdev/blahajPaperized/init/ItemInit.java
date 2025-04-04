package de.cjdev.blahajPaperized.init;

import de.cjdev.blahajPaperized.CuddlyItem;
import de.cjdev.blahajPaperized.PrideName;
import de.cjdev.blahajPaperized.PrideSharkItem;
import de.cjdev.blahajPaperized.SharkItem;
import de.cjdev.papermodapi.api.item.CustomItem;
import de.cjdev.papermodapi.api.item.CustomItems;
import de.cjdev.papermodapi.api.itemgroup.CustomItemGroup;
import de.cjdev.papermodapi.api.itemgroup.CustomItemGroups;
import net.kyori.adventure.text.Component;

import java.util.LinkedHashMap;
import java.util.Map;

import static de.cjdev.blahajPaperized.BlahajPaperized.key;

public class ItemInit {

    public static final CustomItem GRAY_SHARK = CustomItems.register(key("gray_shark"), settings -> new SharkItem(settings, "block.blahaj.gray_shark.tooltip"));
    public static final CustomItem BLUE_SHARK = CustomItems.register(key("blue_shark"), settings -> new SharkItem(settings, "block.blahaj.blue_shark.tooltip"));
    public static final CustomItem BLUE_WHALE = CustomItems.register(key("blue_whale"), settings -> new CuddlyItem(settings, "block.blahaj.blue_whale.tooltip"));
    public static final CustomItem BREAD = CustomItems.register(key("bread"), settings -> new CuddlyItem(settings, null));
    public static final CustomItem BROWN_BEAR = CustomItems.register(key("brown_bear"), settings -> new CuddlyItem(settings, "block.blahaj.brown_bear.tooltip"));
    public static final Map<PrideName, CustomItem> PRIDE_BLAHAJS = new LinkedHashMap<>(PrideName.values().length);

    public static final CustomItemGroup ITEM_GROUP = CustomItemGroups.register(key("itemgroup"), CustomItemGroup.builder()
            .icon(BLUE_SHARK::getDisplayStack)
            .displayName(Component.translatable("lol.idk.what.this.should.be"))
            .entries((b, entries) -> {
                entries.add(GRAY_SHARK);
                entries.add(BLUE_SHARK);
                entries.add(BLUE_WHALE);
                entries.add(BREAD);
                entries.add(BROWN_BEAR);
                entries.addAll(PRIDE_BLAHAJS.values());
            })
            .build());

    public static void load() {
        for (PrideName prideName : PrideName.values()) {
            PRIDE_BLAHAJS.put(prideName, CustomItems.register(key(prideName.name() + "_shark"), settings -> new PrideSharkItem(settings, "block.blahaj.blue_shark.tooltip", prideName)));
        }
    }

}
