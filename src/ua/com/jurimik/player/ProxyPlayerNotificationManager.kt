package ua.com.jurimik.player

import ua.com.jurimik.server.NotificationManager

class ProxyPlayerNotificationManager(private val player: Player) : NotificationManager {
    override fun notifyEvent(message: String) {
        println("message for ${player.name}: $message")
    }

    override fun notifyAsButton(label: String, callback: () -> Unit) {
        println("message for ${player.name}: $[$label]")
    }
}