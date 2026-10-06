package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Camera screen used to start a story. The preview is only a dark
 * placeholder (no camera permission needed); the shutter button
 * moves on to the story editor.
 */
class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        findViewById<TextView>(R.id.btnCloseCamera).setOnClickListener { finish() }

        findViewById<TextView>(R.id.btnShutter).setOnClickListener {
            startActivity(Intent(this, StoryEditorActivity::class.java))
            // Close the camera so Back from the editor returns to where we started
            finish()
        }
    }
}
