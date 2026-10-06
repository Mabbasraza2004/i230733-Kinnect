package com.abbasraza.i230733

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.widget.TextView
import androidx.core.content.ContextCompat

/**
 * Wires up the bottom tab bar (view_bottom_nav.xml) on the five main screens.
 * Putting it in one place avoids copying the same click code into every screen.
 *
 * How the back stack works:
 *  - Home always stays at the bottom of the stack.
 *  - Going from Home to a tab opens that tab on top of Home.
 *  - Going from one tab to another replaces the current tab (finish()),
 *    so pressing Back from any tab always lands on Home.
 */
object BottomNav {

    fun setup(activity: Activity, selectedTabId: Int) {
        // Which screen each tab opens
        val tabs = mapOf(
            R.id.tabHome to HomeActivity::class.java,
            R.id.tabFriends to FriendsActivity::class.java,
            R.id.tabMarketplace to MarketplaceActivity::class.java,
            R.id.tabNotifications to NotificationsActivity::class.java,
            R.id.tabMenu to MenuActivity::class.java
        )

        for ((tabId, screen) in tabs) {
            val tab = activity.findViewById<TextView>(tabId)

            // Highlight the tab for the screen we are on
            if (tabId == selectedTabId) {
                tab.setTextColor(ContextCompat.getColor(activity, R.color.teal))
                tab.setTypeface(tab.typeface, Typeface.BOLD)
                continue // tapping the current tab does nothing
            }

            tab.setOnClickListener {
                val onHome = activity is HomeActivity
                if (screen == HomeActivity::class.java) {
                    // Home is already underneath us, so just close this tab
                    activity.finish()
                } else {
                    activity.startActivity(Intent(activity, screen))
                    if (!onHome) activity.finish()
                }
            }
        }
    }
}
