package com.abbasraza.i230733

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

/**
 * Notifications list. Unread ones have a light teal background;
 * tapping a notification marks it as read.
 */
class NotificationsActivity : AppCompatActivity() {

    private lateinit var list: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        findViewById<TextView>(R.id.tvHeaderTitle).text = "Notifications"

        list = findViewById(R.id.notificationList)

        addNotification("OF", "Omar Farooq reacted to your post.", "5m", unread = true)
        addNotification("SN", "Sara Noor sent you a friend request.", "20m", unread = true)
        addNotification("AK", "Aisha Khan commented: \"Take me with you next time\"", "1h", unread = false)
        addNotification("HA", "Hamza Ali shared a memory with you.", "3h", unread = false)
        addNotification("BA", "It's Bilal Ahmed's birthday today. Wish him well!", "8h", unread = false)

        BottomNav.setup(this, R.id.tabNotifications)
    }

    private fun addNotification(initials: String, text: String, time: String, unread: Boolean) {
        val row = LayoutInflater.from(this).inflate(R.layout.item_notification, list, false)
        row.findViewById<TextView>(R.id.tvNotifAvatar).text = initials
        row.findViewById<TextView>(R.id.tvNotifText).text = text
        row.findViewById<TextView>(R.id.tvNotifTime).text = time

        if (unread) {
            row.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_light))
        }
        // Tapping marks it as read by removing the highlight
        row.setOnClickListener { row.setBackgroundColor(0) }

        list.addView(row)
    }
}
