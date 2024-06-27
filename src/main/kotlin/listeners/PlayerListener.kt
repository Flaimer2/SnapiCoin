package ru.snapix.snapicoin.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import ru.snapix.snapicoin.api.CoinApi

class PlayerListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val name = event.player.name

        if (CoinApi.hasUser(name)) {
            return
        }

        CoinApi.createUser(name)
    }
}