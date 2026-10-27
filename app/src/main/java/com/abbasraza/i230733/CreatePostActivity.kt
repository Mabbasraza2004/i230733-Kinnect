package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreatePostActivity : AppCompatActivity() {
    private lateinit var btnPost: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        val etPost = findViewById<EditText>(R.id.etPost)
        btnPost = findViewById(R.id.btnPost)
        setPostEnabled(etPost.text.isNotBlank())

        etPost.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                setPostEnabled(!s.isNullOrBlank())
            }
        })

        findViewById<View>(R.id.btnClose).setOnClickListener { finish() }

        btnPost.setOnClickListener {
            Toast.makeText(this, "Posted!", Toast.LENGTH_SHORT).show()
            finish()
        }

        findViewById<TextView>(R.id.rowPhotoVideo).setOnClickListener {
            startActivity(Intent(this, PhotoPickerActivity::class.java))
        }

        findViewById<TextView>(R.id.rowCamera).setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        val comingSoon = intArrayOf(
            R.id.rowTagPeople, R.id.rowFeeling, R.id.rowCheckIn, R.id.rowLive, R.id.rowEvent
        )
        for (id in comingSoon) {
            val row = findViewById<TextView>(id)
            row.setOnClickListener {
                Toast.makeText(this, "${row.text} coming soon", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setPostEnabled(enabled: Boolean) {
        btnPost.isEnabled = enabled
        btnPost.alpha = if (enabled) 1f else 0.4f
    }
}
