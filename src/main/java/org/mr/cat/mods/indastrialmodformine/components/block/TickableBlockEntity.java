package org.mr.cat.mods.indastrialmodformine.components.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public interface TickableBlockEntity {
    public <T extends BlockEntity> void tick(Level level0, BlockPos pos0, BlockState state0, T blockEntity);

    static <T extends BlockEntity> BlockEntityTicker<T> getTickerHelper(Level level) {
        return level.isClientSide() ? null : (level0, pos0, state0, blockEntity) ->
                ((TickableBlockEntity)blockEntity).tick(level0, pos0, state0, blockEntity);
    }
}
