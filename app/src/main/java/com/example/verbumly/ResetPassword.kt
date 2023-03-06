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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ResetPassword : AppCompatActivity() {

    lateinit var auth: FirebaseAuth //FIREBASE AUTH

    lateinit var mailEt: EditText
    lateinit var changePasswordBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        // We search on R the elements that point to the variables
        mailEt = findViewById<EditText>(R.id.mailEt)
        changePasswordBtn = findViewById<Button>(R.id.changePasswordBtn)

        changePasswordBtn.setOnClickListener() {
            auth = FirebaseAuth.getInstance()
            // Validate input
            var email: String = mailEt.text.toString()
            // Mail validation
            // If it's not a mail
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mailEt.error = "Invalid Mail"
            }else{
                Log.d("DEBUG", mailEt.text.toString())
                auth.sendPasswordResetEmail(mailEt.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("DEBUG", "Email sent.")
                            Toast.makeText(this, "Reset password mail sent to your email", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(this, "No email selected", Toast.LENGTH_LONG).show();
                        }
                    }
            }

        }

    }

}