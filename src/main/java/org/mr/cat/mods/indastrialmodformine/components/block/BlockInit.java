package org.mr.cat.mods.indastrialmodformine.components.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;

import static net.minecraft.world.level.block.Blocks.SPRUCE_PLANKS;

public class BlockInit {
    // Отложенная регистрация
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IndastrialModForMine.MODID);

    // Создание блока
    public static final RegistryObject<Block> COKE_FURNACE_BLOCK =
            BLOCKS.register("coke_furnace_block", () -> new Block(
                    BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));


    // Создание блока
    public static final RegistryObject<Block> COKE_FURNACE_STAIR =
            BLOCKS.register("coke_furnace_stair", () -> new StairBlock(
                    SPRUCE_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)));


    // Создание плиты
    public static final RegistryObject<Block> COKE_FURNACE_SLAB =
            BLOCKS.register("coke_furnace_slab", () -> new SlabBlock((
                    BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops())));

}
