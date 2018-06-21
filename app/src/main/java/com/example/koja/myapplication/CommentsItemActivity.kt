package com.example.koja.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_comments_item.*

class CommentsItemActivity : AppCompatActivity() {

    val comments = arrayListOf<String>("Kvalitetna malina, sve pohvale", "Dobra je malina, nema bolje", "Preporucujem", "Kakav proizvod takav i gazda")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments_item)

        val idEditText: EditText = findViewById(R.id.idEditText)
        val idButton: Button = findViewById(R.id.idButton)

        //ListView
        val idListView: ListView = findViewById(R.id.idListView)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, comments)
        idListView.adapter = adapter

        //Update data
        idButton.setOnClickListener {
            var text: String = idEditText.text.toString()
            comments.add(text)
            adapter.notifyDataSetChanged()
            idEditText.text.clear()
        }

    }
}
