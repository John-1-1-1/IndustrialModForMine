
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package org.mr.cat.mods.indastrialmodformine.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;
import org.mr.cat.mods.indastrialmodformine.components.client.gui.menu.FireBoxFurnaceMenu;

public class ModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(
			ForgeRegistries.MENU_TYPES, IndastrialModForMine.MODID);

	 public static final RegistryObject<MenuType<FireBoxFurnaceMenu>> FIRE_BOX_FURNACE_MENU =
			 REGISTRY.register("fire_box_furnace_menu", () ->
			 IForgeMenuType.create(FireBoxFurnaceMenu::new));
}
