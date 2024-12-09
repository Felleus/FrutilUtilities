package com.fsd

import co.aikar.commands.PaperCommandManager
import com.fsd.commands.DamageCommand
import com.fsd.listeners.CactusListener
import com.fsd.listeners.EnderCrystalListener
import org.bukkit.plugin.java.JavaPlugin

class FrutilUtilities : JavaPlugin() {

    var damage: Double = 10.0
        private set

    override fun onEnable() {
        saveDefaultConfig()
        damage = config.getDouble("ender_crystal_damage", 10.0)

        val commandManager = PaperCommandManager(this)
        commandManager.registerCommand(DamageCommand(this))

        server.pluginManager.registerEvents(EnderCrystalListener(this), this)
        server.pluginManager.registerEvents(CactusListener(), this)
    }

    override fun onDisable() {
    }

    fun setDamage(newDamage: Double) {
        damage = newDamage
        config.set("ender_crystal_damage", newDamage)
        saveConfig()
    }
}
