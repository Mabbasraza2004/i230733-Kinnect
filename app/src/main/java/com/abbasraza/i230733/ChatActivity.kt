package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ChatActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "name"
    }

    private lateinit var messageList: LinearLayout
    private lateinit var scrollView: ScrollView
    private lateinit var typingRow: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val name = intent.getStringExtra(EXTRA_NAME) ?: "Aisha Khan"
        val initials = name.split(" ").take(2).joinToString("") { it.first().uppercase() }

        findViewById<TextView>(R.id.tvChatTitle).text = name
        findViewById<TextView>(R.id.tvChatAvatar).text = initials
        findViewById<TextView>(R.id.tvIntroName).text = name
        findViewById<TextView>(R.id.tvIntroAvatar).text = initials

        messageList = findViewById(R.id.messageList)
        scrollView = findViewById(R.id.scrollMessages)
        typingRow = findViewById(R.id.typingRow)
        val etMessage = findViewById<EditText>(R.id.etMessage)

        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }

        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<View>(R.id.btnVoiceCall).setOnClickListener {
            val intent = Intent(this, VoiceCallActivity::class.java)
            intent.putExtra(VoiceCallActivity.EXTRA_NAME, name)
            startActivity(intent)
        }

        findViewById<View>(R.id.btnSend).setOnClickListener {
            val text = etMessage.text.toString().trim()
            addBubble(if (text.isEmpty()) "👍" else text)
            etMessage.text.clear()
        }
    }

    private fun addBubble(text: String) {
        val bubble = TextView(this)
        bubble.text = text
        bubble.textSize = 16f
        bubble.setTextColor(ContextCompat.getColor(this, R.color.white))
        bubble.setBackgroundResource(R.drawable.bg_bubble_me)
        val pad = (14 * resources.displayMetrics.density).toInt()
        bubble.setPadding(pad, pad * 2 / 3, pad, pad * 2 / 3)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.END
        params.topMargin = pad / 3
        bubble.layoutParams = params

        messageList.addView(bubble, messageList.indexOfChild(typingRow))
        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
    }
}
