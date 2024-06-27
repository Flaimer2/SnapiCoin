plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    `maven-publish`
}

group = "ru.snapix"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.12.2-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.0")
    compileOnly("ru.snapix:snapilibrary-bukkit:1.4.1")
    compileOnly("ru.snapix:snapibalancer-bukkit:1.3")
}

bukkit {
    main = "ru.snapix.snapicoin.SnapiCoin"
    author = "SnapiX"
    website = "https://mcsnapix.ru"
    description = "Плагин на валюту SnapiCoin"
    depend = listOf("SnapiLibrary")
    softDepend = listOf("PlaceholderAPI")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = project.name.lowercase()
            groupId = group.toString()

            from(components["java"])
        }
    }
}