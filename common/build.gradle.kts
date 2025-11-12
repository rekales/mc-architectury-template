plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
}

architectury {
    common("neoforge", "fabric")
}

dependencies {
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings(loom.officialMojangMappings())
//    modImplementation("dev.architectury:architectury:${property("architectury_api_version")}")
}
