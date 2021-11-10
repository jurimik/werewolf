package ua.com.jurimik.server

import ua.com.jurimik.player.Player
import ua.com.jurimik.role.Daily
import ua.com.jurimik.role.Nightly
import ua.com.jurimik.role.Twilight

class Room (private val chatId: Long) : Game {

    private val notificationManager = ProxyNotificationManager()
    private var playerList = mutableListOf<Player>()
    private var registrationTime: Int = 120

    private var state: GameState = GameState.Registration

    private val minPlayersForGame = 5

    override fun onStartRegistration() {
        notificationManager.notifyEvent("Регистрация началась. У вас есть $registrationTime секунд, чтобы вступить в игру")
        playerList = mutableListOf()
    }

    override fun onRegister(player: Player) {
        if (playerList.add(player)) notificationManager.notifyEvent("Игрок ${player.name} вступил в игру")
    }

    override fun onUnregister(player: Player) {
        if (playerList.remove(player)) notificationManager.notifyEvent("Игрок ${player.name} покинул игру")
    }

    override fun onExtend(seconds: Int) {
        if (seconds < 0) return
        if (state == GameState.Registration && registrationTime > 0) {
            registrationTime += seconds
            notificationManager.notifyEvent("Время увеличено на $seconds секунд. Осталось времени на регистрацию $registrationTime секунд")
        } else {
            notificationManager.notifyEvent("Эту команду можно использовать только во время регистрации")
        }
    }

    override fun onEndRegistration() {
        if (minPlayersForGame > playerList.size) {
            notificationManager.notifyEvent("Вас очень мало для игры. Давай по новой, Миша")
            onEndGame()
        } else {
            notificationManager.notifyEvent("Игра начинается")
            onStartGame()
        }
    }

    override fun onStartGame() {
        notificationManager.notifyEvent("Распределяем роли")
        drawRoles(playerList)
    }

    override fun onGameStarted() {
        notificationManager.notifyEvent("Роли распределены, пора за работу")
    }

    override fun onEndGame() {
        notificationManager.notifyEvent("Игра окончена")
    }

    override fun onNight() {
        notificationManager.notifyEvent("Наступила ночь")
        for (player in playerList) {
            if (player.role is Nightly) {
                (player.role as Nightly).nightAction()
            }
        }
    }

    override fun onDay() {
        notificationManager.notifyEvent("Наступил день")
        for (player in playerList) {
            if (player.role is Daily) {
                (player.role as Daily).dayAction()
            }
        }
    }

    override fun onEvening() {
        notificationManager.notifyEvent("Наступил вечер")
        for (player in playerList) {
            if (!player.isAlive) continue
            player.vote(playerList)
            if (player.role is Twilight) {
                (player.role as Twilight).eveningAction()
            }
        }
    }

    private fun drawRoles(playerList: List<Player>) {
        onGameStarted()
        TODO("Not yet implemented")
    }


}