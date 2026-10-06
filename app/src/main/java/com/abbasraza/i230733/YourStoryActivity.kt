package com.abbasraza.i230733

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Shows the story the user just shared, using the text and colour
 * passed in from StoryEditorActivity.
 */
class YourStoryActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TEXT = "text"
        const val EXTRA_COLOUR = "colour"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_story)

        val text = intent.getStringExtra(EXTRA_TEXT)
        val colour = intent.getIntExtra(EXTRA_COLOUR, R.color.teal)

        // Fall back to a default line if the user shared an empty story
        findViewById<TextView>(R.id.tvYourStoryText).text =
            if (text.isNullOrBlank()) "Hello from Kinnect 👋" else text
        findViewById<View>(R.id.yourStoryRoot).setBackgroundResource(colour)

        findViewById<TextView>(R.id.btnCloseStory).setOnClickListener { finish() }
    }
}
