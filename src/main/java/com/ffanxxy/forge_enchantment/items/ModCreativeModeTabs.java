package com.ffanxxy.forge_enchantment.items;

import com.ffanxxy.forge_enchantment.ForgeEnchantment;
import com.ffanxxy.forge_enchantment.blocks.ModBlocks;
import com.ffanxxy.forge_enchantment.enchantments.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ForgeEnchantment.MODID);

    public static final Supplier<CreativeModeTab> ALL_ITEMS = CREATIVE_MODE_TAB.register("fe_all_items_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.forge_enchantment.all_items"))
                    .icon(() -> new ItemStack(ModItems.LAPIS_LAZULI_POWDER.get()))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.LAPIS_LAZULI_POWDER);
                        output.accept(Items.REDSTONE);
                        output.accept(ModItems.WORDS_BOOK);
                        output.accept(ModItems.WORDS_PAPER);
                        output.accept(ModItems.MAGIC_PAPER);
                        output.accept(ModBlocks.ENCHANTMENT_FORGING_TABLE);

                        Holder<Enchantment> infusionHolder =
                                itemDisplayParameters.holders().lookup(Registries.ENCHANTMENT).get().getOrThrow(ModEnchantments.INFUSION);
                        for (int i = 1; i <= 3; i++) {
                            // 创建附魔书 ItemStack
                            ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
                            enchantedBook.enchant(infusionHolder,i);
                            output.accept(enchantedBook);
                        }
                    }))
                    .build());

    public static void register(IEventBus bus){
        CREATIVE_MODE_TAB.register(bus);
    }
}
