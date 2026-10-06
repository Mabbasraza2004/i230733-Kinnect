package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Small pop-up (dialog theme in the manifest) with a row of reactions.
 * The chosen emoji is sent back to HomeActivity with setResult().
 */
class ReactionPickerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REACTION = "reaction"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reaction_picker)

        // Every child of the row is one emoji TextView, so loop over them
        // instead of writing a click listener for each one by hand
        val row = findViewById<LinearLayout>(R.id.reactionRow)
        for (i in 0 until row.childCount) {
            val emojiView = row.getChildAt(i) as TextView
            emojiView.setOnClickListener {
                val data = Intent().putExtra(EXTRA_REACTION, emojiView.text.toString())
                setResult(RESULT_OK, data)
                finish()
            }
        }
    }
}
