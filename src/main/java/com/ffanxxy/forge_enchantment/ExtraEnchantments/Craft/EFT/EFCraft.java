package com.ffanxxy.forge_enchantment.ExtraEnchantments.Craft.EFT;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.Contexts.CraftingContext.AfterCraftingContext;

/**
 * 处理 修饰器 的类，直接输出合成结果， 使用 ACContext作为存储
 */
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
        // 处理逻辑 ...
        this.DealContext = Incontext;
    }

}
