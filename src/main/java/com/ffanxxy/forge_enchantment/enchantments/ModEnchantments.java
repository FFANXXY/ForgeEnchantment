package com.ffanxxy.forge_enchantment.enchantments;


import com.ffanxxy.forge_enchantment.ForgeEnchantment;
import com.ffanxxy.forge_enchantment.enchantments.custom.InfusionEnchantment;
import com.ffanxxy.forge_enchantment.utils.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.Enchantments;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> INFUSION = ResourceKey.create(Registries.ENCHANTMENT ,
            ResourceLocation.fromNamespaceAndPath(ForgeEnchantment.MODID,"infusion"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, INFUSION, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ModTags.Items.ORE_POWDERS),
                items.getOrThrow(ModTags.Items.ORE_POWDERS),
                15,
                3,
                Enchantment.dynamicCost(5,3),
                Enchantment.dynamicCost(7,5),
                2,
                EquipmentSlotGroup.ANY))
//                    .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.BOW_EXCLUSIVE))
//                附魔效果
                        .withEffect(EnchantmentEffectComponents.POST_ATTACK , EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM, new InfusionEnchantment())
                        .withCustomName(mutableComponent -> Component.translatable("enchantment.forge_enchantment.infusion"))
        );
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key,
                                                Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }

}
