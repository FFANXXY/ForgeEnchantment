package com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments;

import com.ffanxxy.forge_enchantment.ExtraEnchantments.ForgeEnchantments.FE.FEnchantment;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class ForgedEnchantment {
    private final String id;
    private final int level;

    public ForgedEnchantment(String id,int level) {
        this.id = id;
        this.level = level;
    }
    public ForgedEnchantment(FEnchantment FEnchantment, int level) {
        this.id = FEnchantment.getId();
        this.level = level;
    }

    public static final Codec<ForgedEnchantment> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("id").forGetter(ForgedEnchantment::getId),
                    Codec.INT.fieldOf("level").forGetter(ForgedEnchantment::getLevel)
            ).apply(instance, ForgedEnchantment::new)
    );

    public String getId() {
        return this.id;
    }
    public int getLevel() {
        return this.level;
    }
}
