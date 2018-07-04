package com.example.koja.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_edit.*

class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val idTextView: TextView = findViewById(R.id.idTextView)
        val idButton: Button = findViewById(R.id.idButton)
        val idButton02: Button = findViewById(R.id.idButton02)

        //get textString from SecondActivity
        val intentText = getIntent()
        val item = intentText.getStringExtra("item")
        idTextView.text = item

        idButton.setOnClickListener{
            val intent = Intent(this, DescribeItemActivity::class.java)
            startActivity(intent)
        }

        idButton02.setOnClickListener{
            val intent = Intent(this, CommentsItemActivity::class.java)
            startActivity(intent)
        }

    }
}
