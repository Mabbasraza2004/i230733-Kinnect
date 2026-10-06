package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

/**
 * The logged-in user's own profile. "Edit profile" opens EditProfileActivity,
 * and whatever is saved there is shown here when the user comes back.
 */
class ProfileActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvBio: TextView

    // Receives the new name and bio from EditProfileActivity
    private val editProfile =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data = result.data
            if (result.resultCode == RESULT_OK && data != null) {
                tvName.text = data.getStringExtra(EditProfileActivity.EXTRA_NAME)
                tvBio.text = data.getStringExtra(EditProfileActivity.EXTRA_BIO)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvName = findViewById(R.id.tvProfileName)
        tvBio = findViewById(R.id.tvProfileBio)

        findViewById<TextView>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<TextView>(R.id.btnEditProfile).setOnClickListener {
            // Send the current values so the edit form starts filled in
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra(EditProfileActivity.EXTRA_NAME, tvName.text.toString())
            intent.putExtra(EditProfileActivity.EXTRA_BIO, tvBio.text.toString())
            editProfile.launch(intent)
        }
    }
}
