package com.abbasraza.i230733

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class CommentsActivity : AppCompatActivity() {
    private lateinit var commentList: LinearLayout
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        commentList = findViewById(R.id.commentList)
        scrollView = findViewById(R.id.scrollComments)
        val etComment = findViewById<EditText>(R.id.etComment)

        val aisha = addComment("AK", R.color.av_crimson, "Aisha Khan",
            "That sky is unreal. Which part of the beach was this?", "2h")
        showReactions(aisha, "24")
        aisha.findViewById<TextView>(R.id.btnCommentLike)
            .setTextColor(ContextCompat.getColor(this, R.color.teal))

        val lina = addComment("LM", R.color.av_purple, "Lina Marsh",
            "Near the old pier, right before the rain started!", "1h", reply = true)
        showReactions(lina, "3")

        addComment("ZR", R.color.av_slate, "Zain Raza",
            "@Lina Marsh next time invite the rest of us", "45m", reply = true)
        addMoreRepliesLink("View 6 more replies")

        val hamza = addComment("HA", R.color.av_brown, "Hamza Ali", "Same spot, one year ago:", "1h")
        hamza.findViewById<View>(R.id.commentPhoto).visibility = View.VISIBLE

        addComment("MC", R.color.av_olive, "Maya Chen", "Framing this one.", "30m")

        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<View>(R.id.btnCancelReply).setOnClickListener {
            findViewById<View>(R.id.replyingBar).visibility = View.GONE
        }

        findViewById<View>(R.id.btnSend).setOnClickListener {
            val text = etComment.text.toString().trim()
            if (text.isNotEmpty()) {
                addComment("JW", R.color.teal, "Jacob West", text, "Just now")
                etComment.text.clear()
                scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
            }
        }
    }

    private fun addComment(
        initials: String, colour: Int, name: String, text: String, time: String,
        reply: Boolean = false
    ): View {
        val row = LayoutInflater.from(this).inflate(R.layout.item_comment, commentList, false)
        val avatar = row.findViewById<TextView>(R.id.tvCommentAvatar)
        avatar.text = initials
        avatar.backgroundTintList = ContextCompat.getColorStateList(this, colour)
        row.findViewById<TextView>(R.id.tvCommentName).text = name
        row.findViewById<TextView>(R.id.tvCommentText).text = text
        row.findViewById<TextView>(R.id.tvCommentTime).text = time

        if (reply) {
            val dp = resources.displayMetrics.density
            row.setPadding((52 * dp).toInt(), row.paddingTop, 0, row.paddingBottom)
            avatar.layoutParams.width = (34 * dp).toInt()
            avatar.layoutParams.height = (34 * dp).toInt()
        }

        commentList.addView(row)
        return row
    }

    private fun showReactions(row: View, count: String) {
        val reacts = row.findViewById<TextView>(R.id.tvCommentReacts)
        reacts.text = count
        reacts.visibility = View.VISIBLE
    }

    private fun addMoreRepliesLink(text: String) {
        val link = LayoutInflater.from(this)
            .inflate(R.layout.item_more_replies, commentList, false) as TextView
        link.text = text
        commentList.addView(link)
    }
}
