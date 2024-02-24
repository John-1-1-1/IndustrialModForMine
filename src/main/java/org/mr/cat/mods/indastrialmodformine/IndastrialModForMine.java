package org.mr.cat.mods.indastrialmodformine;

import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.mr.cat.mods.indastrialmodformine.init.*;
import org.slf4j.Logger;


@Mod(IndastrialModForMine.MODID)
public class IndastrialModForMine {

    // Идентификатор мода
    public static final String MODID = "indastrialmodformine";
    // Логгер
    private static final Logger LOGGER = LogUtils.getLogger();

    // Конструктор
    public IndastrialModForMine() {

        // подгрузка регистраторов
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // регистрация commonSetup метода в modloading
        modEventBus.addListener(this::commonSetup);
        // подгрузка регистратора блоков
        ModBlocks.REGISTRY.register(modEventBus);
        // подгрузка регистратора предметов
        ModItems.REGISTRY.register(modEventBus);
        // подгрузка регистратора Entity
        ModBlockEntities.REGISTRY.register(modEventBus);
        // подгрузка регистратора менюшек
        ModMenus.REGISTRY.register(modEventBus);

        ModTabs.RYGISTRY.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }
}
