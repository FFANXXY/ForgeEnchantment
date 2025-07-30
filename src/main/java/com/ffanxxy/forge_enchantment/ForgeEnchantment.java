package com.ffanxxy.forge_enchantment;

import com.ffanxxy.forge_enchantment.blockEntity.ModBlockEntityTypes;
import com.ffanxxy.forge_enchantment.blocks.ModBlocks;
import com.ffanxxy.forge_enchantment.commands.CommandEvents;
import com.ffanxxy.forge_enchantment.component.ModDataComponents;
import com.ffanxxy.forge_enchantment.datagen.Generators;
import com.ffanxxy.forge_enchantment.enchantments.ModEnchantmentEffects;
import com.ffanxxy.forge_enchantment.items.ModItemEvents;
import com.ffanxxy.forge_enchantment.items.ModCreativeModeTabs;
import com.ffanxxy.forge_enchantment.items.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;


@Mod(ForgeEnchantment.MODID)
public class ForgeEnchantment {

    public static final String MODID = "forge_enchantment";

    private static final Logger LOGGER = LogUtils.getLogger();

    public ForgeEnchantment(IEventBus modEventBus, ModContainer modContainer) {
//        注册所有物品 Register all the items
        ModItems.register(modEventBus);
//        注册所有附魔效果 Register all the EnchantmentsEffects
        ModEnchantmentEffects.register(modEventBus);
//        注册创造模式物品栏 Register CreativeModeTabs
        ModCreativeModeTabs.register(modEventBus);
//        注册数据组件 Register Data Components
        ModDataComponents.register(modEventBus);
//        注册方块实体
        ModBlockEntityTypes.register(modEventBus);
//        注册方块 Register Blocks
        ModBlocks.register(modEventBus);

//        数据生成 Datagen
        modEventBus.addListener(Generators:: onGatherData);

        IEventBus forgeEventBus = NeoForge.EVENT_BUS;
//        事件 Events
        ModItemEvents.addListenerWith(forgeEventBus);
        CommandEvents.addListenerWith(forgeEventBus);
    }
}

