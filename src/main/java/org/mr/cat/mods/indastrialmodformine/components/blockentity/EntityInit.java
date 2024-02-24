package org.mr.cat.mods.indastrialmodformine.components.blockentity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;
import org.mr.cat.mods.indastrialmodformine.components.block.BlockInit;

import static net.minecraft.world.level.block.Blocks.FIRE;
import static net.minecraft.world.level.block.Blocks.SPRUCE_PLANKS;
import static org.mr.cat.mods.indastrialmodformine.components.block.BlockInit.COKE_FURNACE_FIREBOX;

public class EntityInit {
    // Отложенная регистрация
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IndastrialModForMine.MODID);

    // Создание сущности
    public static final RegistryObject<BlockEntityType<FireBoxFurnaceEntity>> EXAMPLE_SCREEN_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("example_screen_block",
                    () -> BlockEntityType.Builder.of(FireBoxFurnaceEntity::new, COKE_FURNACE_FIREBOX.get())
                            .build(null));



}
