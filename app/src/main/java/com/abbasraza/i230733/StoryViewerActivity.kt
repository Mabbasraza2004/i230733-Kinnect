package com.abbasraza.i230733

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StoryViewerActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "name"
        private const val STORY_LENGTH_MS = 5000L
        private const val TICK_MS = 50L
    }

    private val handler = Handler(Looper.getMainLooper())
    private var elapsed = 0L
    private lateinit var progress: ProgressBar

    private val tick = object : Runnable {
        override fun run() {
            elapsed += TICK_MS
            progress.progress = (elapsed * 100 / STORY_LENGTH_MS).toInt()
            if (elapsed >= STORY_LENGTH_MS) finish() else handler.postDelayed(this, TICK_MS)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_viewer)

        progress = findViewById(R.id.storyProgress)

        val name = intent.getStringExtra(EXTRA_NAME) ?: "Story"
        findViewById<TextView>(R.id.tvStoryName).text = name
        findViewById<TextView>(R.id.tvStoryAvatar).text = initialsOf(name)

        findViewById<View>(R.id.btnCloseViewer).setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(tick, TICK_MS)
    }

    override fun onPause() {
        handler.removeCallbacks(tick)
        super.onPause()
    }

    private fun initialsOf(name: String): String =
        name.split(" ").filter { it.isNotEmpty() }.take(2)
            .joinToString("") { it.first().uppercase() }
}
