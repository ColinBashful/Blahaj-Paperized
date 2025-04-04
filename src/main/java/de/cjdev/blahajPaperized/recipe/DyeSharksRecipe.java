package de.cjdev.blahajPaperized.recipe;

import de.cjdev.blahajPaperized.PrideName;
import de.cjdev.blahajPaperized.SharkItem;
import de.cjdev.blahajPaperized.init.ItemInit;
import de.cjdev.papermodapi.api.item.CustomItem;
import de.cjdev.papermodapi.api.item.CustomItems;
import de.cjdev.recipeapi.RecipeAPI;
import de.cjdev.recipeapi.api.recipe.CustomCraftingInput;
import de.cjdev.recipeapi.api.recipe.CustomCraftingRecipe;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DyeSharksRecipe implements CustomCraftingRecipe {
    @Override
    public @NotNull CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    private static @Nullable PrideName getPrideName(List<DyeColor> dyes) {
        int flags = 0;

        for (DyeColor dyeColor : dyes) {
            int flag = 1 << dyeColor.getId();
            if ((flags & flag) != 0)
                return null;
            flags |= flag;
        }

        return PrideName.fromFlags(flags);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CustomCraftingInput customCraftingInput) {
        List<DyeColor> DYES = getDyes(customCraftingInput);

        return ItemInit.PRIDE_BLAHAJS.get(getPrideName(DYES)).getDefaultStack();
    }

    @Override
    public boolean matches(@NotNull CustomCraftingInput customCraftingInput) {
        if (customCraftingInput.ingredientCount() < 2)
            return false;
        List<DyeColor> DYES = getDyes(customCraftingInput);

        long dyeCount = DYES.size();
        if (dyeCount > 6)
            return false;

        List<SharkItem> SHARK_ITEMS = new ArrayList<>(customCraftingInput.ingredientCount());
        for (ItemStack ingredient : customCraftingInput.ingredients()) {
            if (!(CustomItems.getItemByStack(ingredient) instanceof SharkItem sharkItem)) continue;
            SHARK_ITEMS.add(sharkItem);
        }
        if (dyeCount + Math.min(SHARK_ITEMS.size(), 1) != customCraftingInput.ingredientCount())
            return false;
        return getPrideName(DYES) != null;
    }

    private static List<DyeColor> getDyes(@NotNull CustomCraftingInput customCraftingInput) {
        List<DyeColor> DYES = new ArrayList<>(customCraftingInput.ingredientCount());
        for (ItemStack ingredient : customCraftingInput.ingredients()) {
            if (!(CraftItemStack.unwrap(ingredient).getItem() instanceof DyeItem dyeItem) || CustomItems.isCustomStack(ingredient)) continue;
            DYES.add(dyeItem.getDyeColor());
        }
        return DYES;
    }
}
