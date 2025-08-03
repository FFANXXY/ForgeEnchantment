package com.ffanxxy.forge_enchantment.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentForgingTableBlockEntity extends BlockEntity {

    public EnchantmentForgingTableBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityTypes.ENCHANTMENT_FORGING_TABLE_BLOCK_ENTITY.get(), pos, blockState);
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(6) { // 6个槽位
        @Override
        protected void onContentsChanged(int slot) {
            setChanged(); // 标记数据需要保存
        }
    };

    // 获取物品处理器
    public IItemHandler getItemHandler() {
        return itemHandler;
    }

    public ItemStack getItemFromSlot(int slot) {
        if(slot + 1 > itemHandler.getSlots() || slot < 0) {
            return ItemStack.EMPTY;
        }
        return itemHandler.getStackInSlot(slot);
    }

    // 获取非空物品列表（过滤空槽位）
    public List<ItemStack> getNonEmptyItems() {
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                items.add(stack.copy());
            }
        }
        return items;
    }

    public void setItems(List<ItemStack> items) {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            itemHandler.setStackInSlot(i, items.get(i));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }


    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("inventory")) {
            itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        }
    }
}
