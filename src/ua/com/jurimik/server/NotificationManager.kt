package ua.com.jurimik.server

interface NotificationManager {
    fun notifyEvent(message: String)
    fun notifyAsButton(label: String, callback: () -> Unit)
}