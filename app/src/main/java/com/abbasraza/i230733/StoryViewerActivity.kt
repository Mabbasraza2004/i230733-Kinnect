package com.abbasraza.i230733

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Full-screen view of a friend's story. The progress bar at the top fills
 * up over five seconds, then the story closes by itself.
 */
class StoryViewerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "name"
        private const val STORY_LENGTH_MS = 5000L
        private const val TICK_MS = 50L
    }

    private val handler = Handler(Looper.getMainLooper())
    private var elapsed = 0L
    private lateinit var progress: ProgressBar

    // Runs every TICK_MS milliseconds to move the progress bar forward
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

        // The name comes from whichever story card was tapped on the home screen
        val name = intent.getStringExtra(EXTRA_NAME) ?: "Story"
        findViewById<TextView>(R.id.tvStoryName).text = name
        findViewById<TextView>(R.id.tvStoryAvatar).text = initialsOf(name)

        findViewById<TextView>(R.id.btnCloseViewer).setOnClickListener { finish() }
    }

    // Start/stop the timer with the screen so it doesn't run in the background
    override fun onResume() {
        super.onResume()
        handler.postDelayed(tick, TICK_MS)
    }

    override fun onPause() {
        handler.removeCallbacks(tick)
        super.onPause()
    }

    /** "Omar Farooq" -> "OF" */
    private fun initialsOf(name: String): String =
        name.split(" ").filter { it.isNotEmpty() }.take(2)
            .joinToString("") { it.first().uppercase() }
}
