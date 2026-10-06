package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Friend requests. "Confirm" and "Delete" both remove the request
 * from the list, and the counter in the header updates.
 */
class FriendsActivity : AppCompatActivity() {

    private lateinit var requestList: LinearLayout
    private lateinit var tvCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        findViewById<TextView>(R.id.tvHeaderTitle).text = "Friends"

        requestList = findViewById(R.id.requestList)
        tvCount = findViewById(R.id.tvRequestCount)

        addRequest("Sara Noor", "4 mutual friends")
        addRequest("Bilal Ahmed", "12 mutual friends")
        addRequest("Daniyal Shah", "1 mutual friend")
        addRequest("Noor Fatima", "7 mutual friends")
        updateCount()

        findViewById<TextView>(R.id.btnSearch).setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        BottomNav.setup(this, R.id.tabFriends)
    }

    /** Adds one request row (item_friend_request.xml) with working buttons. */
    private fun addRequest(name: String, mutual: String) {
        val row = LayoutInflater.from(this).inflate(R.layout.item_friend_request, requestList, false)
        row.findViewById<TextView>(R.id.tvRequestAvatar).text =
            name.split(" ").take(2).joinToString("") { it.first().uppercase() }
        row.findViewById<TextView>(R.id.tvRequestName).text = name
        row.findViewById<TextView>(R.id.tvRequestMutual).text = mutual

        row.findViewById<TextView>(R.id.btnConfirm).setOnClickListener {
            Toast.makeText(this, "You and $name are now friends", Toast.LENGTH_SHORT).show()
            requestList.removeView(row)
            updateCount()
        }
        row.findViewById<TextView>(R.id.btnDelete).setOnClickListener {
            requestList.removeView(row)
            updateCount()
        }
        requestList.addView(row)
    }

    private fun updateCount() {
        tvCount.text = "Friend requests (${requestList.childCount})"
    }
}
