package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "name"
        const val EXTRA_BIO = "bio"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val etName = findViewById<EditText>(R.id.etEditName)
        val etBio = findViewById<EditText>(R.id.etEditBio)

        etName.setText(intent.getStringExtra(EXTRA_NAME))
        etBio.setText(intent.getStringExtra(EXTRA_BIO))

        findViewById<TextView>(R.id.btnCancel).setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        findViewById<TextView>(R.id.btnSave).setOnClickListener {
            val name = etName.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Name can't be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val result = Intent()
            result.putExtra(EXTRA_NAME, name)
            result.putExtra(EXTRA_BIO, etBio.text.toString().trim())
            setResult(RESULT_OK, result)
            finish()
        }
    }
}
