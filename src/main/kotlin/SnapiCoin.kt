package ru.snapix.snapicoin

import org.bukkit.plugin.java.JavaPlugin

class SnapiCoin : JavaPlugin() {
    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {

    }

    companion object {
        lateinit var instance: SnapiCoin
    }
}

val snapiCoin = SnapiCoin.instance