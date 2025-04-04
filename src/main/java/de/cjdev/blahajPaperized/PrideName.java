package de.cjdev.blahajPaperized;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static de.cjdev.blahajPaperized.PrideName.ColorFlag.*;

public enum PrideName {
    ace(BLACK | GRAY | WHITE | PURPLE),
    agender(LIME | WHITE | GRAY | BLACK),
    aro(GREEN | LIME | WHITE | GRAY | BLACK),
    aroace(YELLOW | ORANGE | BLUE | LIGHT_BLUE),
    bi(BLUE | PINK | MAGENTA),
    demiboy(LIGHT_BLUE | WHITE | GRAY),
    demigirl(WHITE | MAGENTA | GRAY),
    demi_r(WHITE | GREEN | BLACK),
    demi_s(WHITE | PURPLE | BLACK),
    enby(PURPLE | MAGENTA | WHITE | YELLOW),
    gay(BLUE | LIGHT_BLUE | WHITE | LIME | GREEN),
    genderfluid(PINK | WHITE | MAGENTA | BLUE | BLACK),
    genderqueer(MAGENTA | GREEN | WHITE),
    greyrose(BLUE | PURPLE | LIGHT_GRAY),
    grey_r(GREEN | GRAY),
    grey_s(MAGENTA | GRAY),
    intersex(PURPLE | YELLOW),
    lesbian(RED | ORANGE | WHITE | PINK | PURPLE),
    pan(MAGENTA | YELLOW | LIGHT_BLUE),
    poly(LIGHT_BLUE | RED | BLACK | WHITE | YELLOW),
    pride(RED | ORANGE | YELLOW | GREEN | BLUE | PURPLE),
    trans(LIGHT_BLUE | PINK);

    public final int flag;

    private static final Map<Integer, PrideName> LOOKUP = new HashMap<>(values().length);

    PrideName(int flag) {
        this.flag = flag;
    }

    public static @Nullable PrideName fromFlags(int flags) {
        return LOOKUP.get(flags);
    }

    static {
        for (PrideName prideName : values()) {
            LOOKUP.put(prideName.flag, prideName);
        }
    }

    public static class ColorFlag {
        public static final int WHITE = 1;
        public static final int ORANGE = 1 << 1;
        public static final int MAGENTA = 1 << 2;
        public static final int LIGHT_BLUE = 1 << 3;
        public static final int YELLOW = 1 << 4;
        public static final int LIME = 1 << 5;
        public static final int PINK = 1 << 6;
        public static final int GRAY = 1 << 7;
        public static final int LIGHT_GRAY = 1 << 8;
        public static final int CYAN = 1 << 9;
        public static final int PURPLE = 1 << 10;
        public static final int BLUE = 1 << 11;
        public static final int BROWN = 1 << 12;
        public static final int GREEN = 1 << 13;
        public static final int RED = 1 << 14;
        public static final int BLACK = 1 << 15;
    }
}
