package com.abbasraza.i230733

import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Sign-up form. We only check that the required fields are filled in,
 * because there is no server to create a real account.
 */
class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val etFirstName = findViewById<EditText>(R.id.etFirstName)
        val etEmail = findViewById<EditText>(R.id.etSignupEmail)
        val etPassword = findViewById<EditText>(R.id.etSignupPassword)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)

        findViewById<TextView>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<TextView>(R.id.btnSignup).setOnClickListener {
            // checkedRadioButtonId is -1 when no option is selected
            if (etFirstName.text.isBlank() || etEmail.text.isBlank() ||
                etPassword.text.isBlank() || rgGender.checkedRadioButtonId == -1
            ) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Account created, please log in", Toast.LENGTH_SHORT).show()
                finish() // back to the login screen
            }
        }
    }
}
