package com.ffanxxy.forge_enchantment.ExtraEnchantments.Recipe;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.utils.Exception.Crafting.NotSameSlotsExpection;
import com.ffanxxy.forge_enchantment.ExtraEnchantments.utils.Results.CraftResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.*;
import java.util.stream.Collectors;

public final class EFTRecipe {
    private final List<ItemStack> items;
    private final ItemStack result;
    private final int need_tick;

    public EFTRecipe(ItemStack result,int need_tick, ItemStack... stacks) {
        this.result = result;
        this.need_tick = need_tick;
        items = Arrays.stream(stacks).toList();
    }

    public CraftResult canCraft(List<ItemStack> raws) {
        if(raws.size() != items.size()) {
            return new CraftResult(false,new NotSameSlotsExpection("The number of items does not match the recipe"));
        }
        var res = matches(items, raws);
        if (res.suc()) {
            return new CraftResult(true, res.last());
        }
        return new CraftResult(false);
    }

    public static MatchResult matches(List<ItemStack> rec, List<ItemStack> raw) {
        List<ItemStack> r = raw;
        Map<Item, Integer> required = rec.stream()
                .filter(stack -> !stack.isEmpty())
                .collect(Collectors.toMap(
                        ItemStack::getItem,
                        ItemStack::getCount,
                        Integer::sum
                ));

        for(ItemStack stack : r) {
            ItemStack model = stack.copy();
           int need_count = required.getOrDefault(stack.getItem(),-1);
           if (need_count == -1 || need_count > stack.getCount()) {
               return new MatchResult(false,raw);
           }
           stack.setCount(stack.getCount() - need_count);
           r.set(r.indexOf(model),stack);
        }
        return new MatchResult(true, r);
    }

    public int getNeedtick() {
        return need_tick;
    }

    private record MatchResult(Boolean suc, List<ItemStack> last) {}

    public ItemStack getResult() {
        return this.result;
    }
}
