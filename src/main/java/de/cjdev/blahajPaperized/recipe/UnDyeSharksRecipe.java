package de.cjdev.blahajPaperized.recipe;

import de.cjdev.blahajPaperized.PrideSharkItem;
import de.cjdev.blahajPaperized.init.ItemInit;
import de.cjdev.papermodapi.api.item.CustomItems;
import de.cjdev.recipeapi.api.recipe.CustomCraftingInput;
import de.cjdev.recipeapi.api.recipe.CustomCraftingRecipe;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UnDyeSharksRecipe implements CustomCraftingRecipe {

    @Override
    public @NotNull CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CustomCraftingInput customCraftingInput) {
        return ItemInit.GRAY_SHARK.getDefaultStack();
    }

    @Override
    public boolean matches(@NotNull CustomCraftingInput customCraftingInput) {
        return customCraftingInput.ingredientCount() == 1 && CustomItems.getItemByStack(customCraftingInput.ingredients().getFirst()) instanceof PrideSharkItem;
    }

    private static List<DyeColor> getDyes(@NotNull CustomCraftingInput customCraftingInput) {
        List<DyeColor> DYES = new ArrayList<>(customCraftingInput.ingredientCount());
        for (ItemStack ingredient : customCraftingInput.ingredients()) {
            switch (ingredient.getType()) {
                case WHITE_DYE, LIGHT_GRAY_DYE, GRAY_DYE, BLACK_DYE, BROWN_DYE, RED_DYE, ORANGE_DYE, YELLOW_DYE,
                     LIME_DYE,
                     GREEN_DYE, CYAN_DYE, LIGHT_BLUE_DYE, BLUE_DYE, PURPLE_DYE, MAGENTA_DYE, PINK_DYE:
                    if (CraftItemStack.unwrap(ingredient).getItem() instanceof DyeItem dyeItem && !CustomItems.isCustomStack(ingredient))
                        DYES.add(dyeItem.getDyeColor());
                    break;
            }
        }
        return DYES;
    }
}
