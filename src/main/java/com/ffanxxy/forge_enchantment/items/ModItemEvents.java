package com.ffanxxy.forge_enchantment.items;

import com.ffanxxy.forge_enchantment.items.events.ItemsEvents;
import net.neoforged.bus.api.IEventBus;

public class ModItemEvents {
    public static void addListenerWith(IEventBus bus){
        bus.addListener(ItemsEvents::onItemTooltip);
        bus.addListener(ItemsEvents::onUseItem);
    }
}
