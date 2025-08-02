package com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.FE;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.Craft.Modifiers.Modifiers;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/*
    ForgedEnchantment的存储，用于提供ID和效果
 */
public class FEnchantment {
    private final String id;
    private final String space;
    private final TagKey<Item> natureItems;
    private final TagKey<Item> supportItems;
    private final int maxLevel;
    private final Modifiers modifiers;

    public FEnchantment(String space, String id,
                        TagKey<Item> natureItems, TagKey<Item> supportItems,
                        int maxLevel, Modifiers effect) {
        this.space = space;
        this.id = id;
        this.natureItems = natureItems;
        this.supportItems = supportItems;
        this.maxLevel = maxLevel;
        this.modifiers = effect;
    }

    public String getId() {
        return id;
    }

    public String getSpace() {
        return space;
    }

    public TagKey<Item> getNatureItems() {
        return natureItems;
    }

    public TagKey<Item> getSupportItems() {
        return supportItems;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public Modifiers getModifiers() {
        return modifiers;
    }
}
