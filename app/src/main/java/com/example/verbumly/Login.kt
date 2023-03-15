package com.example.verbumly

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class Login : AppCompatActivity() {

    lateinit var auth: FirebaseAuth //FIREBASE AUTH

    private lateinit var mailEt: EditText
    private lateinit var passEt: EditText
    private lateinit var loginBtn: Button
    private lateinit var changePasswordBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // We search on R the elements that point to the variables
        mailEt = findViewById<EditText>(R.id.mailEt)
        passEt = findViewById<EditText>(R.id.passEt)
        loginBtn = findViewById<Button>(R.id.loginBtn)
        changePasswordBtn = findViewById<Button>(R.id.changePasswordBtn)

        loginBtn.setOnClickListener {

            auth = FirebaseAuth.getInstance()
            // Validate input
            val email: String = mailEt.text.toString()
            val pass: String = passEt.text.toString()
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

        changePasswordBtn.setOnClickListener {
            val intent = Intent(this, ResetPassword::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun playerLogIn(email: String, pass: String) {
//        val passHash = Hashing.sha256()
//            .hashString(pass, StandardCharsets.UTF_8)
//            .toString()
        Log.d("DEBUG", pass)
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val tx = "Welcome back $email"
                    Toast.makeText(this, tx, Toast.LENGTH_LONG).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Toast.makeText(
                        this, "Auth error", Toast.LENGTH_LONG
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