package de.cjdev.blahajPaperized;

public class PrideSharkItem extends SharkItem {
    private final PrideName prideName;

    public PrideSharkItem(Settings settings, String tooltip, PrideName prideName) {
        super(settings, tooltip);
        this.prideName = prideName;
    }

    public PrideName getPrideName() {
        return prideName;
    }
}
