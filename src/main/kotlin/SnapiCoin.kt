package ru.snapix.snapicoin

import caches.Coins
import org.bukkit.plugin.java.JavaPlugin
import ru.snapix.library.ServerType
import ru.snapix.library.snapiLibrary
import ru.snapix.snapicoin.commands.Commands
import ru.snapix.snapicoin.database.CoinDatabase
import ru.snapix.snapicoin.listeners.PlayerListener
import ru.snapix.snapicoin.settings.Settings

class SnapiCoin : JavaPlugin() {
    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {
        CoinDatabase.load()

        if (snapiLibrary.serverType == ServerType.LOBBY) {
            CoinDatabase.users().forEach { Coins.updateUser(it) }
            server.pluginManager.registerEvents(PlayerListener(), this)
        }

        Commands.enable()
        CoinExpansion().register()
    }

    fun reload() {
        Settings.reload()
    }

    companion object {
        lateinit var instance: SnapiCoin
    }
}

val snapiCoin = SnapiCoin.instance