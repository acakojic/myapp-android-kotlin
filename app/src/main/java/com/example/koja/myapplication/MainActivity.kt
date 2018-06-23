package com.example.koja.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity() {

    var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val elements = arrayOf("Odabir - Klikni Ovde", "Prodaja - 23", "Kupovina - 56")
        val buying = arrayOf("Malina", "Kupina", "Tresnja", "Visnja", "Jabuka", "Sljiva", "Kajsija")
        val selling = arrayOf("Magarac", "Konj", "Krava", "Ovca", "Kokoska")

        val idSpinner: Spinner = findViewById(R.id.idSpinner)
        val idListView: ListView = findViewById(R.id.idListView)
        val idTextView: TextView = findViewById(R.id.idTextView)

        //spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, elements)
        idSpinner.adapter = adapter

        //listview
        val adapter02 = ArrayAdapter(this, android.R.layout.simple_list_item_1, elements)
        adapter02.setDropDownViewResource(android.R.layout.simple_list_item_1)
        idListView.adapter = adapter02

        idSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                idTextView.text = "onNothingSelected"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 1) {
                    idListView.setVisibility(View.VISIBLE)
                    setupListView(idListView, idTextView, selling, elements, position)
                    number = 1
                }
                else if (position == 2){
                    idListView.setVisibility(View.VISIBLE)
                    setupListView(idListView, idTextView, buying, elements, position)
                    number = 2
                }
                else{
                    idListView.setVisibility(View.INVISIBLE)
                    number = 0
                }
            }
        }

        idListView.setOnItemClickListener { _, _, position, _ ->
            if (number == 1) {
                setupOnClickListView(selling, position)
            }
            else if (number == 2){
                setupOnClickListView(buying, position)
            }
            else{
                //number = 0
            }
        }

    }

    fun setupListView (idListView: ListView, idTextView: TextView, arrayList: Array<String>, elements: Array<String>, position: Int){
        idListView.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, arrayList)
        idTextView.text = elements[position]
    }

    fun setupOnClickListView(arrayList: Array<String>, position: Int){
        val text = arrayList[position]
        val intent = Intent(this@MainActivity, ItemActivity::class.java)
        intent.putExtra("item", text)
        startActivity(intent)
    }
}
