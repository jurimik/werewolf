package ua.com.jurimik.server

import ua.com.jurimik.player.Player

interface Game {
    fun onStartRegistration()
    fun onRegister(player: Player)
    fun onUnregister(player: Player)
    fun onExtend(seconds: Int)
    fun onEndRegistration()
    fun onStartGame()
    fun onGameStarted()
    fun onEndGame()
    fun onNight()
    fun onDay()
    fun onEvening()
}

enum class GameState {
    Registration,
    Game
}