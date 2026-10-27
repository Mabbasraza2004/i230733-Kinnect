package com.abbasraza.i230733

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ChatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)

        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }

        val list = findViewById<LinearLayout>(R.id.chatList)
        addChatRow(list, "Aisha Khan", "Sending the photos tonight · 2m", R.color.av_crimson,
            unread = true, online = true)
        addChatRow(list, "Design crew", "Maya: the new mockups look clean · 15m", R.color.av_olive,
            unread = true, online = false)
        addChatRow(list, "Lina Marsh", "You: See you at the pier · 1h", R.color.av_purple,
            unread = false, online = false, seenColour = R.color.av_crimson)
        addChatRow(list, "Omar Farooq", "Haha that story was wild · 3h", R.color.av_green,
            unread = false, online = true)
        addChatRow(list, "Bilal Ahmed", "Thanks, bro! · Mon", R.color.av_purple,
            unread = false, online = false)
        addChatRow(list, "Noor Fatima", "You: Happy birthday! · Sun", R.color.av_green,
            unread = false, online = false)
    }

    private fun addChatRow(
        list: LinearLayout, name: String, preview: String, colour: Int,
        unread: Boolean, online: Boolean, seenColour: Int = 0
    ) {
        val row = LayoutInflater.from(this).inflate(R.layout.item_chat_row, list, false)

        val avatar = row.findViewById<TextView>(R.id.tvChatAvatar)
        avatar.text = initialsOf(name)
        avatar.backgroundTintList = ContextCompat.getColorStateList(this, colour)

        val tvName = row.findViewById<TextView>(R.id.tvChatName)
        val tvPreview = row.findViewById<TextView>(R.id.tvChatPreview)
        tvName.text = name
        tvPreview.text = preview

        if (online) row.findViewById<View>(R.id.chatOnlineDot).visibility = View.VISIBLE

        val dot = row.findViewById<View>(R.id.chatStatusDot)
        if (unread) {
            tvName.setTypeface(tvName.typeface, Typeface.BOLD)
            tvPreview.setTypeface(tvPreview.typeface, Typeface.BOLD)
            tvPreview.setTextColor(ContextCompat.getColor(this, R.color.ink))
            dot.visibility = View.VISIBLE
        } else if (seenColour != 0) {
            dot.backgroundTintList = ContextCompat.getColorStateList(this, seenColour)
            dot.visibility = View.VISIBLE
        }

        row.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra(ChatActivity.EXTRA_NAME, name)
            startActivity(intent)
        }
        list.addView(row)
    }

    private fun initialsOf(name: String): String =
        name.split(" ").take(2).joinToString("") { it.first().uppercase() }
}
