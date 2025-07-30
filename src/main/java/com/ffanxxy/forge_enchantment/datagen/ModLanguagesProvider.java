package com.ffanxxy.forge_enchantment.datagen;

import com.ffanxxy.forge_enchantment.ForgeEnchantment;
import com.ffanxxy.forge_enchantment.blocks.ModBlocks;
import com.ffanxxy.forge_enchantment.enchantments.ModEnchantments;
import com.ffanxxy.forge_enchantment.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.HashMap;
import java.util.Map;


public class ModLanguagesProvider {

    public record ActionTranslation(String action, String success, String failed) {}

    private static String getId(ResourceKey<Enchantment> enchantmentResourceKey) {
        return enchantmentResourceKey.location().toLanguageKey("enchantment");
    }

    public static String getId(DeferredItem<Item> itemHolder) {
        return itemHolder.getId().toLanguageKey("item");
    }

    public static String getId(DeferredBlock<Block> blockHolder) {
        return blockHolder.getId().toLanguageKey("block");
    }

    public static ActionTranslation getAction(String action) {
        return new ActionTranslation("action." + action,
                "action." + action + ".success",
                "action." + action + ".failed");
    }

    public static final Map<String, Map<String, String>> LANG = new HashMap<>();

    static {
        LANG.put(getId(ModItems.LAPIS_LAZULI_POWDER), Map.of(
                "en_us", "Lapis Lazuli Powder",
                "zh_cn", "青金石粉"
        ));
        LANG.put(getId(ModEnchantments.INFUSION), Map.of(
                "en_us", "Infusion",
                "zh_cn", "灌注"
        ));

        LANG.put("creativetab.forge_enchantment.all_items", Map.of(
                "en_us", "Forge Enchantment",
                "zh_cn", "铸魔"
        ));
        LANG.put("forge_enchantment.failed.no_ehc.enchantment_powder", Map.of(
                "en_us", "There doesn't seem to be anything special...",
                "zh_cn", "似乎没有什么特别的..."
        ));
        LANG.put("forge_enchantment.item.discrete", Map.of(
                "en_us", "Discrete: ",
                "zh_cn", "离散性质: "
        ));
        LANG.put("forge_enchantment.item.discrete.fictive", Map.of(
                "en_us", "FICTIVE ",
                "zh_cn", "虚构 "
        ));
        LANG.put("forge_enchantment.item.discrete.fictive_more", Map.of(
                "en_us", "Non-existent individuals  ",
                "zh_cn", "不存在的个体  "
        ));
        LANG.put("forge_enchantment.item.discrete.fixed", Map.of(
                "en_us", "FIXED ",
                "zh_cn", "固定 "
        ));
        LANG.put("forge_enchantment.item.discrete.fixed_more", Map.of(
                "en_us", "The world decides  ",
                "zh_cn", "世界所决定的  "
        ));
        LANG.put("forge_enchantment.success.laspis.enchantment_powder", Map.of(
                "en_us", "You seem to feel the Dissipating Magical Power.",
                "zh_cn", "你似乎感受到了逸散的魔力... "
        ));
        LANG.put("gui.forge_enchantment.down_shift_more", Map.of(
                "en_us", "Press §e[Shift]§r for more Information",
                "zh_cn", "按住 §e[Shift]§r 查看详细"
        ));
        LANG.put("item.lapis_lazuli_powder.lore1", Map.of(
                "en_us", "Tiny fragments of mystical abilities that seem to come from some intact individual?",
                "zh_cn", "一些神秘的微小碎片，似乎来自某个完整的个体？"
        ));
        LANG.put("item.redstone_powder.lore1", Map.of(
                "en_us", "Some powder with mystical powers.",
                "zh_cn", "一些具有神秘力量的粉末。"
        ));
        LANG.put(getId(ModItems.WORDS_BOOK), Map.of(
                "en_us", "Words Book",
                "zh_cn", "言之书"
        ));
        LANG.put("forge_enchantment.word_book_none", Map.of(
                "en_us", "An ancient mysterious book, seem to be useless?",
                "zh_cn", "古老的神秘图书，貌似没什么用？"
        ));
        LANG.put(getId(ModItems.WORDS_PAPER), Map.of(
                "en_us", "Words Paper",
                "zh_cn", "言之契"
        ));
        LANG.put(getId(ModItems.MAGIC_PAPER), Map.of(
                "en_us", "Magic Paper",
                "zh_cn", "注魔之纸"
        ));
        LANG.put(getId(ModBlocks.FORGED_ENCHANTMENT_TABLE), Map.of(
                "en_us", "Forged Enchantment Table",
                "zh_cn", "铸魔台"
        ));
        LANG.put(getAction("to_magic").action , Map.of(
                "en_us", "Magicalization",
                "zh_cn", "魔化"
        ));
        LANG.put(getAction("to_magic").success , Map.of(
                "en_us", "The item in front of me changed its form...",
                "zh_cn", "眼前的物品变幻了形态..."
        ));
        LANG.put(getAction("to_magic").failed , Map.of(
                "en_us", "Something seems to be missing?",
                "zh_cn", "似乎缺少一些什么？"
        ));
    }



    public static class en_USProvider extends LanguageProvider{

        public en_USProvider(PackOutput output) {
            super(output, ForgeEnchantment.MODID, "en_us");
        }

        @Override
        protected void addTranslations() {
            for (String key : LANG.keySet()) {
                this.add(key, LANG.get(key).get("en_us"));
            }
        }
    }
    public static class zh_CNProvider extends LanguageProvider{

        public zh_CNProvider(PackOutput output) {
            super(output, ForgeEnchantment.MODID, "zh_cn");
        }

        @Override
        protected void addTranslations() {
            for (String key : LANG.keySet()) {
                this.add(key, LANG.get(key).get("zh_cn"));
            }
        }
    }
}
