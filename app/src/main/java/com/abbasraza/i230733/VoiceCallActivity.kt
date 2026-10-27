package com.abbasraza.i230733

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class VoiceCallActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "name"
    }

    private val handler = Handler(Looper.getMainLooper())
    private var seconds = 0
    private lateinit var tvStatus: TextView

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

        handler.postDelayed(timer, 2000)

        findViewById<View>(R.id.btnEndCall).setOnClickListener { finish() }

        toggleOnClick(findViewById(R.id.btnMute))
        toggleOnClick(findViewById(R.id.btnSpeaker))
    }

    private fun toggleOnClick(button: ImageView) {
        button.setOnClickListener {
            button.isSelected = !button.isSelected
            if (button.isSelected) {
                button.setBackgroundResource(R.drawable.bg_circle_white)
                button.imageTintList = ContextCompat.getColorStateList(this, R.color.teal)
            } else {
                button.setBackgroundResource(R.drawable.bg_circle_glass_light)
                button.imageTintList = ContextCompat.getColorStateList(this, R.color.white)
            }
        }
    }

    override fun onDestroy() {
        handler.removeCallbacks(timer)
        super.onDestroy()
    }
}
