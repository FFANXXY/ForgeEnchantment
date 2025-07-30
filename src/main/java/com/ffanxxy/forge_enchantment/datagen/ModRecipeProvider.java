package com.ffanxxy.forge_enchantment.datagen;

import com.ffanxxy.forge_enchantment.blocks.ModBlocks;
import com.ffanxxy.forge_enchantment.items.ModItems;
import net.minecraft.commands.arguments.TimeArgument;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        /*
            3*MagicPaper 1*leather with enchantment
         */

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.WORDS_PAPER)
                .requires(ModItems.MAGIC_PAPER, 3)
                .unlockedBy("get_magic_paper",has(ModItems.MAGIC_PAPER)).save(recipeOutput);


//        SmithingTrimRecipeBuilder.smithingTrim(
//
//        );
    }
}
