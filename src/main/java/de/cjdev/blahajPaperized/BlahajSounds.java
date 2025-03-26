package de.cjdev.blahajPaperized;

import net.kyori.adventure.sound.Sound;
import net.minecraft.util.RandomSource;

import java.util.ArrayList;
import java.util.List;

public class BlahajSounds {

    public static final List<Sound> BLOCK_CUDDLY_ITEM = new ArrayList<>();

    private static Sound register(String id) {
        return Sound.sound(BlahajPaperized.key(id), Sound.Source.PLAYER, 0.5f, 1);
    }

    public static void init() {
        for (int i = 1; i < 6; i++) {
            BLOCK_CUDDLY_ITEM.add(register("block.blahaj.cuddly_item.use." + i));
        }
    }

    public static Sound getRandomSqueak(RandomSource random) {
        return BLOCK_CUDDLY_ITEM.get(random.nextInt(BLOCK_CUDDLY_ITEM.size()));
    }

}
