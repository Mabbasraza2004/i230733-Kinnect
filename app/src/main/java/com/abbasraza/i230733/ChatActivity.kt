package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

/**
 * One conversation. Messages are TextView "bubbles" added to a vertical list:
 * mine on the right in teal, theirs on the left in white.
 */
class ChatActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "name"
    }

    private lateinit var messageList: LinearLayout
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val name = intent.getStringExtra(EXTRA_NAME) ?: "Aisha Khan"
        findViewById<TextView>(R.id.tvChatTitle).text = name

        messageList = findViewById(R.id.messageList)
        scrollView = findViewById(R.id.scrollMessages)
        val etMessage = findViewById<EditText>(R.id.etMessage)

        // A short sample conversation
        addBubble("Hey! Are we still meeting today?", mine = false)
        addBubble("Yes, after class", mine = true)
        addBubble("See you at 5 then!", mine = false)

        findViewById<TextView>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<TextView>(R.id.btnVoiceCall).setOnClickListener {
            val intent = Intent(this, VoiceCallActivity::class.java)
            intent.putExtra(VoiceCallActivity.EXTRA_NAME, name)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.btnSend).setOnClickListener {
            val text = etMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                addBubble(text, mine = true)
                etMessage.text.clear()
            }
        }
    }

    /** Creates a bubble in code (no XML needed) and adds it to the conversation. */
    private fun addBubble(text: String, mine: Boolean) {
        val bubble = TextView(this)
        bubble.text = text
        bubble.textSize = 15f
        val pad = (12 * resources.displayMetrics.density).toInt() // 12dp in pixels
        bubble.setPadding(pad, pad / 2 + 2, pad, pad / 2 + 2)

        if (mine) {
            bubble.setBackgroundResource(R.drawable.bg_bubble_me)
            bubble.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            bubble.setBackgroundResource(R.drawable.bg_bubble_them)
            bubble.setTextColor(ContextCompat.getColor(this, R.color.ink))
        }

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        // Right side for my messages, left side for theirs
        params.gravity = if (mine) Gravity.END else Gravity.START
        params.topMargin = pad / 2
        bubble.layoutParams = params

        messageList.addView(bubble)
        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
    }
}
