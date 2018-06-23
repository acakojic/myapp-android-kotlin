package com.example.koja.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.koja.myapplication.R.id.idListView

class SecondActivity : AppCompatActivity() {

    val items = arrayListOf<String>("Jabuka", "Sljiva", "Malina", "Kajsija", "Visnja")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val idTextView: TextView = findViewById(R.id.idTextView)
        val idButton: Button = findViewById(R.id.idButton)

        //get textString from EditActivity
        val intentText = getIntent()
        val textName = intentText.getStringExtra("textName")
        idTextView.text = textName

        idButton.setOnClickListener{
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }

        //ListView
        val idListView: ListView = findViewById(idListView)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        idListView.adapter = adapter

        idListView.setOnItemClickListener { _, _, position, _ ->
            val text = items[position]
            val intent = Intent(this, ItemActivity::class.java)
            intent.putExtra("item", text)
            startActivity(intent)
        }

    }

}
