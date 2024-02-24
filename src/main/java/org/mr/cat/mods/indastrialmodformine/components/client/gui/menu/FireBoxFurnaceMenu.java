package org.mr.cat.mods.indastrialmodformine.components.client.gui.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;
import org.mr.cat.mods.indastrialmodformine.components.blockentity.FireBoxFurnaceEntity;
import org.mr.cat.mods.indastrialmodformine.init.ModMenus;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FireBoxFurnaceMenu  extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {

    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private final Container container;
    private IItemHandler internal;

    // Client Constructor
    public FireBoxFurnaceMenu(int containerId, Inventory playerInv, FriendlyByteBuf additionalData) {
        this(containerId, playerInv, playerInv.player.level().getBlockEntity(additionalData.readBlockPos()));
    }

    // Server Constructor
    public FireBoxFurnaceMenu(int containerId, Inventory playerInv, BlockEntity blockEntity) {
        super(ModMenus.FIRE_BOX_FURNACE_MENU.get(), containerId);


        this.internal = new ItemStackHandler(1);
        this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 22, 22) {
            private final int slot = 0;
        }));


        int $$7;
        int $$6;

        for($$7 = 0; $$7 < 3; ++$$7) {
            for($$6 = 0; $$6 < 9; ++$$6) {
                this.addSlot(new Slot(playerInv, $$6 + $$7 * 9 + 9, 8 + $$6 * 18, 84 + $$7 * 18));
            }
        }

        for($$7 = 0; $$7 < 9; ++$$7) {
            this.addSlot(new Slot(playerInv, $$7, 8 + $$7 * 18, 142));
        }
        container = null;
    }

    @Override
    public Map<Integer, Slot> get() {
        return customSlots;
    }

    // Перемещение элементов. Взято с печи
    public ItemStack quickMoveStack(Player p_38986_, int p_38987_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(p_38987_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (p_38987_ == 2) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (p_38987_ != 1 && p_38987_ != 0) {
                 if (p_38987_ >= 3 && p_38987_ < 30) {
                    if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (p_38987_ >= 30 && p_38987_ < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(p_38986_, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
