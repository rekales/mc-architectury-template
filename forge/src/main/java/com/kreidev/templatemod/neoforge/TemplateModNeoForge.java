package com.kreidev.templatemod.neoforge;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

import com.kreidev.templatemod.TemplateMod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import static com.kreidev.templatemod.TemplateMod.MOD_ID;

@Mod(MOD_ID)
public final class TemplateModNeoForge {

    public TemplateModNeoForge() {
        net.minecraftforge.eventbus.api.IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CommonConfigNeoForge.SPEC);
        TemplateMod.init();
        modEventBus.addListener(CommonConfigNeoForge::onLoad);
        modEventBus.addListener(CommonConfigNeoForge::onReload);
    }
}
