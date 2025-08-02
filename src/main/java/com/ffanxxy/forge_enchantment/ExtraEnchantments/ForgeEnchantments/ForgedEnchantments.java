package com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.FE.FEnchantment;
import com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.custom.LessTimeFE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ForgedEnchantments {
    public static final List<FEnchantment> FEnchantments = new ArrayList<>();


    public static FEnchantment LESS_TIME = register(LessTimeFE::new);

    static {
        FEnchantments.add(LESS_TIME);
    }

    public static FEnchantment register(Supplier<? extends FEnchantment> sup) {
        return new FEnchantment(sup.get().getSpace() , sup.get().getId(), sup.get().getNatureItems(), sup.get().getSupportItems(), sup.get().getMaxLevel(), sup.get().getModifiers());
    }

    public static FEnchantment getFEformId(String id) {
        return FEnchantments.stream().filter(fEnchantment -> {return fEnchantment.getId().equals(id);})
                .toList().getFirst();
    }
}
