package com.ffanxxy.forge_enchantment.enchantments;

import com.ffanxxy.forge_enchantment.ForgeEnchantment;
import com.ffanxxy.forge_enchantment.enchantments.custom.InfusionEnchantment;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
//    注册
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ForgeEnchantment.MODID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> INFUSION =
            ENTITY_ENCHANTMENT_EFFECTS.register("infusion",() -> InfusionEnchantment.CODEC);

    public static void register(IEventBus bus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(bus);
    }
}
