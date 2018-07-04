package com.example.koja.myapplication

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class SendApiActivity : AppCompatActivity() {

    lateinit var idTextView: TextView
    lateinit var idEditText: EditText
    lateinit var idEditText02: EditText
    lateinit var idButton: Button
    lateinit var idButton02: Button
    lateinit var idImageView: ImageView
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_api)

        idTextView = findViewById(R.id.idTextView)
        idEditText = findViewById(R.id.idEditText)
        idEditText02 = findViewById(R.id.idEditText02)
        idButton = findViewById(R.id.idButton)
        idButton02= findViewById(R.id.idButton02)
        idImageView = findViewById(R.id.idImageView)
        val url = "http://c0ecd7ca.ngrok.io/post-data"

        var postValue = JSONObject()

        idButton.setOnClickListener {

            postValue.put("title", idEditText.text.toString())
            postValue.put("product", idEditText02.text.toString())
            postValue.put("image", imageToString(bitmap))
            idEditText.text = null
            idEditText02.text = null
            // Instantiate the RequestQueue.
            //val queue = Volley.newRequestQueue(this)

            // Request a string response from the provided URL.
            val stringRequest = JsonObjectRequest(Request.Method.GET, url, postValue,
                    Response.Listener { response ->
                        // Display the first 500 characters of the response string.
                        idTextView.text = "Response is: $postValue"
                        println("DATA =====================================================" + response)
                    },
                    Response.ErrorListener { idTextView.text = "That didn't work!" })

            // Add the request to the RequestQueue.
            //queue.add(stringRequest)
            MySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }

        idButton02.setOnClickListener{

            val gallery = Intent(Intent.ACTION_GET_CONTENT)
            gallery.type = "image/*"
            startActivityForResult(gallery, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        val idImageView: ImageView = findViewById(R.id.idImageView)

        if (resultCode === Activity.RESULT_OK) {
            val imageUri = data.data
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri)
            idImageView.setImageURI(imageUri)
        }
    }

    fun imageToString(bitmap: Bitmap): String?{
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

}
