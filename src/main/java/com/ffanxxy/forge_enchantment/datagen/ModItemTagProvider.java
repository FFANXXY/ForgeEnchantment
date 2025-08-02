package com.ffanxxy.forge_enchantment.datagen;

import com.ffanxxy.forge_enchantment.ForgeEnchantment;
import com.ffanxxy.forge_enchantment.items.ModItems;
import com.ffanxxy.forge_enchantment.utils.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {


    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags,
                              @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, ForgeEnchantment.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.ORE_POWDERS)
                .add(ModItems.LAPIS_LAZULI_POWDER.get())
                .add(Items.REDSTONE)
                .add(ModItems.MAGIC_PAPER.get());
        tag(ModTags.Items.WORDS_ITEMS)
                .add(ModItems.WORDS_PAPER.get())
                .add(ModItems.WORDS_BOOK.get());
    }
}
