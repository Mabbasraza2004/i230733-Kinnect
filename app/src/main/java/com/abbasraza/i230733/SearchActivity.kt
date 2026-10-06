package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Search for people. The list filters as the user types; tapping
 * a person opens their profile.
 */
class SearchActivity : AppCompatActivity() {

    private val people = listOf(
        "Omar Farooq", "Aisha Khan", "Hamza Ali", "Sara Noor",
        "Bilal Ahmed", "Daniyal Shah", "Noor Fatima"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val resultList = findViewById<LinearLayout>(R.id.resultList)
        val etSearch = findViewById<EditText>(R.id.etSearch)

        // Create one row per person once; filtering just hides/shows them
        for (name in people) {
            val row = LayoutInflater.from(this).inflate(R.layout.item_chat_row, resultList, false)
            row.tag = name // remember the name so we can filter on it later
            row.findViewById<TextView>(R.id.tvChatAvatar).text =
                name.split(" ").take(2).joinToString("") { it.first().uppercase() }
            row.findViewById<TextView>(R.id.tvChatName).text = name
            row.findViewById<TextView>(R.id.tvChatPreview).text = "Friend · Islamabad"
            row.setOnClickListener {
                val intent = Intent(this, OtherProfileActivity::class.java)
                intent.putExtra(OtherProfileActivity.EXTRA_NAME, name)
                startActivity(intent)
            }
            resultList.addView(row)
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                for (i in 0 until resultList.childCount) {
                    val row = resultList.getChildAt(i)
                    val matches = (row.tag as String).contains(query, ignoreCase = true)
                    row.visibility = if (matches) View.VISIBLE else View.GONE
                }
            }
        })

        findViewById<TextView>(R.id.btnBack).setOnClickListener { finish() }
    }
}
