package com.fsd.listeners

import com.fsd.FrutilUtilities
import org.bukkit.entity.EnderCrystal
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class EnderCrystalListener(private val plugin: FrutilUtilities) : Listener {

    @EventHandler
    fun onEnderCrystalDamage(event: EntityDamageByEntityEvent) {
        if (event.damager !is EnderCrystal) return
        if (event.entity !is Player) return

        val player = event.entity as Player
        val enderCrystal = event.damager as EnderCrystal
        val crystalLocation = enderCrystal.location
        val playerLocation = player.location

        if (playerLocation.blockY == crystalLocation.blockY - 1) {
            event.damage = 0.0
            return
        }

        val distance = crystalLocation.distance(playerLocation)
        val damageReduction = (distance / 2)
        val finalDamage = (plugin.damage - damageReduction).coerceAtLeast(0.0)

        if (finalDamage > 0) {
            event.damage = finalDamage
        } else {
            event.damage = 0.0
            player.sendMessage("§cУрон от эндер кристалла не был нанесён (слишком далеко).")
        }
    }
}
