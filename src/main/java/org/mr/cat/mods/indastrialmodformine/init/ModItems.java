package org.mr.cat.mods.indastrialmodformine.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;

import static org.mr.cat.mods.indastrialmodformine.init.ModTabs.addToTab;

public class ModItems {

    // Отложенная регистрация сех предметов
    public static final DeferredRegister<Item> REGISTRY =
            DeferredRegister.create(ForgeRegistries.ITEMS,
                    IndastrialModForMine.MODID);

    // Создание предмета блока
    public static RegistryObject<Item> IND_FURNACE_ITEM =
            block(ModBlocks.IND_FURNACE_BLOCK);

    // Создание предмета ступеньки
    public static RegistryObject<Item> IND_FURNACE_STAIR_ITEM =
            block(ModBlocks.IND_FURNACE_STAIR);

    // Создание предмета плиты
    public static RegistryObject<Item> IND_FURNACE_SLAB_ITEM =
            block(ModBlocks.IND_FURNACE_SLAB);


    // Создание предмета плиты
    public static RegistryObject<Item> IND_FURNACE_FIREBOX =
            block(ModBlocks.IND_FURNACE_FIREBOX);

    // Создание предмета плиты
    public static RegistryObject<Item> IND_FURNACE =
            block(ModBlocks.IND_FURNACE);

    // Создание предмета плиты
    public static RegistryObject<Item> SILVER_ORE =
            block(ModBlocks.SILVER_ORE);

    private static RegistryObject<Item> block(RegistryObject<Block> block) {
        return addToTab(REGISTRY.register(
                block.getId().getPath(),
                () -> new BlockItem(block.get(),
                        new Item.Properties())));
    }
}
