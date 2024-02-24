package org.mr.cat.mods.indastrialmodformine.components.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.mr.cat.mods.indastrialmodformine.init.ModBlockEntities;

import java.util.stream.IntStream;

public class FireBoxFurnaceEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {

    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);

    public FireBoxFurnaceEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);

    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("fdfdf");
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return null;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.stacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.stacks = nonNullList;
    }

    public FireBoxFurnaceEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EXAMPLE_SCREEN_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return IntStream.range(0, this.getContainerSize()).toArray();
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @Nullable Direction direction) {
        return this.canPlaceItem(i, itemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction) {
        return true;
    }

    @Override
    public int getContainerSize() {
        return stacks.size();
    }
}
