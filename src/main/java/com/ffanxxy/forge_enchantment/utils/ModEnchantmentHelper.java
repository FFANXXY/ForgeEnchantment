package com.ffanxxy.forge_enchantment.utils;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

public class ModEnchantmentHelper {
    public static Holder<Enchantment> get(Level level, ResourceKey<Enchantment> key) {
        // 1. 获取注册表访问器
        RegistryAccess registryAccess = level.registryAccess();
        // 2. 获取附魔注册表
        Registry<Enchantment> enchantRegistry = registryAccess.registryOrThrow(Registries.ENCHANTMENT);

        // 3. 通过 ResourceKey 获取 Holder<Enchantment>
        return enchantRegistry.getHolderOrThrow(key);
    }

    public static int getLevel(Level level, ResourceKey<Enchantment> key, ItemStack itemStack) {
        return itemStack.getEnchantmentLevel(get(level,key));
    }
}
