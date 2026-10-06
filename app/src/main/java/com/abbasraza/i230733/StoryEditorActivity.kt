package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Lets the user type text on top of their story and change the
 * background colour, then share it.
 */
class StoryEditorActivity : AppCompatActivity() {

    // Background colours the user can cycle through
    private val colours = intArrayOf(R.color.teal, R.color.rust, R.color.dark)
    private var colourIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_editor)

        val canvas = findViewById<EditText>(R.id.etStoryText)

        findViewById<TextView>(R.id.btnCloseEditor).setOnClickListener { finish() }

        findViewById<TextView>(R.id.btnChangeColour).setOnClickListener {
            // % wraps the index back to 0 after the last colour
            colourIndex = (colourIndex + 1) % colours.size
            findViewById<View>(R.id.storyEditorRoot)
                .setBackgroundResource(colours[colourIndex])
        }

        findViewById<TextView>(R.id.btnShareStory).setOnClickListener {
            val intent = Intent(this, YourStoryActivity::class.java)
            intent.putExtra(YourStoryActivity.EXTRA_TEXT, canvas.text.toString())
            intent.putExtra(YourStoryActivity.EXTRA_COLOUR, colours[colourIndex])
            startActivity(intent)
            finish()
        }
    }
}
