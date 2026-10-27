package com.abbasraza.i230733

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PhotoPickerActivity : AppCompatActivity() {
    private var selectedCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_picker)

        val btnDone = findViewById<TextView>(R.id.btnDone)
        val grid = findViewById<GridLayout>(R.id.photoGrid)

        for (i in 0 until grid.childCount) {
            val tile = grid.getChildAt(i) as FrameLayout
            val circle = tile.getChildAt(1) as TextView

            if (circle.text.contains(":")) continue

            if (circle.text.isNotEmpty()) selectedCount++

            tile.setOnClickListener {
                if (circle.text.isEmpty()) {
                    selectedCount++
                    circle.text = selectedCount.toString()
                    circle.setBackgroundResource(R.drawable.bg_select_number)
                    tile.foreground = getDrawable(R.drawable.bg_selected_tile)
                } else {
                    selectedCount--
                    circle.text = ""
                    circle.setBackgroundResource(R.drawable.bg_select_empty)
                    tile.foreground = null
                }
                btnDone.text = "Next ($selectedCount)"
            }
        }
        btnDone.text = "Next ($selectedCount)"

        findViewById<TextView>(R.id.btnCancel).setOnClickListener { finish() }

        btnDone.setOnClickListener {
            Toast.makeText(this, "$selectedCount photo(s) added", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
