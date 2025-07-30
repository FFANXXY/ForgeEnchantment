package com.ffanxxy.forge_enchantment.blocks;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.ForgedEnchantment;
import com.ffanxxy.forge_enchantment.ForgeEnchantment;
import com.ffanxxy.forge_enchantment.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ForgeEnchantment.MODID);

    public static final DeferredBlock<Block> FORGED_ENCHANTMENT_TABLE = registerBlock(
            "forged_enchantment_table", () -> new Block(BlockBehaviour.Properties.of())
    );

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus bus){
        BLOCKS.register(bus);
    }
}
