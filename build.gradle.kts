plugins {
    id("java")
    id("java-library")

    id("dev.architectury.loom") version("1.11-SNAPSHOT") apply false
    id("architectury-plugin") version("3.4-SNAPSHOT")
}

allprojects {
    apply(plugin = "java")

    version = project.properties["mod_version"]!!
    group = project.properties["mod_group"]!!

    repositories {
        mavenCentral()
        maven("https://www.cursemaven.com")
        maven("https://api.modrinth.com/maven")
        maven("https://maven.neoforged.net/releases")
        maven ("https://maven.blamejared.com") // JEI
    }

    java {
        withSourcesJar()
    }
}

// TODO: figure out how to change the logging level
// TODO: figure out maven publishing
