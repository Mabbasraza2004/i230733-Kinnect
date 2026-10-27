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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SearchActivity : AppCompatActivity() {
    private lateinit var resultList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        resultList = findViewById(R.id.resultList)
        val etSearch = findViewById<EditText>(R.id.etSearch)

        addPerson("Omar Farooq", "Friend · Lives in Karachi", R.color.av_green, canAdd = false)
        addPerson("Omar Siddiqui", "12 mutual friends · FAST-NUCES", R.color.av_indigo, canAdd = true)
        addPerson("Omar Tariq", "Works at Daraz · 3 mutual friends", R.color.av_brown, canAdd = true)
        addPerson("Aisha Khan", "Friend · Lives in Karachi", R.color.av_crimson, canAdd = false)
        addPerson("Lina Marsh", "Friend · 24 mutual friends", R.color.av_purple, canAdd = false)
        addPerson("Hamza Ali", "Friend · Lives in Lahore", R.color.av_brown, canAdd = false)
        addPerson("Sara Iqbal", "8 mutual friends", R.color.av_indigo, canAdd = true)
        addPerson("Zain Raza", "Friend · FAST-NUCES", R.color.av_slate, canAdd = false)

        filter(etSearch.text.toString())

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })

        findViewById<View>(R.id.btnClearSearch).setOnClickListener { etSearch.text.clear() }

        findViewById<View>(R.id.btnJoinGroup).setOnClickListener {
            Toast.makeText(this, "Request to join sent", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }
    }

    private fun addPerson(name: String, info: String, colour: Int, canAdd: Boolean) {
        val row = LayoutInflater.from(this).inflate(R.layout.item_person, resultList, false)
        row.tag = name

        val avatar = row.findViewById<TextView>(R.id.tvPersonAvatar)
        avatar.text = name.split(" ").take(2).joinToString("") { it.first().uppercase() }
        avatar.backgroundTintList = ContextCompat.getColorStateList(this, colour)
        row.findViewById<TextView>(R.id.tvPersonName).text = name
        row.findViewById<TextView>(R.id.tvPersonInfo).text = info

        val btnAdd = row.findViewById<View>(R.id.btnAddPerson)
        if (canAdd) {
            btnAdd.setOnClickListener {
                Toast.makeText(this, "Friend request sent to $name", Toast.LENGTH_SHORT).show()
                btnAdd.visibility = View.GONE
            }
        } else {
            btnAdd.visibility = View.GONE
        }

        row.setOnClickListener {
            val intent = Intent(this, OtherProfileActivity::class.java)
            intent.putExtra(OtherProfileActivity.EXTRA_NAME, name)
            startActivity(intent)
        }
        resultList.addView(row)
    }

    private fun filter(query: String) {
        for (i in 0 until resultList.childCount) {
            val row = resultList.getChildAt(i)
            val matches = (row.tag as String).contains(query.trim(), ignoreCase = true)
            row.visibility = if (matches) View.VISIBLE else View.GONE
        }
    }
}
