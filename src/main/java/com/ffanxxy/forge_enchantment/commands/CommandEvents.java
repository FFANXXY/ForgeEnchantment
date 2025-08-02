package com.ffanxxy.forge_enchantment.commands;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.FE.FEnchantment;
import com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.FEHelper;
import com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.ForgedEnchantments;
import com.ffanxxy.forge_enchantment.items.ModItems;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.List;

public class CommandEvents {
    public static void addListenerWith(IEventBus bus) {
        bus.addListener(CommandEvents::onRegisterCommandEvent);
    }

    private static final List<String> enchantments = ForgedEnchantments.FEnchantments.stream().map(FEnchantment::getId).toList();

    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("forgenchantment")
                        .then(Commands.argument("entity",EntityArgument.entity())
                                .then(Commands.argument("enchantment", StringArgumentType.word())
                                        .suggests((context, builder) -> {
                                            return SharedSuggestionProvider.suggest(enchantments, builder);
                                        })
                                        .executes(CommandEvents::EnchantHandle)))

        );
    }

    public static int EnchantHandle(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Entity entity = EntityArgument.getEntity(context, "entity");
        FEnchantment enchantment = ForgedEnchantments.getFEformId(StringArgumentType.getString(context, "enchantment"));
        ItemStack itemStack = entity.getWeaponItem();
        if(itemStack == null) return 0;

        if (itemStack.is(ModItems.WORDS_BOOK)){
            FEHelper.enchant(itemStack,enchantment, 1);
        }

        return 1;
    }
}
