package com.abbasraza.i230733

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MarketplaceActivity : AppCompatActivity() {
    private lateinit var grid: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)
        findViewById<TextView>(R.id.tvHeaderTitle).text = "Marketplace"

        grid = findViewById(R.id.itemGrid)

        addItem("Rs 45,000", "Road bike, 21-speed", "Clifton", R.drawable.img_green, justListed = true)
        addItem("Rs 8,500", "Rattan armchair", "DHA Phase 6", R.drawable.img_rose, justListed = false)
        addItem("Rs 15,000", "Solid wood study desk", "Gulshan", R.drawable.img_blue, justListed = false)
        addItem("Rs 3,200", "Ceramic plant pots, set of 3", "Saddar", R.drawable.img_sand, justListed = true)

        findViewById<View>(R.id.btnSell).setOnClickListener {
            Toast.makeText(this, "Selling coming soon", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.btnCategories).setOnClickListener {
            Toast.makeText(this, "Categories coming soon", Toast.LENGTH_SHORT).show()
        }

        BottomNav.setup(this, R.id.tabMarketplace)
    }

    private fun addItem(price: String, title: String, place: String, photo: Int, justListed: Boolean) {
        val card = LayoutInflater.from(this).inflate(R.layout.item_marketplace, grid, false)
        card.findViewById<TextView>(R.id.tvItemPrice).text = price
        card.findViewById<TextView>(R.id.tvItemTitle).text = title
        card.findViewById<TextView>(R.id.tvItemPlace).text = place
        card.findViewById<ImageView>(R.id.itemPhoto).setImageResource(photo)
        if (justListed) {
            card.findViewById<View>(R.id.tvJustListed).visibility = View.VISIBLE
        }
        card.setOnClickListener {
            Toast.makeText(this, "Message seller about \"$title\"", Toast.LENGTH_SHORT).show()
        }
        grid.addView(card)
    }
}
