package com.ffanxxy.forge_enchantment.ExtraEnchantments.Craft.EFT;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.Contexts.CraftingContext.AfterCraftingContext;

public class EFCraft {
    private final AfterCraftingContext Incontext;

    private AfterCraftingContext DealContext;

    public EFCraft(AfterCraftingContext context) {
        this.Incontext = context;
    }

    public AfterCraftingContext getResult() {
       return this.DealContext;
    }

    public void deal() {
        this.DealContext = Incontext;
    }

}
