package com.example.epicuntitledmobilegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.text.SimpleDateFormat
import java.util.*

class Register : AppCompatActivity() {

    private val TAG : String = "STATE"

    lateinit var auth: FirebaseAuth //FIREBASE AUTH

    lateinit var mailEt: EditText
    lateinit var passEt: EditText
    lateinit var nameEt: EditText
    lateinit var dateTv: TextView
    lateinit var registerBtn: Button

    // Load data to TV
    // We use calendar (but there are more options)
    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat.getDateInstance() //or use
    val formatedDate = formatter.format(date)
    // We show the TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        mailEt = findViewById<EditText>(R.id.mailEt)
        passEt = findViewById<EditText>(R.id.passEt)
        nameEt = findViewById<EditText>(R.id.nameEt)
        dateTv = findViewById<TextView>(R.id.dateTv)
        registerBtn = findViewById<Button>(R.id.registerBtn)

        dateTv.text = formatedDate

        registerBtn.setOnClickListener() {
            // Validate input
            val email: String = mailEt.text.toString()
            val pass: String = passEt.text.toString()
            // Mail validation
            // If it's not mail type
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mailEt.error = "Invalid Mail"
            } else if (pass.length < 6) {
                passEt.error = "Password less than 6 chars"
            } else {
                registerPlayer(email, pass)
            }
        }
    }

    private fun registerPlayer(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        // Question mark = can be null
        if (user != null) {
            var puntuation: Int = 0
            var uidString: String = user.uid
            var mailString: String = mailEt.text.toString()
            var passString: String = passEt.text.toString()
            var nameString: String = nameEt.text.toString()
            var dateString: String = dateTv.text.toString()
            // TODO: HERE SAVES THE CONTENT OF THE DATABASE
        } else {
            Toast.makeText(this, "ERROR CREATE USER ",Toast.LENGTH_SHORT).show()
        }
    }
}

