
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package org.mr.cat.mods.indastrialmodformine.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.mr.cat.mods.indastrialmodformine.IndastrialModForMine;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			// MenuScreens.register(IndastrialModForMine.CSD.get(), CsdScreen::new);
		});
	}
}
