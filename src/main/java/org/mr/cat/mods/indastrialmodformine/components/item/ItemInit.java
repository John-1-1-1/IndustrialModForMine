package org.mr.cat.mods.indastrialmodformine.components.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;
import org.mr.cat.mods.indastrialmodformine.components.block.BlockInit;

import static org.mr.cat.mods.indastrialmodformine.components.CreativeTabInit.addToTab;

public class ItemInit {

    // Отложенная регистрация сех предметов
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IndastrialModForMine.MODID);

    // Создание предмета блока
    public static RegistryObject<BlockItem> COKE_FURNACE_ITEM =
            addToTab(ITEMS.register("coke_furnace_block", () ->
                    new BlockItem(BlockInit.COKE_FURNACE_BLOCK.get(),
                            new Item.Properties())));

    // Создание предмета ступеньки
    public static RegistryObject<BlockItem> COKE_FURNACE_STAIR_ITEM =
            addToTab(ITEMS.register("coke_furnace_stair", () ->
                    new BlockItem(BlockInit.COKE_FURNACE_STAIR.get(),
                            new Item.Properties())));

    // Создание предмета плиты
    public static RegistryObject<BlockItem> COKE_FURNACE_SLAB_ITEM =
            addToTab(ITEMS.register("coke_furnace_slab", () ->
                    new BlockItem(BlockInit.COKE_FURNACE_SLAB.get(),
                            new Item.Properties())));
}
