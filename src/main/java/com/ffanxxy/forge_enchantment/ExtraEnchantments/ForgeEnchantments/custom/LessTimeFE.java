package com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.custom;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.Craft.Modifiers.Modifiers;
import com.ffanxxy.forge_enchantment.ExtraEnchantments.Craft.Modifiers.NeedTickModifier;
import com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.FE.FEnchantment;
import com.ffanxxy.forge_enchantment.ForgeEnchantment;
import com.ffanxxy.forge_enchantment.utils.ModTags;

public class LessTimeFE extends FEnchantment {
     private static final Modifiers LessTiemEffect = Modifiers.create(
             NeedTickModifier.create(NeedTickModifier.Operation.SUB_TICK, 10)
     );

    public LessTimeFE() {
        super(ForgeEnchantment.MODID, "less_time", ModTags.Items.WORDS_ITEMS, ModTags.Items.WORDS_ITEMS, 3,
                LessTiemEffect);

    }
}
