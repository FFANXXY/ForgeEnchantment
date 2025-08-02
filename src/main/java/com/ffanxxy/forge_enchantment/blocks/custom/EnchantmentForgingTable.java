package com.ffanxxy.forge_enchantment.blocks.custom;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.Contexts.CraftingContext.AfterCraftingContext;
import com.ffanxxy.forge_enchantment.ExtraEnchantments.Craft.EFT.EFCraft;
import com.ffanxxy.forge_enchantment.blockEntity.EnchantmentForgingTableBlockEntity;
import com.ffanxxy.forge_enchantment.utils.ModTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnchantmentForgingTable extends BaseEntityBlock {
    public EnchantmentForgingTable(Properties properties) {
        super(properties);
    }

    public static final MapCodec<EnchantmentForgingTable> CODEC = simpleCodec(EnchantmentForgingTable::new);

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @NotNull
    @Override
    public RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new EnchantmentForgingTableBlockEntity(blockPos, blockState);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var blockEntity = level.getBlockEntity(pos);

        if(blockEntity instanceof EnchantmentForgingTableBlockEntity efBE) {
            if(!(stack.is(ModTags.Items.WORDS_ITEMS))) {

                int size = efBE.getNonEmptyItems().size();
                if (size >= 6) {
                    // 失败
                    return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
                } else {
                    //成功
                    efBE.getItemHandler().insertItem(size, new ItemStack(stack.getItem(), 1), false);
                    stack.shrink(1);
//                    return ItemInteractionResult.sidedSuccess(false);
                    return ItemInteractionResult.SUCCESS;
                }

            } else {
                var context = AfterCraftingContext.create(level, player, efBE.getNonEmptyItems() , stack);
                EFCraft crafter = new EFCraft(context);
                crafter.deal();

                giveItemToPlayer(player, crafter.getResult().getResultItemStack());
                efBE.setItems(crafter.getResult().getLastitem());

                return ItemInteractionResult.SUCCESS;
            }
            } else {
            return ItemInteractionResult.FAIL;
        }
    }

    private void giveItemToPlayer(Player player, ItemStack itemStack) {
        if (player.getInventory().add(itemStack)) {
            return;
        } else {
            // 如果物品栏已满，物品掉落在地上
            player.drop(itemStack, false);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
//        var blockEntity = level.getBlockEntity(pos);
//
//        if(blockEntity instanceof EnchantmentForgingTableBlockEntity efBE) {
//            int size = efBE.getNonEmptyItems().size();
//            if (size >= 1) {
//                giveItemToPlayer(player, efBE.getItemHandler().getStackInSlot(size));
//                efBE.getItemHandler().insertItem(size, new ItemStack(Items.AIR),true);
//            }
//        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }
}
