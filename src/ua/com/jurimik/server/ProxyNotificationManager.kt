package ua.com.jurimik.server

class ProxyNotificationManager : NotificationManager {
    override fun notifyEvent(message: String) {
        println(message)
    }

    override fun notifyAsButton(label: String, callback: () -> Unit) {
        println("[$label]")
    }
}