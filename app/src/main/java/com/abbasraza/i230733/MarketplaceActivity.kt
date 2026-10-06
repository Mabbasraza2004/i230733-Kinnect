package com.abbasraza.i230733

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Marketplace: a two-column grid of items for sale.
 */
class MarketplaceActivity : AppCompatActivity() {

    private lateinit var grid: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)
        findViewById<TextView>(R.id.tvHeaderTitle).text = "Marketplace"

        grid = findViewById(R.id.itemGrid)

        addItem("Rs 18,000", "Mountain bike", R.color.teal)
        addItem("Rs 7,500", "Study desk", R.color.rust)
        addItem("Rs 12,000", "Armchair", R.color.dark)
        addItem("Rs 1,200", "Set of plant pots", R.color.teal_light)
        addItem("Rs 25,000", "Used iPhone 11", R.color.chip)
        addItem("Rs 3,000", "Cricket bat", R.color.teal)

        findViewById<TextView>(R.id.btnSearch).setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        BottomNav.setup(this, R.id.tabMarketplace)
    }

    /** Adds one listing card (item_marketplace.xml) to the grid. */
    private fun addItem(price: String, title: String, photoColour: Int) {
        val card = LayoutInflater.from(this).inflate(R.layout.item_marketplace, grid, false)
        card.findViewById<TextView>(R.id.tvItemPrice).text = price
        card.findViewById<TextView>(R.id.tvItemTitle).text = title
        card.findViewById<View>(R.id.itemPhoto).setBackgroundResource(photoColour)
        card.setOnClickListener {
            Toast.makeText(this, "Message seller about \"$title\"", Toast.LENGTH_SHORT).show()
        }
        grid.addView(card)
    }
}
