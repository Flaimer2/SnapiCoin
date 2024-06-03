package ru.snapix.plugin_name

import org.bukkit.plugin.java.JavaPlugin

class Plugin_Name : JavaPlugin() {
    override fun onLoad() {
        instance = this
    }

    companion object {
        lateinit var instance: Plugin_Name
    }
}