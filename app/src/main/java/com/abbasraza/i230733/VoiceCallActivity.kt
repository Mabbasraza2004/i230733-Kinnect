package com.abbasraza.i230733

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Voice call screen. Shows "Calling…" for two seconds, then pretends the
 * call was answered and counts the call time up every second.
 */
class VoiceCallActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "name"
    }

    private val handler = Handler(Looper.getMainLooper())
    private var seconds = 0
    private lateinit var tvStatus: TextView

    // Updates the mm:ss timer once a second
    private val timer = object : Runnable {
        override fun run() {
            tvStatus.text = String.format("%02d:%02d", seconds / 60, seconds % 60)
            seconds++
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_call)

        val name = intent.getStringExtra(EXTRA_NAME) ?: "Unknown"
        findViewById<TextView>(R.id.tvCallName).text = name
        findViewById<TextView>(R.id.tvCallAvatar).text =
            name.split(" ").take(2).joinToString("") { it.first().uppercase() }

        tvStatus = findViewById(R.id.tvCallStatus)

        // "Answer" after 2 seconds, then start the timer
        handler.postDelayed(timer, 2000)

        findViewById<TextView>(R.id.btnEndCall).setOnClickListener { finish() }

        // Mute / speaker just toggle their look
        toggleOnClick(findViewById(R.id.btnMute))
        toggleOnClick(findViewById(R.id.btnSpeaker))
    }

    private fun toggleOnClick(button: TextView) {
        button.setOnClickListener {
            button.isSelected = !button.isSelected
            button.alpha = if (button.isSelected) 1f else 0.6f
        }
    }

    override fun onDestroy() {
        // Stop the timer when the call screen closes
        handler.removeCallbacks(timer)
        super.onDestroy()
    }
}
