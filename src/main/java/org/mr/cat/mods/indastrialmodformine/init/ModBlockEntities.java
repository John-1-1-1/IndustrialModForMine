package org.mr.cat.mods.indastrialmodformine.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;
import org.mr.cat.mods.indastrialmodformine.components.blockentity.FireBoxFurnaceEntity;

import static org.mr.cat.mods.indastrialmodformine.init.ModBlocks.COKE_FURNACE_FIREBOX;

public class ModBlockEntities {
    // Отложенная регистрация
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IndastrialModForMine.MODID);

    // Создание сущности
    public static final RegistryObject<BlockEntityType<?>> EXAMPLE_SCREEN_BLOCK_ENTITY =
            register("example_screen_block",
                    COKE_FURNACE_FIREBOX,
                    FireBoxFurnaceEntity::new);

    private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
        return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
    }

}
