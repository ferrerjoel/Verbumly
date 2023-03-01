package com.example.epicuntitledmobilegame

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.example.epicuntitledmobilegame.data.WordData
import com.example.epicuntitledmobilegame.ui_elements.LetterBox
import com.example.epicuntitledmobilegame.ui_elements.LetterBoxAdapter
import kotlin.random.Random
import kotlin.random.nextInt


const val WORD_LINES = 6
class Level : AppCompatActivity() {

    lateinit var gridView: GridView

    lateinit var letterBoxArray: ArrayList<LetterBox>
    lateinit var word : String
    lateinit var adapter : ArrayAdapter<LetterBox>
    var lettersNum : Int = 0

    val currentInput : String = ""
    val currentBoxPosition : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        lettersNum = intent.getIntExtra("letters", 0)

        word = WordData.getRandomWord(lettersNum)
        Log.d("DEBUG", "CHOSEN WORD: $word")

        letterBoxArray = ArrayList<LetterBox>()

        for (i in 1..lettersNum) {
            for (j in 1 .. WORD_LINES) {
                letterBoxArray.add(LetterBox(this, null))
            }
        }

        // Initialize the adapter, with the array of text view
        adapter = LetterBoxAdapter(this, letterBoxArray)

        gridView = findViewById(R.id.lettersView)
        gridView.numColumns = lettersNum

        gridView.adapter = adapter

    }

    private fun checkWord() {

    }
}