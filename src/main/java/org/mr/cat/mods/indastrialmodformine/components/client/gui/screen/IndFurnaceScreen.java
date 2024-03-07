package org.mr.cat.mods.indastrialmodformine.components.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;
import org.mr.cat.mods.indastrialmodformine.components.blockentity.IndFurnaceEntity;
import org.mr.cat.mods.indastrialmodformine.components.client.gui.menu.IndFurnaceMenu;

public class IndFurnaceScreen extends AbstractContainerScreen<IndFurnaceMenu> {

    IndFurnaceEntity blockEntity;

    @Override
    protected void init() {
        super.init();

        var pos  = this.menu.getBlockEntity();

        if(this.minecraft == null) return;
        Level level = this.minecraft.level;
        if(level == null) return;

        BlockEntity be = level.getBlockEntity(pos);
        if(be instanceof IndFurnaceEntity blockEntity) {
            this.blockEntity = blockEntity;
        } else {
            return;
        }
    }

    public IndFurnaceScreen(IndFurnaceMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
    }

    private static final ResourceLocation TEXTURE = new ResourceLocation(IndastrialModForMine.MODID,
            "textures/gui/furnace.png");

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth,
                this.imageHeight);


        RenderSystem.disableBlend();
    }
}
