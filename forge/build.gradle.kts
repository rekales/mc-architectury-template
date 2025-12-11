plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

architectury {
    platformSetupLoomIde()
    forge()
}

loom {
    enableTransitiveAccessWideners.set(true)
    silentMojangMappingsLicense()
}
val shadowBundle = configurations.create("shadowBundle") {
    isCanBeConsumed = false
    isCanBeResolved = true
}


dependencies {
    minecraft("net.minecraft:minecraft:${property("minecraft_version")}")
    mappings(loom.officialMojangMappings())
    forge("net.minecraftforge:forge:${property("forge_version")}")

    implementation(project(":common", configuration = "namedElements"))
    "developmentForge"(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }

    // Development QOL
//    modLocalRuntime("curse.maven:configured-457570:7076243")
//    modLocalRuntime("mezz.jei:jei-${property("minecraft_version")}-neoforge:${property("jei_version")}") { isTransitive = false }

    modImplementation("com.simibubi.create:create-${property("minecraft_version")}:${property("create_version")}:slim")
    modImplementation("net.createmod.ponder:Ponder-Forge-${property("minecraft_version")}:${property("ponder_version")}")
    modCompileOnly("dev.engine-room.flywheel:flywheel-forge-api-${property("minecraft_version")}:${property("flywheel_version")}")
    modRuntimeOnly("dev.engine-room.flywheel:flywheel-forge-${property("minecraft_version")}:${property("flywheel_version")}")
    modImplementation("com.tterrag.registrate:Registrate:${property("registrate_version")}")
    implementation("io.github.llamalad7:mixinextras-forge:0.4.1")
    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")

//    modImplementation("dev.architectury:architectury-neoforge:${property("architectury_api_version")}")
//    modCompileOnly("mezz.jei:jei-${property("minecraft_version")}:${property("jei_version")}:api")
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("META-INF/mods.toml") {
        expand(project.properties)
    }
}

tasks {
    jar {
        archiveBaseName.set("${rootProject.property("mod_id")}-${project.name}")
        archiveClassifier.set("dev-slim")
    }

    shadowJar {
        exclude("fabric.mod.json")
        archiveClassifier.set("dev-shadow")
        archiveBaseName.set("${rootProject.property("mod_id")}-${project.name}")
        configurations = listOf(shadowBundle)
    }

    remapJar {
        dependsOn(shadowJar)
        inputFile.set(shadowJar.flatMap { it.archiveFile })
        archiveBaseName.set("${rootProject.property("mod_id")}-${project.name}")
        archiveVersion.set("${rootProject.version}")
    }
}
