package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        findViewById<TextView>(R.id.tvHeaderTitle).text = "Menu"

        findViewById<View>(R.id.rowProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<View>(R.id.rowFriends).setOnClickListener {
            findViewById<View>(R.id.tabFriends).performClick()
        }
        findViewById<View>(R.id.rowMarketplace).setOnClickListener {
            findViewById<View>(R.id.tabMarketplace).performClick()
        }

        comingSoon(R.id.rowMemories, "Memories")
        comingSoon(R.id.rowSaved, "Saved")
        comingSoon(R.id.rowGroups, "Groups")
        comingSoon(R.id.rowEvents, "Events")
        comingSoon(R.id.rowHelp, "Help & support")
        comingSoon(R.id.rowSettings, "Settings & privacy")
        comingSoon(R.id.rowAlsoFrom, "Also from SMD")

        findViewById<View>(R.id.btnLogout).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        BottomNav.setup(this, R.id.tabMenu)
    }

    private fun comingSoon(viewId: Int, label: String) {
        findViewById<View>(viewId).setOnClickListener {
            Toast.makeText(this, "$label coming soon", Toast.LENGTH_SHORT).show()
        }
    }
}
