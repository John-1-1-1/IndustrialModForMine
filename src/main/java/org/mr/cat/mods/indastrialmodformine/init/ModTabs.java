package org.mr.cat.mods.indastrialmodformine.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModTabs {

    // отложенная регистрация вкладки
    public static final DeferredRegister<CreativeModeTab> RYGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IndastrialModForMine.MODID);

    // отображаемые элементы
    public static final List<Supplier<? extends ItemLike>> INDASTRIAL_TAB_ITEMS = new ArrayList<>();

    // регистрация элементов для вкладки
    public static final RegistryObject<CreativeModeTab> INDASTRIAL_TAB = RYGISTRY.register("indastrial_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.indastrial_tab"))
                    //.icon(ItemInit.EXAMPLE_ITEM.get()::getDefaultInstance)
                    .displayItems((displayParams, output) ->
                            INDASTRIAL_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get())))
                    .withSearchBar()
                    .build()
    );

    // Добавление items в отложенную регистрацию во вкладке
    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        INDASTRIAL_TAB_ITEMS.add(itemLike);
        return itemLike;
    }
}
