package com.ffanxxy.forge_enchantment.blockEntity;

import com.ffanxxy.forge_enchantment.ForgeEnchantment;
import com.ffanxxy.forge_enchantment.blocks.ModBlocks;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.Type;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ForgeEnchantment.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EnchantmentForgingTableBlockEntity>>
        ENCHANTMENT_FORGING_TABLE_BLOCK_ENTITY;

    static {
        ENCHANTMENT_FORGING_TABLE_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("forge_enchantment_table",
                () -> BlockEntityType.Builder.of(EnchantmentForgingTableBlockEntity::new, ModBlocks.ENCHANTMENT_FORGING_TABLE.get()).build(DSL.remainderType()));
    }

    public static void register(IEventBus bus) {
        BLOCK_ENTITY_TYPES.register(bus);
    }
}
