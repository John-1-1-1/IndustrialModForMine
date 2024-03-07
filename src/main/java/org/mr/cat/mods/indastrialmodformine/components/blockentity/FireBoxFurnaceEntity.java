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
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
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

public class FireBoxFurnaceEntity extends RandomizableContainerBlockEntity implements WorldlyContainer, TickableBlockEntity, MenuProvider {

    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
    private final LazyOptional<? extends IItemHandler>[]
            handlers = SidedInvWrapper.create(
            this, Direction.values());

    public FireBoxFurnaceEntity(BlockPos position, BlockState state) {
        super(ModBlockEntities.IND_FURNACE_ENTITY.get(), position, state);
    }

    int litTime;

    int maxLit = 200;
    int litProgress;


    // загрузка при запуске
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

    // сохранение параметров
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
    public @NotNull CompoundTag getUpdateTag() {
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
    public @NotNull Component getDefaultName() {
        return Component.literal("FireBox");
    }

    @Override
    public @NotNull AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new FireBoxFurnaceMenu(id, inventory,
                new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(this.worldPosition));
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("FireBox");
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.stacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> stacks) {
        this.stacks = stacks;
    }

    @Override
    public int @NotNull [] getSlotsForFace(Direction side) {
        return IntStream.range(0, this.getContainerSize()).toArray();
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, @NotNull ItemStack stack, @javax.annotation.Nullable Direction direction) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, @NotNull ItemStack stack, @NotNull Direction direction) {
        return true;
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER)
            return handlers[facing.ordinal()].cast();
        return super.getCapability(capability, facing);
    }

    public boolean isLit() { return this.litTime > 0;}


    protected int getBurnDuration(ItemStack p_58343_) {
        if (p_58343_.isEmpty()) {
            return 0;
        } else {
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

    public int getLitProgress(){
        return litTime * 13 / maxLit;
    }


    private void ChangeLitProgress(){
        litProgress = Mth.clamp(litProgress + (this.isLit() ? 1 : -1), 0, 1000);
    }

    @Override
    public <T extends BlockEntity> void tick(Level level0, BlockPos pos0, BlockState state0, T blockEntity) {
        ItemStack itemstack = this.getItem(0);

        ChangeLitProgress();

        level0.setBlock(pos0, state0.setValue(FireBoxFurnace.LIT, this.isLit()),3);
        setChanged();
        this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);

        if (this.isLit()) {
            --this.litTime;
            return;
        }

        if (!itemstack.isEmpty() && this.getBurnDuration(itemstack) > 0) {
            this.litTime = this.getBurnDuration(itemstack);
            maxLit = this.litTime;
            itemstack.shrink(1);
        }
    }
}
