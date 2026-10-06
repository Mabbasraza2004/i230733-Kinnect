package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Menu tab: link to the user's profile, a few shortcuts, and Log out.
 */
class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        findViewById<TextView>(R.id.tvHeaderTitle).text = "Menu"

        findViewById<TextView>(R.id.rowProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Shortcuts that are not part of this assignment just show a message
        val shortcuts = intArrayOf(R.id.rowSaved, R.id.rowMemories, R.id.rowGroups, R.id.rowSettings)
        for (id in shortcuts) {
            val row = findViewById<TextView>(id)
            row.setOnClickListener {
                // The label is on the second line of the text, under the icon
                val label = row.text.lines().last()
                Toast.makeText(this, "$label coming soon", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<TextView>(R.id.btnLogout).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            // NEW_TASK + CLEAR_TASK wipes every open screen, so after logging
            // out the Back button can't return to the home feed
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        findViewById<TextView>(R.id.btnSearch).setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        BottomNav.setup(this, R.id.tabMenu)
    }
}
