package com.ffanxxy.forge_enchantment.ExtraEnchantments.Craft.Modifiers;

public class NeedTickModifier implements Modifier {
    public Operation getOperation() {
        return operation;
    }

    public int getValue() {
        return value;
    }

    public Boolean getByLevel() {
        return byLevel;
    }

    public enum Operation {
        ADD_TICK,
        SUB_TICK,
        ADD_PERCENT,
        SUB_PERCENT
    }

    private final Operation operation;
    private final int value;
    private final Boolean byLevel;

    private NeedTickModifier(Operation operation,int value, Boolean byLevel){
        this.byLevel = byLevel;
        this.operation = operation;
        this.value = value;
    }

    public static NeedTickModifier create(Operation operation, int value) {
        return new NeedTickModifier(operation,value,true);
    }
}
