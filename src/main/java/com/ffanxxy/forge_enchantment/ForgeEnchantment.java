package com.ffanxxy.forge_enchantment;

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
//        注册所有物品
        ModItems.register(modEventBus);
        ModEnchantmentEffects.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModDataComponents.register(modEventBus);
        ModBlocks.register(modEventBus);

//        数据生成
        modEventBus.addListener(Generators:: onGatherData);

        IEventBus forgeEventBus = NeoForge.EVENT_BUS;
//        事件
        ModItemEvents.addListenerWith(forgeEventBus);
        CommandEvents.addListenerWith(forgeEventBus);
    }
}

