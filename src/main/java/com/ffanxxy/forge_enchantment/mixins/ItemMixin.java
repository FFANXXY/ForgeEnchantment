package com.ffanxxy.forge_enchantment.mixins;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 修改原版物品 Mixin for the Item
 */
@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "isEnchantable", at = @At("HEAD"), cancellable = true)
    private void onIsEnchantable(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        // 仅针对红石物品启用附魔
        if (stack.is(Items.REDSTONE) | stack.is(Items.PAPER)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getEnchantmentValue", at = @At("HEAD"), cancellable = true)
    private void onGetEnchantmentValue(CallbackInfoReturnable<Integer> cir) {
        Item self = (Item)(Object)this;
        if (self == Items.REDSTONE | self == Items.PAPER) {
            cir.setReturnValue(25);
        }
    }
}
