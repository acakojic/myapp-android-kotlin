package com.example.koja.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.koja.myapplication.R.id.idButton
import com.example.koja.myapplication.R.id.idListView

import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    val items = arrayListOf<String>("Jabuka", "Sljiva", "Malina", "Kajsija", "Visnja")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val idTextView: TextView = findViewById(R.id.idTextView)
        val idButton: Button = findViewById(R.id.idButton)

        //get textString from MainActivity
        val intentText = getIntent()
        val textName = intentText.getStringExtra("textName")
        idTextView.text = textName

        idButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //ListView
        val idListView: ListView = findViewById(idListView)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        idListView.adapter = adapter

        idListView.setOnItemClickListener { parent, view, position, id ->
            val text = items[position]
            val intent = Intent(this, ItemActivity::class.java)
            intent.putExtra("item", text)
            startActivity(intent)
        }

    }

}
