package ru.snapix.snapicoin.settings

import ru.snapix.library.config.Configuration
import ru.snapix.library.config.configurationOptions
import ru.snapix.library.config.create
import ru.snapix.snapicoin.snapiCoin

object Settings {
    private val options = configurationOptions {
        createSingleElementCollections = true
    }
    private val mainConfig = Configuration.create("config.yml", MainConfig::class.java, snapiCoin, options)
    private val databaseConfig = Configuration.create("database.yml", DatabaseConfig::class.java, snapiCoin, options)
    private val messageConfig = Configuration.create("message.yml", MessageConfig::class.java, snapiCoin, options)
    val config
        get() = mainConfig.data()
    val database
        get() = databaseConfig.data()
    val message
        get() = messageConfig.data()

    fun reload() {
        databaseConfig.reloadConfig()
    }
}