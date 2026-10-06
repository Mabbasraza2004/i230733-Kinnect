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

    private fun open(screen: Class<*>) {
        startActivity(Intent(this, screen))
    }
}
