plugins {
    kotlin("jvm") version "2.0.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    `maven-publish`
}

group = "ru.snapix"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.12.2-R0.1-SNAPSHOT")
    compileOnly("ru.snapix:snapilibrary:1.2-bukkit")
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