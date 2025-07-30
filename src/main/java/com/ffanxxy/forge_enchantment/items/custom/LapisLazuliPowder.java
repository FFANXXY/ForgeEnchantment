package com.ffanxxy.forge_enchantment.items.custom;

import com.ffanxxy.forge_enchantment.component.ModDataComponents;
import com.ffanxxy.forge_enchantment.enchantments.ModEnchantments;
import com.ffanxxy.forge_enchantment.message.FEColor;
import com.ffanxxy.forge_enchantment.utils.ModEnchantmentHelper;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.List;

public class LapisLazuliPowder extends Item {
    public LapisLazuliPowder() {
        super(new Properties());
    }

    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return true;
    }

//    附魔能力
    @Override
    public int getEnchantmentValue(@NotNull ItemStack stack) {
        return 25;
    }

    // 当合成时
    @Override
    public void onCraftedBy(ItemStack stack, @NotNull Level level, Player player) {
        stack.set(ModDataComponents.DISCRETE,player.getRandom().nextFloat());
        super.onCraftedBy(stack, level, player);
    }


//    在物品栏时
    @Override
    public void inventoryTick(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
//        必须在 服务端并且在玩家的物品栏中
        if(level.isClientSide() || !(entity instanceof Player player)) return;
        if(stack.get(ModDataComponents.DISCRETE) == null) {
            stack.set(ModDataComponents.DISCRETE,player.getRandom().nextFloat());
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    //    物品使用事件
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        int infusion_level = itemStack.getEnchantmentLevel(ModEnchantmentHelper.get(level,ModEnchantments.INFUSION));
//        检测是否拥有附魔
        if(infusion_level > 0) {
            if(level.isClientSide()){
                Component msg = Component.translatable("forge_enchantment.success.laspis.enchantment_powder")
                        .withColor(FEColor.rgb("A7C7D1"));
                player.displayClientMessage(msg,true);
                player.playSound(SoundEvents.ENCHANTMENT_TABLE_USE);
            } else {
//                服务端给予玩家经验
                player.giveExperiencePoints(10 * infusion_level);
            }
//            减少物品
            itemStack.shrink(1);
            return InteractionResultHolder.consume(itemStack);
        }else {
//            如果没有，则使用失败
            if(level.isClientSide()) {
                Component msg = Component.translatable("forge_enchantment.failed.no_ehc.enchantment_powder")
                        .withColor(FEColor.LITTLE_PINK.get());
                player.displayClientMessage(msg,true);
                player.playSound(SoundEvents.GRASS_STEP);
            }
            return InteractionResultHolder.fail(itemStack);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal(" ").
                append(Component.translatable("item.lapis_lazuli_powder.lore1")
                        .withStyle(ChatFormatting.GRAY)));

        tooltipComponents.add(Component.literal(" ").
                append(Component.translatable("gui.forge_enchantment.down_shift_more")));
        //            显示属性 discrete
        MutableComponent discrete_info = Component.literal("  ").append(Component.translatable("forge_enchantment.item.discrete"));
        Float discrete_level = stack.get(ModDataComponents.DISCRETE);
        Component info_last;
//        "forge_enchantment.item.lapis_lazuli_powder.discrete_fictive_more"
        if (Screen.hasShiftDown()) {
            if(discrete_level == null) {
                info_last = discrete_info.append(Component.translatable("forge_enchantment.item.discrete.fictive_more")
                        .withColor(FEColor.UI_CYAN.get()));
            } else {
                info_last = discrete_info.append(Component.literal(discrete_level.toString()))
                        .withColor(FEColor.UI_CYAN.get());
            }
        } else {
            if(discrete_level == null) {
                info_last = discrete_info.append(Component.translatable("forge_enchantment.item.discrete.fictive")
                        .withColor(FEColor.UI_CYAN.get()));
            } else {
                info_last = discrete_info.append(Component.literal(String.format("%.2f", discrete_level))
                        .withColor(FEColor.UI_CYAN.get()));
            }
        }
        tooltipComponents.add(info_last);

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
