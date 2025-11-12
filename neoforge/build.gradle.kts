plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

architectury {
    platformSetupLoomIde()
    neoForge()
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
    neoForge("net.neoforged:neoforge:${property("neoforge_version")}")

//    modImplementation("dev.architectury:architectury-neoforge:${property("architectury_api_version")}")

    implementation(project(":common", configuration = "namedElements"))
    "developmentNeoForge"(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }
    shadowBundle(project(":common", configuration = "transformProductionFabric"))


    modLocalRuntime("curse.maven:configured-457570:7076243")
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("META-INF/neoforge.mods.toml") {
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
