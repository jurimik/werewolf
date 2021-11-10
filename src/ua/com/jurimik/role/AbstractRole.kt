package ua.com.jurimik.role

interface AbstractRole {

    fun onGameStarted()

    fun onDay()
    fun onEvening()
    fun onNight()

    fun onCheck()
    fun onKill()
    fun onExecution()
}

