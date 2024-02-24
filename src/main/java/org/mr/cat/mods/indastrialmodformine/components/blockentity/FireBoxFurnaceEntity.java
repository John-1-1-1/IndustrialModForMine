package org.mr.cat.mods.indastrialmodformine.components.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.mr.cat.mods.indastrialmodformine.init.ModBlockEntities;

public class FireBoxFurnaceEntity extends BlockEntity {

    public FireBoxFurnaceEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }

    public FireBoxFurnaceEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EXAMPLE_SCREEN_BLOCK_ENTITY.get(), pos, state);
    }
}
