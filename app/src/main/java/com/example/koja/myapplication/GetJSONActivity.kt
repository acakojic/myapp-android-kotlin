package com.example.koja.myapplication

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.RecyclerView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray

class GetJSONActivity : AppCompatActivity() {

    lateinit var idButton: Button
    lateinit var idRecyclerView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_json)

        idButton = findViewById(R.id.idButton)
        idRecyclerView = findViewById(R.id.idRecyclerView)

        val url = "http://12880bb0.ngrok.io/post-data"

        var arr = arrayListOf<String>()

        idButton.setOnClickListener {
            val stringRequest = StringRequest(Request.Method.GET, url,
                    Response.Listener { response ->
                        // Display the first 500 characters of the response string.
                        //idTextView.text = "Response is: $postValue"
                        println("DATA =================== " + response)

                        var getJson = JSONArray(response)
                        //var value = getJson.getJSONObject(0)
                        //var product = value.getString("product")
                        //var productTitle = getJson.getJSONObject(0).getJSONObject("product").getString("title")

                        //var product = getJson.getJSONObject(0).getString("title")
                        //println("getJson.length() ================== " + getJson.length())

                        for (i in 0 until getJson.length()) {
                            println("Product : =============================== " + getJson.getJSONObject(i).getString("title"))
                            arr.add(getJson.getJSONObject(i).getString("title"))
                        }

                        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr)
                        idRecyclerView.adapter = adapter

                        println("Array length --------------- " + arr)
                    },
                    Response.ErrorListener {
                        //idTextView.text = "That didn't work!"
                        println("That didn't work!")
                    })

            // Add the request to the RequestQueue.
            //queue.add(stringRequest)
            MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

    }
}
