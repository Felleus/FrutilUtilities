package com.fsd.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.HelpCommand
import com.fsd.FrutilUtilities
import org.bukkit.command.CommandSender

@CommandAlias("fsdcdamage")
@CommandPermission("frutilutilities.command.fsdcdamage")
class DamageCommand(private val plugin: FrutilUtilities) : BaseCommand() {

    @Default
    fun onSetDamage(sender: CommandSender, damage: String?) {
        if (damage.isNullOrEmpty()) {
            return
        }

        val parsedDamage: Double
        try {
            parsedDamage = damage.toDouble()
        } catch (e: NumberFormatException) {
            sender.sendMessage("§cОшибка: Введено неверное значение для урона. Пожалуйста, используйте числовое значение.")
            return
        }

        if (parsedDamage < 0) {
            sender.sendMessage("§cУрон не может быть отрицательным.")
            return
        }

        plugin.setDamage(parsedDamage)
        sender.sendMessage("§aУрон от эндер кристала установлен на §6$parsedDamage")
    }

    @HelpCommand
    fun onHelp(sender: CommandSender) {
        sender.sendMessage("§7Использование команды: §6/fsdcdamage <урон>")
    }
}
