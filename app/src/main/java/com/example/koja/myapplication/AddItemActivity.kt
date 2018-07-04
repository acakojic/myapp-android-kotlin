package com.example.koja.myapplication

import android.Manifest
import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ImageView
import android.content.Intent
import android.net.Uri
import java.text.SimpleDateFormat
import java.util.*
import android.provider.MediaStore
import java.io.File
import android.R.attr.data




class AddItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val idButton: Button = findViewById(R.id.idButton)
        val idButton02: Button = findViewById(R.id.idButton02)
        val idImageView: ImageView = findViewById(R.id.idImageView)

        idButton.setOnClickListener{
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                val pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                val pictureName = getPictureName()
                val imageFile = File(pictureDirectory, pictureName)

                var pictureUri = Uri.fromFile(imageFile)

                intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri)
                startActivityForResult(intent, 1)

                idImageView.setImageURI(pictureUri)
        }

        idButton02.setOnClickListener{

            //val intent = Intent()
            //intent.type = 'image/*'
            /*intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0)
            */
            val gallery = Intent(Intent.ACTION_GET_CONTENT)
            gallery.type = "image/*"
            startActivityForResult(gallery, 1)
        }
    }

    private fun getPictureName(): String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val timestamp = sdf.format(Date())
        return "PlantPlacesImage$timestamp.jpg"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        val idImageView: ImageView = findViewById(R.id.idImageView)

        if (resultCode === Activity.RESULT_OK) {
            val imageUri = data.data
            idImageView.setImageURI(imageUri)

        }

        //Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        //imageView.setImageBitmap(bitmap);
    }

}
