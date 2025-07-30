package com.ffanxxy.forge_enchantment.datagen;

import com.ffanxxy.forge_enchantment.ForgeEnchantment;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;

public class Generators {
//    注册事件
    public static void onGatherData(GatherDataEvent event){
        var gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        var existingFileHelper = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new ModLanguagesProvider.zh_CNProvider(packOutput));
        gen.addProvider(event.includeClient(), new ModLanguagesProvider.en_USProvider(packOutput));
//        添加数据包
        gen.addProvider(event.includeClient(), new ModDatapackProvider(packOutput,lookupProvider));
//        Tags
        ModBlockTagProvider blockTagsProvider = new ModBlockTagProvider(packOutput,lookupProvider,existingFileHelper);
        gen.addProvider(event.includeClient(),blockTagsProvider);

        gen.addProvider(event.includeClient(), new ModItemTagProvider(packOutput,lookupProvider, blockTagsProvider.contentsGetter(),existingFileHelper));
        gen.addProvider(event.includeClient(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
        gen.addProvider(event.includeClient(), new ModRecipeProvider(packOutput, lookupProvider));
    }

}
