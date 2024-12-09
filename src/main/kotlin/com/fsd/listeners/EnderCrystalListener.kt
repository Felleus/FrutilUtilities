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
        val location = enderCrystal.location
        val distance = location.distance(player.location)
        val damageReduction = (distance / 2)
        val finalDamage = (plugin.damage - damageReduction).coerceAtLeast(0.0)

        if (finalDamage > 0) {
            event.damage = finalDamage
            player.sendMessage("§cВы получили $finalDamage урона от эндер кристалла.")
        } else {
            event.isCancelled = true
            player.sendMessage("§cУрон от эндер кристалла не был нанесён (слишком далеко).")
        }
    }
}
