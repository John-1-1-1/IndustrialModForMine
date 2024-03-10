
package org.mr.cat.mods.indastrialmodformine.components.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.RedstoneSide;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;

import java.util.Map;

    public class HeatPipe extends Block {
        public static final EnumProperty<WireSide> NORTH = EnumProperty.create("north", WireSide.class);
        public static final EnumProperty<WireSide> EAST = EnumProperty.create("east", WireSide.class);;
        public static final EnumProperty<WireSide> SOUTH = EnumProperty.create("south", WireSide.class);
        public static final EnumProperty<WireSide> WEST = EnumProperty.create("west", WireSide.class);
        public static final EnumProperty<WireSide> UP = EnumProperty.create("up", WireSide.class);
        public static final EnumProperty<WireSide> DOWN = EnumProperty.create("down", WireSide.class);

        public BlockState getStateForPlacement(BlockPlaceContext p_55513_) {
            return UpdateState( p_55513_.getLevel(), p_55513_.getClickedPos());
        }


        public BlockState updateShape(BlockState p_55598_, Direction p_55599_, BlockState p_55600_, LevelAccessor p_55601_, BlockPos p_55602_, BlockPos p_55603_) {
            return UpdateState(p_55601_, p_55602_);
        }

        public BlockState UpdateState(LevelAccessor levelAccessor, BlockPos pos){
            BlockGetter blockgetter = levelAccessor;
            BlockPos blockpos = pos;

            var defaultBlockState = this.defaultBlockState();

            var east = blockpos.east();
            var west = blockpos.west();
            var north = blockpos.north();
            var south = blockpos.south();
            var up = blockpos.relative(Direction.UP);
            var down = blockpos.relative(Direction.DOWN);

            return defaultBlockState.setValue(EAST, (blockgetter.getBlockState(east).getBlock() instanceof HeatPipe)? WireSide.SIDE:WireSide.NONE)
                    .setValue(WEST, (blockgetter.getBlockState(west).getBlock() instanceof HeatPipe)?WireSide.SIDE:WireSide.NONE)
                    .setValue(NORTH, (blockgetter.getBlockState(north).getBlock() instanceof HeatPipe)?WireSide.SIDE:WireSide.NONE)
                    .setValue(SOUTH, (blockgetter.getBlockState(south).getBlock() instanceof HeatPipe)?WireSide.SIDE:WireSide.NONE)
                    .setValue(UP, (blockgetter.getBlockState(up).getBlock() instanceof HeatPipe)?WireSide.SIDE:WireSide.NONE)
                    .setValue(DOWN, (blockgetter.getBlockState(down).getBlock() instanceof HeatPipe)?WireSide.SIDE:WireSide.NONE);
        }

        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) {
            p_48725_.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
        }

        public HeatPipe(Properties p_49795_) {
            super(p_49795_);
        }

        public VoxelShape getShape(BlockState p_57510_, BlockGetter p_57511_, BlockPos blockpos, CollisionContext p_57513_) {
            return Block.box(5.0D, 5.0D, 5.0D, 11.0D, 11.0D, 11.0D);
        }
    }
