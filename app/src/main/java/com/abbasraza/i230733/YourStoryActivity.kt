package com.abbasraza.i230733

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class YourStoryActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TEXT = "text"
        const val EXTRA_PHOTO = "photo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_story)

        val text = intent.getStringExtra(EXTRA_TEXT)
        val photo = intent.getIntExtra(EXTRA_PHOTO, R.drawable.img_green)

        findViewById<TextView>(R.id.tvYourStoryText).text =
            if (text.isNullOrBlank()) "Saturday plans" else text
        findViewById<ImageView>(R.id.yourStoryPhoto).setImageResource(photo)

        findViewById<View>(R.id.btnCloseStory).setOnClickListener { finish() }
    }
}
