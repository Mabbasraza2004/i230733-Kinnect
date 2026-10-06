package com.abbasraza.i230733

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Comments on a post. Shows a few sample comments, and lets the user
 * add their own to the bottom of the list.
 */
class CommentsActivity : AppCompatActivity() {

    private lateinit var commentList: LinearLayout
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        commentList = findViewById(R.id.commentList)
        scrollView = findViewById(R.id.scrollComments)
        val etComment = findViewById<EditText>(R.id.etComment)

        // Sample comments so the screen isn't empty
        addComment("HA", "Hamza Ali", "Looks amazing! Which trail did you take?", "1h")
        addComment("AK", "Aisha Khan", "Take me with you next time 😄", "45m")
        addComment("SN", "Sara Noor", "Beautiful view", "20m")

        findViewById<TextView>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<TextView>(R.id.btnSend).setOnClickListener {
            val text = etComment.text.toString().trim()
            if (text.isNotEmpty()) {
                addComment("AR", "Abbas Raza", text, "Just now")
                etComment.text.clear()
                // Wait until the new row is laid out, then scroll to it
                scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
            }
        }
    }

    /** Inflates one comment row (item_comment.xml), fills it in and adds it to the list. */
    private fun addComment(initials: String, name: String, text: String, time: String) {
        val row = LayoutInflater.from(this).inflate(R.layout.item_comment, commentList, false)
        row.findViewById<TextView>(R.id.tvCommentAvatar).text = initials
        row.findViewById<TextView>(R.id.tvCommentName).text = name
        row.findViewById<TextView>(R.id.tvCommentText).text = text
        row.findViewById<TextView>(R.id.tvCommentTime).text = time
        commentList.addView(row)
    }
}
