package com.example.epicuntitledmobilegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnLogin.setOnClickListener(){
            Toast.makeText(this, "Clicked login button", Toast.LENGTH_LONG).show()
        }
        btnRegister.setOnClickListener(){
            Toast.makeText(this, "Clicked register button",Toast.LENGTH_LONG).show()
        }
    }


}