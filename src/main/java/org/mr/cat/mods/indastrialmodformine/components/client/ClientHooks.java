package org.mr.cat.mods.indastrialmodformine.components.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import org.mr.cat.mods.indastrialmodformine.components.screen.FireBoxScreen;

public class ClientHooks {

    // Открывание окна
    public static void openExampleBlockScreen(BlockPos pos) {
        //Minecraft.getInstance().setScreen(new FireBoxScreen(pos));
    }
}
