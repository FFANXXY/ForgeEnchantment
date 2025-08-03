package com.ffanxxy.forge_enchantment.ExtraEnchantments.Contexts.CraftingContext;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.Contexts.EffectContext;
import com.ffanxxy.forge_enchantment.ExtraEnchantments.Recipe.EFTRecipe;
import com.ffanxxy.forge_enchantment.ExtraEnchantments.Recipe.EFTRecipes;
import com.ffanxxy.forge_enchantment.ExtraEnchantments.utils.Results.CraftResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class AfterCraftingContext implements EffectContext {
    private final Level level;
    private final Player player;
    private final List<ItemStack> raws;
    private final ItemStack useItem;

    private final EFTRecipe recipe;
    private final Boolean CanCraft;
    private final List<ItemStack> lastitem;


    private AfterCraftingContext(Level level, Player player, List<ItemStack> raws, ItemStack useItem) {
        this.level = level;
        this.player = player;
        this.raws = raws;
        this.useItem = useItem;

        // 多次调用canCraft 消耗过大，考虑移到新线程
        List<EFTRecipe> recipes = EFTRecipes.getRecipes().stream()
                .filter(eftRecipe -> eftRecipe.canCraft(raws).getSuccess()).toList();
        if(recipes.isEmpty()) {
            this.recipe = EFTRecipes.EMPTY();
            this.CanCraft = false;
            this.lastitem = this.raws;
        } else {
            this.recipe = recipes.getFirst();
            CraftResult result = recipe.canCraft(raws);
            this.CanCraft = result.getSuccess();

            if (this.CanCraft) {
                this.lastitem = result.getLast();
            } else {
                this.lastitem = raws;
            }
        }
    }

    public static AfterCraftingContext create(Level level, Player player, List<ItemStack> raws, ItemStack useItem) {
        return new AfterCraftingContext(level, player, raws, useItem);
    }

    public EFTRecipe getRecipe() {
        return this.recipe;
    }

    public List<ItemStack> getLastitem() {
        return lastitem;
    }

    public Level getLevel() {
        return this.level;
    }

    public Player getPlayer() {
        return player;
    }

    public List<ItemStack> getRaws() {
        return raws;
    }

    public ItemStack getUseItem() {
        return useItem;
    }

    public ItemStack getResultItemStack() {
        return recipe.getResult();
    }

    public int getRecipeNeedTick() {
        return  recipe.getNeedtick();
    }

    public Boolean getCanCraft() {
        return this.CanCraft;
    }

}
