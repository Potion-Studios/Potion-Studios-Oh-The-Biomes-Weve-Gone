pluginManagement.repositories {
    maven("https://maven.fabricmc.net/")
    maven("https://maven.architectury.dev/")
    maven("https://maven.minecraftforge.net/")
    maven("https://maven.neoforged.net/releases/")
    maven("https://maven.firstdarkdev.xyz/releases")
    gradlePluginPortal()
}

plugins {
    id("com.gradle.develocity") version("3.19.2")
}

develocity.buildScan {
    termsOfUseUrl = "https://gradle.com/terms-of-service"
    termsOfUseAgree = "yes"
}

include("Common", "Fabric", "Forge", "NeoForge")

rootProject.name = "Oh The Biomes We've Gone"
