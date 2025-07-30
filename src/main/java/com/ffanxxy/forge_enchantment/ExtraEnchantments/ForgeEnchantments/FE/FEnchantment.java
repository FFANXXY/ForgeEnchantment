package com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.FE;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.Effects.CraftingEffects.CraftEffect;

/*
    ForgedEnchantment的抽象类
    ForgedEnchantment's abstract class
 */
public class FEnchantment {
    private final String id;


    public FEnchantment(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
