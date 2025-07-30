package com.ffanxxy.forge_enchantment.message;

/**
 * @author FFANXXY
 * 存储所有的颜色，包括生成颜色的方法
 * store all the mod colors , including the methons to spawn the custom color
 */
public enum FEColor {
    LITTLE_PINK(16238583),
    UI_CYAN(15663100);

    private final int color;
    FEColor(int color) {
        this.color = color;
    }
    public int get() {return this.color;}
    /**
     * 通过RGB的16进制码生成颜色
     * @param color rgb码
     * @return 对应的int
     */
    public static int rgb(String color) {return Integer.parseInt(color,16);}
}
