package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<TextView>(R.id.btnLogin).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        findViewById<TextView>(R.id.btnCreateAccount).setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        findViewById<TextView>(R.id.btnForgotPassword).setOnClickListener {
            Toast.makeText(this, "Password reset link sent", Toast.LENGTH_SHORT).show()
        }
    }
}
