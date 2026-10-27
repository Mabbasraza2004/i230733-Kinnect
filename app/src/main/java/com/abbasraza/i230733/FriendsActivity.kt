package com.abbasraza.i230733

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class FriendsActivity : AppCompatActivity() {
    private lateinit var requestList: LinearLayout
    private lateinit var tvCount: TextView

    private var pendingRequests = 12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        findViewById<TextView>(R.id.tvHeaderTitle).text = "Friends"

        requestList = findViewById(R.id.requestList)
        tvCount = findViewById(R.id.tvRequestCount)

        addRequest("Sara Iqbal", "8 mutual friends", "3d", R.color.av_indigo)
        addRequest("Bilal Ahmed", "14 mutual friends", "1w", R.color.av_purple)
        addRequest("Noor Fatima", "3 mutual friends", "2w", R.color.av_green)
        addRequest("Daniyal Shah", "21 mutual friends", "3w", R.color.av_crimson)
        updateCount()

        BottomNav.setup(this, R.id.tabFriends)
    }

    private fun addRequest(name: String, mutual: String, time: String, colour: Int) {
        val row = LayoutInflater.from(this).inflate(R.layout.item_friend_request, requestList, false)
        val avatar = row.findViewById<TextView>(R.id.tvRequestAvatar)
        avatar.text = name.split(" ").take(2).joinToString("") { it.first().uppercase() }
        avatar.backgroundTintList = ContextCompat.getColorStateList(this, colour)
        row.findViewById<TextView>(R.id.tvRequestName).text = name
        row.findViewById<TextView>(R.id.tvRequestMutual).text = mutual
        row.findViewById<TextView>(R.id.tvRequestTime).text = time

        row.findViewById<TextView>(R.id.btnConfirm).setOnClickListener {
            Toast.makeText(this, "You and $name are now friends", Toast.LENGTH_SHORT).show()
            removeRequest(row)
        }
        row.findViewById<TextView>(R.id.btnDelete).setOnClickListener {
            removeRequest(row)
        }
        requestList.addView(row)
    }

    private fun removeRequest(row: View) {
        requestList.removeView(row)
        pendingRequests--
        updateCount()
    }

    private fun updateCount() {
        tvCount.text = pendingRequests.toString()
    }
}
