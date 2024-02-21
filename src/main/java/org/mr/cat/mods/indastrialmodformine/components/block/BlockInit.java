package org.mr.cat.mods.indastrialmodformine.components.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;

public class BlockInit {
    // Отложенная регистрация
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IndastrialModForMine.MODID);

    // Создание блока
    public static final RegistryObject<Block> COKE_FURNACE_BLOCK =
            BLOCKS.register("coke_furnace_block", () -> new Block(
                    BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));

}
