package com.example.koja.myapplication

import android.content.Context
import android.nfc.Tag
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class RestApiActivity : AppCompatActivity() {

    lateinit var idTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_api)

        val idButton: Button = findViewById(R.id.idButton)
        idTextView = findViewById(R.id.idTextView)

        idButton.setOnClickListener {
            //val apiResponse = URL("https://api.myjson.com/bins/198m66").readText()
            asyncTask().execute("http://c0ecd7ca.ngrok.io/post-data")

        }
    }

    inner class asyncTask() : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            // ...
        }

        override fun doInBackground(vararg urls: String?): String? {
            var urlConnection: HttpURLConnection?
            var inString: String = ""

            try {

                val url = URL(urls[0])

                urlConnection = url.openConnection() as HttpURLConnection

                inString = streamToString(urlConnection.inputStream)

                publishProgress(inString)

            } catch (Ex: Exception) {
                Log.d("", "Error in doInBackground " + Ex.message);
            }
            return inString
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            println("RESULT ------------------------------------ " + result)
            var getJson = JSONArray(result)
            //var value = getJson.getJSONObject(0)
            //var product = value.getString("product")
            //var productTitle = getJson.getJSONObject(0).getJSONObject("product").getString("title")

            ///var product = getJson.getJSONObject(0).getString("title")

            for (i in 0 until getJson.length()) {
                println("Product : =============================== " + getJson.getJSONObject(i).getString("title"))
            }
            //println("RESULT product -------++++++----------------+++++-------- " + product)
            println()
            //println("RESULT productTitle -------++++++----------------+++++-------- " + productTitle)
        }

        fun streamToString(inputStream: InputStream): String {

            val bufferReader = BufferedReader(InputStreamReader(inputStream))
            var line: String
            var result = ""

            try {
                do {
                    line = bufferReader.readLine()
                    if (line != null) {
                        result += line
                    }
                } while (line != null)
                inputStream.close()
            } catch (ex: Exception) {

            }

            return result
        }
    }
}