package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class StoryEditorActivity : AppCompatActivity() {
    private val photos = intArrayOf(R.drawable.img_green, R.drawable.img_sea, R.drawable.img_rose)
    private var photoIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_editor)

        val sticker = findViewById<EditText>(R.id.etStoryText)
        val photo = findViewById<ImageView>(R.id.storyPhoto)

        findViewById<View>(R.id.btnCloseEditor).setOnClickListener { finish() }

        findViewById<View>(R.id.btnChangeColour).setOnClickListener {
            photoIndex = (photoIndex + 1) % photos.size
            photo.setImageResource(photos[photoIndex])
        }

        findViewById<View>(R.id.btnShareStory).setOnClickListener {
            val intent = Intent(this, YourStoryActivity::class.java)
            intent.putExtra(YourStoryActivity.EXTRA_TEXT, sticker.text.toString())
            intent.putExtra(YourStoryActivity.EXTRA_PHOTO, photos[photoIndex])
            startActivity(intent)
            finish()
        }
    }
}
