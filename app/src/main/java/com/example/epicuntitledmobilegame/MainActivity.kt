package com.example.epicuntitledmobilegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //variables to check if user is loged
    lateinit var auth: FirebaseAuth
    var user:FirebaseUser? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variables to check if user is loged
        auth= FirebaseAuth.getInstance()
        user = auth.currentUser

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnLogin.setOnClickListener(){
            val intent= Intent(this, Login::class.java)
            startActivity(intent)
            Toast.makeText(this, "Login button clicked", Toast.LENGTH_LONG).show()
        }

        btnRegister.setOnClickListener(){
            val intent= Intent(this, Register::class.java)
            startActivity(intent)
            Toast.makeText(this, "Register button clicked",Toast.LENGTH_LONG).show()

        }

        
    }
    override fun onStart(){
        isLoged()
        super.onStart()
    }
    
    private fun isLoged(){
        if(user != null){
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish()
        }
    }


}