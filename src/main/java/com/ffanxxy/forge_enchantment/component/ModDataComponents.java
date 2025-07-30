package com.ffanxxy.forge_enchantment.component;

import com.ffanxxy.forge_enchantment.ForgeEnchantment;
import com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.ForgedEnchantment;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE,ForgeEnchantment.MODID);

    public static final DeferredHolder<DataComponentType<?> , DataComponentType<Float>> DISCRETE =
            register("discrete",builder -> builder.persistent(Codec.FLOAT));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ForgedEnchantment>>> FORGED_ENCHANTMENTS =
        register("forged_enchantments",builder -> builder.persistent(Codec.list(ForgedEnchantment.CODEC)));


    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                          UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name,() -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus bus) {
        DATA_COMPONENT_TYPES.register(bus);
    }
}
