package com.ffanxxy.forge_enchantment.ExtraEnchantments.Craft.Modifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Modifiers {
    public final List<Modifier> modifiers;

    public Modifiers(List<Modifier> modifiers) {
        this.modifiers = modifiers;
    }
    public Modifiers() {
        this.modifiers = new ArrayList<>();
    }

    public static Modifiers create(Modifier... modifiers) {
        return new Modifiers(Arrays.stream(modifiers).toList());
    }

    public static Modifiers create() {
        return new Modifiers();
    }
}
