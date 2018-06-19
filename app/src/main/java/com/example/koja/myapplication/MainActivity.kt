package com.example.koja.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    lateinit var idTextView: TextView
    lateinit var idEditText: EditText
    lateinit var idButton: Button
    lateinit var idButton02: Button
    lateinit var idListView: ListView
    lateinit var messagesDBHelper: MessagesDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idTextView = findViewById(R.id.idTextView)
        idEditText = findViewById(R.id.idEditText)
        idButton = findViewById(R.id.idButton)
        idListView = findViewById(R.id.idListView)
        var handler: MessagesDBHelper
        handler = MessagesDBHelper(this@MainActivity)

        idButton.setOnClickListener {
            val text = idEditText.text.toString()

            handler.insertMessages(text) //inserted into database
            Toast.makeText(this@MainActivity, "Inserted Successfully", Toast.LENGTH_SHORT).show()
        }

        //idButton02.setOnClickListener{}
        var list: ArrayList<String> = handler.selectMessages()
        var adp: ArrayAdapter<String>

        adp = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, list)
        idListView.adapter = adp

    }
}

