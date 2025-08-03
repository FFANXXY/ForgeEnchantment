package com.ffanxxy.forge_enchantment.ExtraEnchantments.Recipe;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.utils.Exception.Crafting.NotSameSlotsExpection;
import com.ffanxxy.forge_enchantment.ExtraEnchantments.utils.Results.CraftResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.*;
import java.util.stream.Collectors;

public final class EFTRecipe {
    // 千万 一定 必须 肯定 不能忘 Must 不能返回ItemStack
    private final List<ItemStack> items;
    private final ItemStack result;
    private final int need_tick;

    public EFTRecipe(ItemStack result,int need_tick, ItemStack... stacks) {
        this.result = result;
        this.need_tick = need_tick;
        items = Arrays.stream(stacks).toList();
    }

    public EFTRecipe() {
        this.result = ItemStack.EMPTY;
        this.items = new ArrayList<>();
        this.need_tick = 0;
    }

    public CraftResult canCraft(List<ItemStack> raws) {
        if(raws.size() != items.size()) {
            return new CraftResult(false,new NotSameSlotsExpection("The number of items does not match the recipe"));
        }
        MatchResult res = NoOrderMatches(items, raws);
        if (res.suc()) {
            return new CraftResult(true, res.last());
        }
        return new CraftResult(false);
    }

    public static MatchResult NoOrderMatches(List<ItemStack> rec, List<ItemStack> raw) {
        Map<Item,Integer> RawsCollect = raw.stream().collect(Collectors.groupingBy(ItemStack::getItem, Collectors.summingInt(ItemStack::getCount)));
        Map<Item,Integer> RecipeItemCollect = rec.stream().collect(Collectors.groupingBy(ItemStack::getItem, Collectors.summingInt(ItemStack::getCount)));
        List<ItemStack> lastItems = new ArrayList<>();

        for(Item item : RawsCollect.keySet()) {
            int hasCount = RecipeItemCollect.get(item);
            int needCount = RecipeItemCollect.getOrDefault(item,-1);
            if (needCount == -1 || hasCount < needCount) return new MatchResult(false,raw);

            if (hasCount == needCount) {
                lastItems.add(ItemStack.EMPTY);
            };
            lastItems.add(new ItemStack(item, hasCount - needCount));
        }

        while(lastItems.size() < raw.size()) {
            lastItems.add(ItemStack.EMPTY);
        }

        return new MatchResult(true, lastItems);
    }

    public int getNeedtick() {
        return need_tick;
    }

    private record MatchResult(Boolean suc, List<ItemStack> last) {}

    public ItemStack getResult() {
        return result.copy();
    }
}
