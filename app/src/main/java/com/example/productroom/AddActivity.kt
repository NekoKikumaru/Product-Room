package com.example.productroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        btnSave.setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(editName.text) || TextUtils.isEmpty(editPrice.text) || TextUtils.isEmpty(editQuantity.text)) {
                Toast.makeText(applicationContext, "Fill in all the blanks", Toast.LENGTH_LONG).show()
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }
            else {
                val product: Array<String> = arrayOf(
                    editName.text.toString(),
                    editPrice.text.toString(),
                    editQuantity.text.toString()
                )
                replyIntent.putExtra(EXTRA_REPLY, product)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "REPLY_DATA"
    }
}
