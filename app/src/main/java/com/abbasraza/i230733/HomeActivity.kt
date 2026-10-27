package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class HomeActivity : AppCompatActivity() {
    private lateinit var btnLike: TextView

    private var liked = false

    private val pickReaction =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val reaction = result.data?.getStringExtra(ReactionPickerActivity.EXTRA_REACTION)
            if (result.resultCode == RESULT_OK && reaction != null) {
                showReaction(reaction)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val title = findViewById<TextView>(R.id.tvHeaderTitle)
        title.text = "kinnect"
        title.setTextColor(ContextCompat.getColor(this, R.color.teal))
        title.textSize = 30f

        btnLike = findViewById(R.id.btnLike)

        btnLike.setOnClickListener {
            if (liked) clearReaction() else showReaction("Liked")
        }

        btnLike.setOnLongClickListener {
            pickReaction.launch(Intent(this, ReactionPickerActivity::class.java))
            true
        }

        findViewById<View>(R.id.btnComment).setOnClickListener {
            open(CommentsActivity::class.java)
        }

        findViewById<View>(R.id.btnShare).setOnClickListener {
            Toast.makeText(this, "Post shared", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnCreateStory).setOnClickListener {
            open(CameraActivity::class.java)
        }
        openStoryOnClick(R.id.btnStoryOmar, "Omar Farooq")
        openStoryOnClick(R.id.btnStorySara, "Sara Iqbal")
        openStoryOnClick(R.id.btnStoryHamza, "Hamza Ali")

        findViewById<View>(R.id.btnCreatePostField).setOnClickListener {
            open(CreatePostActivity::class.java)
        }

        findViewById<View>(R.id.btnMyAvatar).setOnClickListener {
            open(ProfileActivity::class.java)
        }

        BottomNav.setup(this, R.id.tabHome)
    }

    private fun showReaction(label: String) {
        liked = true
        btnLike.text = label
        btnLike.setTextColor(ContextCompat.getColor(this, R.color.teal))
    }

    private fun clearReaction() {
        liked = false
        btnLike.text = "Like"
        btnLike.setTextColor(ContextCompat.getColor(this, R.color.ink_muted))
    }

    private fun openStoryOnClick(viewId: Int, name: String) {
        findViewById<View>(viewId).setOnClickListener {
            val intent = Intent(this, StoryViewerActivity::class.java)
            intent.putExtra(StoryViewerActivity.EXTRA_NAME, name)
            startActivity(intent)
        }
    }

    private fun open(screen: Class<*>) {
        startActivity(Intent(this, screen))
    }
}
