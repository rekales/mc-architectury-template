package com.kreidev.templatemod.neoforge;

import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfigNeoForge {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    static final ForgeConfigSpec SPEC = BUILDER.build();

    static void onLoad(final ModConfigEvent.Loading event) {
    }

    static void onReload(final ModConfigEvent.Reloading event) {
    }
}
