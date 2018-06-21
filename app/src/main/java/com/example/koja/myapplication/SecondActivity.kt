package com.example.koja.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.example.koja.myapplication.R.id.idButton

import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val idTextView: TextView = findViewById(R.id.idTextView)
        val idButton: Button = findViewById(R.id.idButton)


        //get textString from MainComponent

        val intentText = getIntent()
        val textName = intentText.getStringExtra("textName")
        idTextView.text = textName

        idButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
