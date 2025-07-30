package com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.FE.FEnchantment;
import com.ffanxxy.forge_enchantment.component.ModDataComponents;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FEHelper {
    public static void enchant(ItemStack stack, FEnchantment FEnchantment, int level) {
        List<ForgedEnchantment> list = stack.get(ModDataComponents.FORGED_ENCHANTMENTS);

        if (list != null) {
            list.add(new ForgedEnchantment(FEnchantment, level));
        }else {
            list = new ArrayList<>();
            list.add(new ForgedEnchantment(FEnchantment, level));
        }

        stack.set(ModDataComponents.FORGED_ENCHANTMENTS, list);
    }

}
