package org.mr.cat.mods.indastrialmodformine.components.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import org.mr.cat.mods.indastrialmodformine.components.blockentity.FireBoxFurnaceEntity;
import org.mr.cat.mods.indastrialmodformine.components.blockentity.IndFurnaceEntity;
import org.mr.cat.mods.indastrialmodformine.components.client.gui.menu.IndFurnaceMenu;
import org.mr.cat.mods.indastrialmodformine.components.client.gui.screen.IndFurnaceScreen;

public class IndFurnace  extends Block implements EntityBlock {
    public IndFurnace(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return null;
    }

    @Override
    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockstate, world, pos, entity, hand, hit);
        if (entity instanceof ServerPlayer player) {
            NetworkHooks.openScreen(player, new IndFurnaceEntity(pos, blockstate), pos);
        }
        return InteractionResult.SUCCESS;
    }
}
