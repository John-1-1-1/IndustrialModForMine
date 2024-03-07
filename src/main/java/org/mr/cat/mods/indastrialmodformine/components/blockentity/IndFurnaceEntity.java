package org.mr.cat.mods.indastrialmodformine.components.blockentity;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mr.cat.mods.indastrialmodformine.components.block.TickableBlockEntity;
import org.mr.cat.mods.indastrialmodformine.components.client.gui.menu.IndFurnaceMenu;
import org.mr.cat.mods.indastrialmodformine.init.ModBlockEntities;

public class IndFurnaceEntity extends RandomizableContainerBlockEntity implements TickableBlockEntity, MenuProvider {

    protected IndFurnaceEntity(BlockEntityType<?> p_155629_, BlockPos p_155630_, BlockState p_155631_) {
        super(p_155629_, p_155630_, p_155631_);
    }

    public IndFurnaceEntity(BlockPos position, BlockState state) {
        super(ModBlockEntities.IND_FURNACE_ENTITY.get(), position, state);
    }


    @Override
    protected NonNullList<ItemStack> getItems() {
        return null;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> p_59625_) {

    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("IndFurnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        return new IndFurnaceMenu(p_58627_, p_58628_,
                new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
    }

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    public <T extends BlockEntity> void tick(Level level0, BlockPos pos0, BlockState state0, T blockEntity) {

    }
}
