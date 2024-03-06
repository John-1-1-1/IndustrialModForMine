package org.mr.cat.mods.indastrialmodformine.components.blockentity;

import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;
import org.mr.cat.mods.indastrialmodformine.components.block.FireBoxFurnace;
import org.mr.cat.mods.indastrialmodformine.components.block.TickableBlockEntity;
import org.mr.cat.mods.indastrialmodformine.components.client.gui.menu.FireBoxFurnaceMenu;
import org.mr.cat.mods.indastrialmodformine.init.ModBlockEntities;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class FireBoxFurnaceEntity extends RandomizableContainerBlockEntity implements WorldlyContainer, TickableBlockEntity {

    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[]
            handlers = SidedInvWrapper.create(
            this, Direction.values());

    public FireBoxFurnaceEntity(BlockPos position, BlockState state) {
        super(ModBlockEntities.EXAMPLE_SCREEN_BLOCK_ENTITY.get(), position, state);
    }

    int litTime;

    int litProgress;

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);

        var tutorialmodData = nbt.getCompound(IndastrialModForMine.MODID);

        this.litTime = tutorialmodData.getInt("LitTime");
        this.litProgress = tutorialmodData.getInt("LitProgress");

        if (!this.tryLoadLootTable(nbt))
            this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.stacks);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        var tutorialmodData = new CompoundTag();

        tutorialmodData.putInt("LitTime", this.litTime);
        tutorialmodData.putInt("LitProgress", this.litProgress);

        compound.put(IndastrialModForMine.MODID, tutorialmodData);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.stacks);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    @Override
    public int getContainerSize() {
        return stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.stacks)
            if (!itemstack.isEmpty())
                return false;
        return true;
    }

    @Override
    public Component getDefaultName() {
        return Component.literal("fdfdf");
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new FireBoxFurnaceMenu(id, inventory,
                new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Fdfdf");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.stacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return IntStream.range(0, this.getContainerSize()).toArray();
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @javax.annotation.Nullable Direction direction) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
            return handlers[facing.ordinal()].cast();
        return super.getCapability(capability, facing);
    }

    public boolean isLit() { return this.litTime > 0;}


    protected int getBurnDuration(ItemStack p_58343_) {
        if (p_58343_.isEmpty()) {
            return 0;
        } else {
            Item item = p_58343_.getItem();
            // TODO : Создать свой тип рецептов  или юхать печкин
            return ForgeHooks.getBurnTime(p_58343_, RecipeType.BLASTING);
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        for (LazyOptional<? extends IItemHandler> handler : handlers)
            handler.invalidate();
    }

    public int getPxLit(){
        return litProgress / 20;
    }

    int maxLit = 200;

    public int getLitProgress(){
        return litTime * 13 / maxLit;
    }

    @Override
    public <T extends BlockEntity> void tick(Level level0, BlockPos pos0, BlockState state0, T blockEntity) {
        ItemStack itemstack = (ItemStack)this.getItem(0);
        var e =(BlockState)state0.setValue(FireBoxFurnace.LIT, this.isLit());

        level0.setBlock(pos0, e,3);

        if (this.isLit()){

            ++litProgress;

            if (litProgress > 1000){
                litProgress = 1000;
            }

            --this.litTime;

            setChanged();
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            return;
        }
        else{
            --litProgress;
            if (litProgress < 0){
                litProgress = 0;
            }
            setChanged();
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }

        if (!itemstack.isEmpty()) {
            this.litTime = this.getBurnDuration(itemstack);
            maxLit = this.litTime;
            itemstack.shrink(1);
        }
    }
}
