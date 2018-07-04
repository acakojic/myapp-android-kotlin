package com.example.koja.myapplication;

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_sign_in.*

/**
 * Demonstrate Firebase Authentication using a Google ID Token.
 */
public class SignInActivity: AppCompatActivity()  {

    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val RC_SIGN_IN: Int = 1
    lateinit var signOut: Button
    lateinit var signIn: Button
    lateinit var idButton02: Button
    lateinit var idButton03: Button
    lateinit var idImageView: ImageView
    lateinit var idEditText: EditText
    lateinit var idEditText02: EditText
    lateinit var idEditText03: EditText
    lateinit var idEditText04: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signIn = findViewById<View>(R.id.signInBtn) as Button
        signOut = findViewById<View>(R.id.signOutBtn) as Button

        idEditText = findViewById(R.id.idEditText)
        idEditText02 = findViewById(R.id.idEditText02)
        idEditText03 = findViewById(R.id.idEditText03)
        idEditText04 = findViewById(R.id.idEditText04)
        idButton02 = findViewById(R.id.idButton02)
        idImageView = findViewById(R.id.idImageView)
        idButton03 = findViewById(R.id.idButton03)

        idEditText.visibility = View.GONE
        idEditText02.visibility = View.GONE
        idEditText03.visibility = View.GONE
        idEditText04.visibility = View.GONE
        idButton02.visibility = View.GONE
        //idImageView.visibility = View.GONE
        idButton03.visibility = View.GONE

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        signIn.setOnClickListener {
            val signInIntent: Intent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
            signIn.visibility = View.GONE
            idEditText.visibility = View.VISIBLE
            idEditText02.visibility = View.VISIBLE
            idEditText03.visibility = View.VISIBLE
            idEditText04.visibility = View.VISIBLE
            idButton02.visibility = View.VISIBLE
            //idImageView.visibility = View.VISIBLE
            idButton03.visibility = View.VISIBLE
        }

        idButton02.setOnClickListener{
            val gallery = Intent(Intent.ACTION_GET_CONTENT)
            gallery.type = "image/*"
            startActivityForResult(gallery, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult (task)
        }else {
            Toast.makeText(this, "Problem in execution order :(", Toast.LENGTH_LONG).show()
        }

        //idImageView = findViewById(R.id.idImageView)

        if (resultCode === Activity.RESULT_OK) {
            val imageUri = data.data
            idImageView.setImageURI(imageUri)
        }
    }


    private fun handleResult (completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            updateUI (account)
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun updateUI (account: GoogleSignInAccount) {
        val dispTxt = findViewById<View>(R.id.dispTxt) as TextView
        dispTxt.text = "Dobro dosli " + account.displayName
        println("DISPLAY id ================ " + account.id)
        println("DISPLAY idToken ================ " + account.idToken)
        println("DISPLAY account ================ " + account.account)
        println("DISPLAY displayName ================ " + account.displayName)
        println("DISPLAY idToken ================ " + account.email)

        signOut.visibility = View.VISIBLE
        signOut.setOnClickListener {
            view: View? ->  mGoogleSignInClient.signOut().addOnCompleteListener {
                task: Task<Void> -> if (task.isSuccessful) {
                    dispTxt.text = " "
                    signOut.visibility = View.GONE
                    signOut.isClickable = false
                    signIn.visibility = View.VISIBLE
                    idEditText.visibility = View.GONE
                    idEditText02.visibility = View.GONE
                    idEditText03.visibility = View.GONE
                    idEditText04.visibility = View.GONE
                    idButton02.visibility = View.GONE
                    idImageView.visibility = View.GONE
                    idButton03.visibility = View.GONE
                }
            }
        }
    }
}
