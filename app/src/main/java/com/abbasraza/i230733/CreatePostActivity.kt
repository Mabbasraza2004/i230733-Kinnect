package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * "Create post" screen with a text box and options to add a photo
 * or open the camera.
 */
class CreatePostActivity : AppCompatActivity() {

    private lateinit var btnPost: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        val etPost = findViewById<EditText>(R.id.etPost)
        btnPost = findViewById(R.id.btnPost)
        setPostEnabled(false)

        // Only allow posting once something has been typed
        etPost.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                setPostEnabled(!s.isNullOrBlank())
            }
        })

        findViewById<TextView>(R.id.btnClose).setOnClickListener { finish() }

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

        findViewById<TextView>(R.id.rowTagPeople).setOnClickListener {
            Toast.makeText(this, "Tagging coming soon", Toast.LENGTH_SHORT).show()
        }
    }

    /** Greys the Post button out when there is nothing to post. */
    private fun setPostEnabled(enabled: Boolean) {
        btnPost.isEnabled = enabled
        btnPost.alpha = if (enabled) 1f else 0.4f
    }
}
