package org.mr.cat.mods.indastrialmodformine.components.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;
import org.mr.cat.mods.indastrialmodformine.components.blockentity.FireBoxFurnaceEntity;
import org.mr.cat.mods.indastrialmodformine.components.client.gui.menu.FireBoxFurnaceMenu;

public class FireBoxFurnaceScreen  extends AbstractContainerScreen<FireBoxFurnaceMenu> {

    FireBoxFurnaceEntity blockEntity;

    public FireBoxFurnaceScreen(FireBoxFurnaceMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;


    }

    @Override
    protected void init() {
        super.init();
        var pos  = this.menu.getBlockEntity();

        if(this.minecraft == null) return;
        Level level = this.minecraft.level;
        if(level == null) return;

        BlockEntity be = level.getBlockEntity(pos);
        IndastrialModForMine.LOGGER.info(pos.toString());
        if(be instanceof FireBoxFurnaceEntity blockEntity) {
            this.blockEntity = blockEntity;
        } else {
            return;
        }
    }

    private static final ResourceLocation TEXTURE = new ResourceLocation(IndastrialModForMine.MODID,
            "textures/gui/example_fluid.png");


    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth,
                this.imageHeight);


        guiGraphics.fill(this.leftPos + 99, this.topPos + 14, this.leftPos + 117, this.topPos + 64, 0xFF404040);

        RenderSystem.disableBlend();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int p_283661_, int p_281248_, float p_281886_) {

        super.render(guiGraphics, p_283661_, p_281248_, p_281886_);




        IndastrialModForMine.LOGGER.info(String.valueOf(blockEntity.getLit()));
        guiGraphics.drawString(this.font,
                "Seconds Existed:" + String.valueOf(blockEntity.getLit()),
                this.leftPos + 8,
                this.topPos ,
                0xFF0000,
                false);
    }
}
