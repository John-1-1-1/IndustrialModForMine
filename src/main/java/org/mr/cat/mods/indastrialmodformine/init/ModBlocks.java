package org.mr.cat.mods.indastrialmodformine.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;
import org.mr.cat.mods.indastrialmodformine.components.block.FireBoxFurnace;
import org.mr.cat.mods.indastrialmodformine.components.block.HeatPipe;
import org.mr.cat.mods.indastrialmodformine.components.block.IndFurnace;

import java.util.function.ToIntFunction;

import static net.minecraft.world.level.block.Blocks.SPRUCE_PLANKS;

public class ModBlocks {

    private static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {
        return (p_50763_) -> {
            return (Boolean)p_50763_.getValue(BlockStateProperties.LIT) ? p_50760_ : 0;
        };
    }

    // Отложенная регистрация
    public static final DeferredRegister<Block> REGISTRY =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IndastrialModForMine.MODID);

    // Создание блока
    public static final RegistryObject<Block> IND_FURNACE_BLOCK =
            REGISTRY.register("ind_furnace_block", () -> new Block(
                    BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));


    // Создание блока
    public static final RegistryObject<Block> IND_FURNACE_STAIR =
            REGISTRY.register("ind_furnace_stair", () -> new StairBlock(
                    SPRUCE_PLANKS.defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE)));


    // Создание плиты
    public static final RegistryObject<Block> IND_FURNACE_SLAB =
            REGISTRY.register("ind_furnace_slab", () -> new SlabBlock((
                    BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops())));

    // Создание топки
    public static final RegistryObject<Block> IND_FURNACE_FIREBOX =
            REGISTRY.register("ind_furnace_firebox", () -> new FireBoxFurnace((
                    BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().
                            lightLevel(litBlockEmission(13)))));


    // Создание печи
    public static final RegistryObject<Block> IND_FURNACE =
            REGISTRY.register("ind_furnace", () -> new IndFurnace(
                    BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops()));

    // Создание печи
    public static final RegistryObject<Block> SILVER_ORE =
            REGISTRY.register("silver_ore", () -> new IndFurnace(
                    BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops()));


    public static final RegistryObject<Block> HEAT_PIPE =
            REGISTRY.register("heat_pipe", () -> new HeatPipe(
                    BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops()));


}
