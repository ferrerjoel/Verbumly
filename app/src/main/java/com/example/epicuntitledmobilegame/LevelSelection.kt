package com.example.epicuntitledmobilegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class LevelSelection : AppCompatActivity() {

    lateinit var fiveLettersBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_selection)

        fiveLettersBtn = findViewById(R.id.fiveLettersBtn)

        fiveLettersBtn.setOnClickListener(){
            Toast.makeText(this,"STARTED FIVE LETTERS", Toast.LENGTH_SHORT).show()
            val levelIntent = Intent(this, Level::class.java)
            levelIntent.putExtra("letters", 5)
            startActivity(levelIntent)
        }
    }
}