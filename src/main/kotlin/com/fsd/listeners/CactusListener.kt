package com.fsd.listeners

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByBlockEvent

class CactusListener : Listener {

    @EventHandler
    fun onEntityDamageByBlock(event: EntityDamageByBlockEvent) {
        val damagerBlock = event.damager
        if (damagerBlock != null) {
            if (damagerBlock.type == Material.STONE) {
                if (event.entity is org.bukkit.entity.Item) {
                    event.isCancelled = true
                }
            }
        }
    }
}
