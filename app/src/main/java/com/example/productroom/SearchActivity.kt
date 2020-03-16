package com.example.productroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        btnName.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editName.text)) {
                editName.setError("Please fill the word")
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }
            else {
                val name = editName.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, name)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        btnPrice.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editMinPrice.text)) {
                editMinPrice.setError("Please fill the word")
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }
            else if (TextUtils.isEmpty(editMaxPrice.text)) {
                editMaxPrice.setError("Please fill the word")
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }
            else {
                val minPrice = editMinPrice.text.toString()
                val maxPrice = editMaxPrice.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, arrayOf(minPrice, maxPrice))
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "REPLY_DATA"
    }
}
