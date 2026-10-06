package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Messenger inbox: a list of conversations. Tapping one opens ChatActivity.
 */
class ChatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)

        findViewById<TextView>(R.id.btnBack).setOnClickListener { finish() }

        val list = findViewById<LinearLayout>(R.id.chatList)
        addChatRow(list, "Aisha Khan", "See you at 5 then!", "2m")
        addChatRow(list, "Omar Farooq", "Sent a photo", "1h")
        addChatRow(list, "Hamza Ali", "Did you finish the assignment?", "3h")
        addChatRow(list, "Sara Noor", "Haha that's so true", "Yesterday")
        addChatRow(list, "Bilal Ahmed", "Ok bro", "Mon")
    }

    /** Builds one inbox row from item_chat_row.xml and makes it open the chat. */
    private fun addChatRow(list: LinearLayout, name: String, lastMessage: String, time: String) {
        val row = LayoutInflater.from(this).inflate(R.layout.item_chat_row, list, false)
        row.findViewById<TextView>(R.id.tvChatAvatar).text = initialsOf(name)
        row.findViewById<TextView>(R.id.tvChatName).text = name
        row.findViewById<TextView>(R.id.tvChatPreview).text = "$lastMessage · $time"

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
