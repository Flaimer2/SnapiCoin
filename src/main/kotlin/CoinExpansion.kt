package ru.snapix.snapicoin

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.entity.Player
import ru.snapix.snapicoin.api.CoinApi

class CoinExpansion : PlaceholderExpansion() {
    override fun getIdentifier() = "coin"
    override fun getAuthor() = "SnapiX"
    override fun getVersion() = "1.0.0"
    override fun persist() = true

    override fun onPlaceholderRequest(player: Player, params: String): String? {
        val user = CoinApi.user(player.name) ?: return null
        return when (params) {
            "amount" -> user.coin.toString()
            else -> null
        }
    }
}