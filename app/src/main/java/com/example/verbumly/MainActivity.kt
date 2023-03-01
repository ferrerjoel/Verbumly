package com.example.verbumly

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    //variables to check if user is logged
    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tf = Typeface.createFromAsset(assets,"fonts/lemon_milk/LEMONMILK-Regular.otf")

        //variables to check if user is logged
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
        isLogged()
        super.onStart()
    }
    
    private fun isLogged(){
        if(user != null){
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish()
        }
    }


}