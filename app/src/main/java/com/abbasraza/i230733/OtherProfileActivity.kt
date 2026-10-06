package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Another person's profile, opened from search. You can send them a
 * friend request or start a chat.
 */
class OtherProfileActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "name"
    }

    private var requestSent = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_profile)

        val name = intent.getStringExtra(EXTRA_NAME) ?: "Omar Farooq"
        findViewById<TextView>(R.id.tvOtherName).text = name
        findViewById<TextView>(R.id.tvOtherAvatar).text =
            name.split(" ").take(2).joinToString("") { it.first().uppercase() }

        findViewById<TextView>(R.id.btnBack).setOnClickListener { finish() }

        // Toggles between "Add friend" and "Cancel request"
        val btnAddFriend = findViewById<TextView>(R.id.btnAddFriend)
        btnAddFriend.setOnClickListener {
            requestSent = !requestSent
            btnAddFriend.text = if (requestSent) "✓ Request sent" else "+ Add friend"
        }

        findViewById<TextView>(R.id.btnMessage).setOnClickListener {
            val chat = Intent(this, ChatActivity::class.java)
            chat.putExtra(ChatActivity.EXTRA_NAME, name)
            startActivity(chat)
        }
    }
}
