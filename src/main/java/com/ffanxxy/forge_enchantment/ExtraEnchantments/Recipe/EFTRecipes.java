package com.ffanxxy.forge_enchantment.ExtraEnchantments.Recipe;

import com.ffanxxy.forge_enchantment.items.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EFTRecipes {
    private static final List<EFTRecipe> recipes = new ArrayList<>();

    public static EFTRecipe WORDS_BOOK_RECIPE = new EFTRecipe(
            new ItemStack(ModItems.WORDS_BOOK.get()),
            50,
            new ItemStack(Items.DIAMOND,1),
            new ItemStack(Items.DIAMOND,1),
            new ItemStack(Items.DIAMOND,1),
            new ItemStack(Items.DIAMOND,1),
            new ItemStack(Items.DIAMOND,1),
            new ItemStack(Items.DIAMOND,1)
            );

    static {
        recipes.add(WORDS_BOOK_RECIPE);
    }

    public static List<EFTRecipe> getRecipes() {
        return recipes;
    }
}
