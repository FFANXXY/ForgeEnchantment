package com.ffanxxy.forge_enchantment.items.events;

import com.ffanxxy.forge_enchantment.component.ModDataComponents;
import com.ffanxxy.forge_enchantment.enchantments.ModEnchantments;
import com.ffanxxy.forge_enchantment.items.ModItems;
import com.ffanxxy.forge_enchantment.message.FEColor;
import com.ffanxxy.forge_enchantment.utils.ModEnchantmentHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.redstone.Redstone;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemsEvents {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event){
        ItemStack itemStack = event.getItemStack();
        if(itemStack.is(Items.REDSTONE)) {
            setRedstoneTooltip(event);
        }
    }
    private static void setRedstoneTooltip(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        Float discrete = stack.get(ModDataComponents.DISCRETE);
        var tooltipComponents = event.getToolTip();
//        临时List
        List<Component> addComponents = new ArrayList<>();

        addComponents.addFirst(Component.literal(" ").append(Component.translatable("item.redstone_powder.lore1").withStyle(ChatFormatting.GRAY)));
        addComponents.addFirst(Component.literal(" ").append(Component.translatable("gui.forge_enchantment.down_shift_more")));
        //            显示属性 discrete
        MutableComponent discrete_info = Component.literal("  ").append(Component.translatable("forge_enchantment.item.discrete"));
        Float discrete_level = stack.get(ModDataComponents.DISCRETE);
        Component info_last;

        if (Screen.hasShiftDown()) {
            if(discrete_level == null) {
                info_last = discrete_info.append(Component.translatable("forge_enchantment.item.discrete.fixed_more")
                        .withColor(FEColor.UI_CYAN.get()));
            } else {
                info_last = discrete_info.append(Component.literal(discrete_level.toString()))
                        .withColor(FEColor.UI_CYAN.get());
            }
        } else {
            if(discrete_level == null) {
                info_last = discrete_info.append(Component.translatable("forge_enchantment.item.discrete.fixed")
                        .withColor(FEColor.UI_CYAN.get()));              
            } else {
                info_last = discrete_info.append(Component.literal(String.format("%.2f", discrete_level))
                        .withColor(FEColor.UI_CYAN.get()));
            }

        }
        addComponents.addFirst(info_last);
        for (Component component : addComponents) {
            tooltipComponents.add(1,component);
        }
    }

    @SubscribeEvent
    public static void onUseItem(PlayerInteractEvent.RightClickItem event) {
        ItemStack itemStack = event.getItemStack();
        Player player = event.getEntity();
        Level level = event.getLevel();
        ItemStackResult nextStackResult = toMagicItem(itemStack, level);

        switch (nextStackResult.code) {
            case -1:
                return;
            case -2:
                if(level.isClientSide()) {
                    player.displayClientMessage(
                            Component.translatable("action.to_magic.failed"),true
                    );
                }
            case 1:
                if(level.isClientSide()) {
                    player.displayClientMessage(
                            Component.translatable("action.to_magic.success"),true
                    );
                } else {
                    player.setItemInHand(event.getHand(), nextStackResult.itemStack);
                }

        }

        return;
    }

    public record ItemStackResult(ItemStack itemStack, int code) {}
    public static Map<Item,Item> MagicItemMap = new HashMap<>();

    static {
        MagicItemMap.put(Items.REDSTONE, ModItems.MAGIC_REDSTONE_DUST.get());
        MagicItemMap.put(Items.PAPER, ModItems.MAGIC_PAPER.get());
    }

    /**
     *
     * @param itemStack
     * @param level
     * @return  <p>-1 找不到物品 </p>
     *          <p>-2 没有附魔 </p>
     *          <p>1 成功 </p>
     */
    public static ItemStackResult toMagicItem(ItemStack itemStack, Level level) {
        if(!MagicItemMap.containsKey(itemStack.getItem())) {
            return new ItemStackResult(itemStack, -1);
        } else if (ModEnchantmentHelper.getLevel(level, ModEnchantments.INFUSION, itemStack) == 0) {
            return new ItemStackResult(itemStack, -2);
        }
        return new ItemStackResult(new ItemStack(MagicItemMap.get(itemStack.getItem()),itemStack.getCount()),1);
    }
}
