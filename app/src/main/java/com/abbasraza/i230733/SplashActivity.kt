package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

/**
 * First screen the user sees. Shows the logo for two seconds,
 * then moves on to the login screen.
 */
class SplashActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    // Kept as a field so we can cancel it if the user leaves early
    private val openLogin = Runnable {
        startActivity(Intent(this, LoginActivity::class.java))
        // finish() so pressing Back on the login screen exits the app
        // instead of returning to the splash screen
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler.postDelayed(openLogin, 2000)
    }

    override fun onDestroy() {
        // Avoid opening the login screen after this activity is already gone
        handler.removeCallbacks(openLogin)
        super.onDestroy()
    }
}
