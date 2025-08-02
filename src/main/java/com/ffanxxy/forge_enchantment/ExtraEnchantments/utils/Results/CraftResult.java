package com.ffanxxy.forge_enchantment.ExtraEnchantments.utils.Results;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public class CraftResult {
    private final Boolean isSuccess;
    private final Exception exception;

    private final List<ItemStack> last;

    public CraftResult(Boolean success) {
        this.isSuccess = success;
        this.exception = null;
        this.last = null;
    }
    public CraftResult(Boolean success, Exception exception) {
        this.isSuccess = success;
        this.exception = exception;
        this.last = null;
    }
    public CraftResult(Boolean success, List<ItemStack> last) {
        this.isSuccess = success;
        this.exception = null;
        this.last = last;
    }


    public Boolean getSuccess() {
        return isSuccess;
    }

    public Exception getException() {
        return exception;
    }

    public List<ItemStack> getLast() {
        return last;
    }
}
