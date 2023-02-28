package com.example.epicuntitledmobilegame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.epicuntitledmobilegame.data.WordData
import com.example.epicuntitledmobilegame.ui_elements.LetterBox


const val WORD_LINES = 6
class Level : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    lateinit var letterBoxArray: ArrayList<LetterBox>
    lateinit var word : String
    var lettersNum : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        lettersNum = intent.getIntExtra("letters", 0)

        word = WordData.getRandomWord(lettersNum)

        letterBoxArray = ArrayList<LetterBox>()

        for (i in 1..lettersNum) {
            for (j in 1 .. WORD_LINES) {
                letterBoxArray.add(LetterBox(this, null))
            }
        }

        // Initialize the adapter, with the array of text view

        recyclerView = findViewById(R.id.lettersView)
        recyclerView.layoutManager = GridLayoutManager(this, lettersNum)

        //recyclerView.adapter = adapter
    }
}