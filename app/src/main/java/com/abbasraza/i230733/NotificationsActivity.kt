package com.abbasraza.i230733

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat

class NotificationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        findViewById<TextView>(R.id.tvHeaderTitle).text = "Notifications"

        val newList = findViewById<LinearLayout>(R.id.newList)
        val earlierList = findViewById<LinearLayout>(R.id.earlierList)

        addNotification(newList, "AK", R.color.av_crimson, R.drawable.ic_heart, R.color.react_love,
            "<b>Aisha Khan</b> and <b>32 others</b> reacted to your photo.", "5m", unread = true)

        val request = addNotification(newList, "SI", R.color.av_indigo, R.drawable.ic_person, R.color.teal,
            "<b>Sara Iqbal</b> sent you a friend request.", "20m", unread = true)
        showRequestButtons(request)

        addNotification(earlierList, "ZR", R.color.av_slate, R.drawable.ic_comment, R.color.av_green,
            "<b>Zain Raza</b> replied to your comment: “next time invite the rest of us”", "2h", unread = false)
        addNotification(earlierList, "DC", R.color.av_olive, R.drawable.ic_friends, R.color.av_indigo,
            "New post in <b>SMD Class of 2026</b>: “Assignment 1 is out. Start early.”", "5h", unread = false)
        addNotification(earlierList, "HA", R.color.av_brown, R.drawable.ic_tag, R.color.react_amber,
            "<b>Hamza Ali</b> tagged you in a post.", "Yesterday", unread = false)
        addNotification(earlierList, "LM", R.color.av_purple, R.drawable.ic_like, R.color.teal,
            "<b>Lina Marsh</b> liked your story.", "2d", unread = false)

        BottomNav.setup(this, R.id.tabNotifications)
    }

    private fun addNotification(
        list: LinearLayout, initials: String, avatarColour: Int,
        typeIcon: Int, typeColour: Int, text: String, time: String, unread: Boolean
    ): View {
        val row = LayoutInflater.from(this).inflate(R.layout.item_notification, list, false)

        val avatar = row.findViewById<TextView>(R.id.tvNotifAvatar)
        avatar.text = initials
        avatar.backgroundTintList = ContextCompat.getColorStateList(this, avatarColour)

        val type = row.findViewById<ImageView>(R.id.ivNotifType)
        type.setImageResource(typeIcon)
        type.backgroundTintList = ContextCompat.getColorStateList(this, typeColour)

        row.findViewById<TextView>(R.id.tvNotifText).text =
            HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val tvTime = row.findViewById<TextView>(R.id.tvNotifTime)
        tvTime.text = time

        if (unread) {
            row.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_light))
            tvTime.setTextColor(ContextCompat.getColor(this, R.color.teal))
            tvTime.setTypeface(tvTime.typeface, Typeface.BOLD)
        }

        row.setOnClickListener {
            row.setBackgroundColor(0)
            tvTime.setTextColor(ContextCompat.getColor(this, R.color.ink_muted))
        }

        list.addView(row)
        return row
    }

    private fun showRequestButtons(row: View) {
        val buttons = row.findViewById<View>(R.id.notifButtons)
        buttons.visibility = View.VISIBLE
        row.findViewById<View>(R.id.btnNotifConfirm).setOnClickListener {
            Toast.makeText(this, "Friend request accepted", Toast.LENGTH_SHORT).show()
            buttons.visibility = View.GONE
        }
        row.findViewById<View>(R.id.btnNotifDelete).setOnClickListener {
            buttons.visibility = View.GONE
        }
    }
}
