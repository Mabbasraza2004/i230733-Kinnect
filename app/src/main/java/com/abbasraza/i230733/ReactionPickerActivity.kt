package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ReactionPickerActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_REACTION = "reaction"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reaction_picker)

        val row = findViewById<LinearLayout>(R.id.reactionRow)
        for (i in 0 until row.childCount) {
            val reaction = row.getChildAt(i)
            reaction.setOnClickListener {
                val data = Intent().putExtra(EXTRA_REACTION, reaction.contentDescription.toString())
                setResult(RESULT_OK, data)
                finish()
            }
        }
    }
}
