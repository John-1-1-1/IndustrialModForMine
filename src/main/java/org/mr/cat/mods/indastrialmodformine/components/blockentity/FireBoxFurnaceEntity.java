package org.mr.cat.mods.indastrialmodformine.components.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class FireBoxFurnaceEntity extends BlockEntity {

    public FireBoxFurnaceEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }

    public FireBoxFurnaceEntity(BlockPos pos, BlockState state) {
        super(EntityInit.EXAMPLE_SCREEN_BLOCK_ENTITY.get(), pos, state);

    }
}
