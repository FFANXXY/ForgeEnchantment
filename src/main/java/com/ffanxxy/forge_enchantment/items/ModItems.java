package com.ffanxxy.forge_enchantment.items;

import com.ffanxxy.forge_enchantment.ForgeEnchantment;
import com.ffanxxy.forge_enchantment.items.custom.LapisLazuliPowder;
import com.ffanxxy.forge_enchantment.items.custom.WordsBook;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(ForgeEnchantment.MODID);

    public static final DeferredItem<Item> LAPIS_LAZULI_POWDER =
            ITEMS.register("powder_of_lapis_lazuli", LapisLazuliPowder::new);

    public static final DeferredItem<Item> WORDS_BOOK =
            ITEMS.register("words_book", WordsBook::new);

    public static final DeferredItem<Item> WORDS_PAPER =
            ITEMS.register("words_paper", WordsBook::new);

    public static final DeferredItem<Item> MAGIC_PAPER =
            ITEMS.register("magic_paper", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MAGIC_REDSTONE_DUST =
            ITEMS.register("magic_redstone_dust", () -> new Item(new Item.Properties()));


    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
