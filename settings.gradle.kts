pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev/")
        maven("https://maven.minecraftforge.net/")
        gradlePluginPortal()
    }
}

rootProject.name = "templatemod"

listOf(
    "common",
    "forge",
    "fabric"
).forEach { include(it)}
