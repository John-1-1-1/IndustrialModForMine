package org.mr.cat.mods.indastrialmodformine.components.block;

import net.minecraft.util.StringRepresentable;

public enum WireSide implements StringRepresentable {
    SIDE("side"),
    NONE("none")
    ;


    private final String name;

    WireSide(String side) {
        name = side;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
