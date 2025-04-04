package de.cjdev.blahajPaperized;

import de.cjdev.blahajPaperized.init.ItemInit;
import de.cjdev.blahajPaperized.recipe.DyeSharksRecipe;
import de.cjdev.blahajPaperized.recipe.UnDyeSharksRecipe;
import de.cjdev.papermodapi.api.component.CustomDataComponent;
import de.cjdev.recipeapi.RecipeAPI;
import net.minecraft.util.ExtraCodecs;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlahajPaperized extends JavaPlugin {

    public static final CustomDataComponent<String> OWNER = new CustomDataComponent<>(key("owner"), ExtraCodecs.PLAYER_NAME);

    @Override
    public void onEnable() {

        BlahajSounds.init();
        ItemInit.load();

        RecipeAPI.addRecipe(key("dye_sharks"), new DyeSharksRecipe());
        RecipeAPI.addRecipe(key("undye_sharks"), new UnDyeSharksRecipe());
    }

    public static NamespacedKey key(String id) {
        return new NamespacedKey("blahaj", id);
    }
}
