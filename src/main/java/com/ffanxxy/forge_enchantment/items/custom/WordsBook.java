package com.ffanxxy.forge_enchantment.items.custom;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.ForgedEnchantment;
import com.ffanxxy.forge_enchantment.component.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;


import java.util.List;

public class WordsBook extends Item {
    public WordsBook() {
        super(new Properties().rarity(Rarity.EPIC).stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        List<ForgedEnchantment> enchantments = itemStack.get(ModDataComponents.FORGED_ENCHANTMENTS);

        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("forge_enchantment.word_book_none").withStyle(ChatFormatting.GRAY));
        List<ForgedEnchantment> forgedEnchantments = stack.get(ModDataComponents.FORGED_ENCHANTMENTS);

        if (forgedEnchantments != null) {
            forgedEnchantments.forEach(forgedEnchantment ->
                    tooltipComponents.add(
                            Component.literal(forgedEnchantment.getId() + " " + forgedEnchantment.getLevel())
                    ));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
