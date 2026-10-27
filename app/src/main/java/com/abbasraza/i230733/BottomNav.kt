package com.abbasraza.i230733

import android.app.Activity
import android.content.Intent
import android.view.View

object BottomNav {
    fun setup(activity: Activity, selectedTabId: Int) {
        val tabs = mapOf(
            R.id.tabHome to HomeActivity::class.java,
            R.id.tabFriends to FriendsActivity::class.java,
            R.id.tabMarketplace to MarketplaceActivity::class.java,
            R.id.tabNotifications to NotificationsActivity::class.java,
            R.id.tabMenu to MenuActivity::class.java
        )

        for ((tabId, screen) in tabs) {
            val tab = activity.findViewById<View>(tabId)

            if (tabId == selectedTabId) {
                tab.isSelected = true
                continue
            }

            tab.setOnClickListener {
                val onHome = activity is HomeActivity
                if (screen == HomeActivity::class.java) {
                    activity.finish()
                } else {
                    activity.startActivity(Intent(activity, screen))
                    if (!onHome) activity.finish()
                }
            }
        }

        activity.findViewById<View>(R.id.btnSearch).setOnClickListener {
            activity.startActivity(Intent(activity, SearchActivity::class.java))
        }
        activity.findViewById<View>(R.id.btnChats).setOnClickListener {
            activity.startActivity(Intent(activity, ChatsActivity::class.java))
        }
    }
}
