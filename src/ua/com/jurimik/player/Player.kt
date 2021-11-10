package ua.com.jurimik.player

import javax.management.relation.Role

class Player(
    private val id: Long,
    private val chatId: Long,
    val name: String,
    var isAlive: Boolean = true
) {
    private val notificationManager = ProxyPlayerNotificationManager(this)

    var role: Role? = null
    get() = role

    fun vote(playerList: List<Player>) {
        notificationManager.notifyEvent("Кого вешаем?")
        for (player in playerList) {
            if (player == this) continue
            notificationManager.notifyAsButton(player.name) { }
        }
    }
}