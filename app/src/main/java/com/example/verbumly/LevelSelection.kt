package com.example.verbumly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LevelSelection : AppCompatActivity() {

    private lateinit var fourLettersBtn: Button
    private lateinit var fiveLettersBtn: Button
    private lateinit var sixLettersBtn: Button
    private lateinit var sevenLettersBtn: Button
    private lateinit var eightLettersBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_selection)

        fourLettersBtn = findViewById(R.id.fourLettersBtn)
        fiveLettersBtn = findViewById(R.id.fiveLettersBtn)
        sixLettersBtn = findViewById(R.id.sixLettersBtn)
        sevenLettersBtn = findViewById(R.id.sevenLettersBtn)
        eightLettersBtn = findViewById(R.id.eightLettersBtn)

        fourLettersBtn.setOnClickListener(){
            startLevel(4)
        }

        fiveLettersBtn.setOnClickListener(){
            startLevel(5)
        }

        sixLettersBtn.setOnClickListener(){
            startLevel(6)
        }

        sevenLettersBtn.setOnClickListener(){
            startLevel(7)
        }

        eightLettersBtn.setOnClickListener(){
            startLevel(8)
        }
    }

    private fun startLevel(num : Int) {
        // Toast.makeText(this,"STARTED FIVE LETTERS", Toast.LENGTH_SHORT).show()
        val levelIntent = Intent(this, Level::class.java)
        levelIntent.putExtra("letters", num)
        startActivity(levelIntent)
        finish()
    }
}