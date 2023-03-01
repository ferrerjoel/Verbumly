package com.example.epicuntitledmobilegame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.common.hash.Hashing
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.nio.charset.StandardCharsets


class Login : AppCompatActivity() {

    lateinit var auth: FirebaseAuth //FIREBASE AUTH

    lateinit var mailEt: EditText
    lateinit var passEt: EditText
    lateinit var loginBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // We search on R the elements that point to the variables
        mailEt = findViewById<EditText>(R.id.mailEt)
        passEt = findViewById<EditText>(R.id.passEt)
        loginBtn = findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener() {

            auth = FirebaseAuth.getInstance()
            // Validate input
            var email: String = mailEt.text.toString()
            var pass: String = passEt.text.toString()
            // Mail validation
            // If it's not a mail
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mailEt.error = "Invalid Mail"
            } else if (pass.length < 6) {
                passEt.error = "Password less than 6 chars"
            } else {
                playerLogIn(email, pass)
            }
        }

    }

    private fun playerLogIn(email: String, pass: String) {
//        val passHash = Hashing.sha256()
//            .hashString(pass, StandardCharsets.UTF_8)
//            .toString()
        Log.d("DEBUG",pass)
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this)
            { task ->
                if (task.isSuccessful) {
                    val tx: String = "Welcome back $email"
                    Toast.makeText(this, tx, Toast.LENGTH_LONG).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(
                        this, "Auth error",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

    }

    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
        finish()
    }


}