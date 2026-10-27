package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var tvName: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvBio: TextView

    private val editProfile =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data = result.data
            if (result.resultCode == RESULT_OK && data != null) {
                tvName.text = data.getStringExtra(EditProfileActivity.EXTRA_NAME)
                tvTitle.text = tvName.text
                tvBio.text = data.getStringExtra(EditProfileActivity.EXTRA_BIO)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvName = findViewById(R.id.tvProfileName)
        tvTitle = findViewById(R.id.tvProfileTitle)
        tvBio = findViewById(R.id.tvProfileBio)

        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<View>(R.id.btnAddToStory).setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        findViewById<TextView>(R.id.btnEditProfile).setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra(EditProfileActivity.EXTRA_NAME, tvName.text.toString())
            intent.putExtra(EditProfileActivity.EXTRA_BIO, tvBio.text.toString())
            editProfile.launch(intent)
        }
    }
}
