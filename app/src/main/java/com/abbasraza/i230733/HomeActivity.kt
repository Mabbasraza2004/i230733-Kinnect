package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

/**
 * Home feed: top bar, post composer, stories row, posts and the bottom tab bar.
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var btnLike: TextView

    // Remembers if the user has reacted, so a second tap removes the reaction
    private var liked = false

    // Opens the reaction picker and waits for the emoji the user chooses.
    // registerForActivityResult must be created before onCreate finishes.
    private val pickReaction =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val emoji = result.data?.getStringExtra(ReactionPickerActivity.EXTRA_REACTION)
            if (result.resultCode == RESULT_OK && emoji != null) {
                showReaction("$emoji Reacted")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnLike = findViewById(R.id.btnLike)

        // Short tap = simple like / unlike
        btnLike.setOnClickListener {
            if (liked) clearReaction() else showReaction("♥ Liked")
        }

        // Long press = choose a reaction (like Facebook)
        btnLike.setOnLongClickListener {
            pickReaction.launch(Intent(this, ReactionPickerActivity::class.java))
            true
        }

        findViewById<TextView>(R.id.btnComment).setOnClickListener {
            open(CommentsActivity::class.java)
        }

        findViewById<TextView>(R.id.btnShare).setOnClickListener {
            Toast.makeText(this, "Post shared", Toast.LENGTH_SHORT).show()
        }

        // Stories
        findViewById<TextView>(R.id.btnCreateStory).setOnClickListener {
            open(CameraActivity::class.java)
        }
        openStoryOnClick(R.id.btnStoryOmar, "Omar Farooq")
        openStoryOnClick(R.id.btnStoryAisha, "Aisha Khan")
        openStoryOnClick(R.id.btnStoryHamza, "Hamza Ali")

        // New post
        findViewById<TextView>(R.id.btnCreatePostField).setOnClickListener {
            open(CreatePostActivity::class.java)
        }

        // Messenger
        findViewById<TextView>(R.id.btnChats).setOnClickListener {
            open(ChatsActivity::class.java)
        }

        // Own profile
        findViewById<TextView>(R.id.btnMyAvatar).setOnClickListener {
            open(ProfileActivity::class.java)
        }

        BottomNav.setup(this, R.id.tabHome)
    }

    private fun showReaction(label: String) {
        liked = true
        btnLike.text = label
        btnLike.setTextColor(ContextCompat.getColor(this, R.color.rust))
    }

    private fun clearReaction() {
        liked = false
        btnLike.text = "♡ Like"
        btnLike.setTextColor(ContextCompat.getColor(this, R.color.ink_muted))
    }

    private fun openStoryOnClick(viewId: Int, name: String) {
        findViewById<TextView>(viewId).setOnClickListener {
            val intent = Intent(this, StoryViewerActivity::class.java)
            intent.putExtra(StoryViewerActivity.EXTRA_NAME, name)
            startActivity(intent)
        }
    }

    private fun open(screen: Class<*>) {
        startActivity(Intent(this, screen))
    }
}
