package com.abbasraza.i230733

import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Gallery grid. Tapping a tile selects / unselects it and the header
 * shows how many are selected. "Done" returns to the post screen.
 */
class PhotoPickerActivity : AppCompatActivity() {

    private var selectedCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_picker)

        val tvSelected = findViewById<TextView>(R.id.tvSelectedCount)
        val grid = findViewById<GridLayout>(R.id.photoGrid)

        // Every tile in the grid is a TextView; a ✓ in its text means "selected"
        for (i in 0 until grid.childCount) {
            val tile = grid.getChildAt(i) as TextView
            tile.setOnClickListener {
                if (tile.text.isEmpty()) {
                    tile.text = "✓"
                    selectedCount++
                } else {
                    tile.text = ""
                    selectedCount--
                }
                tvSelected.text = "$selectedCount selected"
            }
        }

        findViewById<TextView>(R.id.btnCancel).setOnClickListener { finish() }

        findViewById<TextView>(R.id.btnDone).setOnClickListener {
            Toast.makeText(this, "$selectedCount photo(s) added", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
