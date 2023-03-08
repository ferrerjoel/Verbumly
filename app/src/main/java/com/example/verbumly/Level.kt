package com.example.verbumly

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.example.verbumly.data.WordData
import com.example.verbumly.ui_elements.LetterBox
import com.example.verbumly.ui_elements.LetterBoxAdapter

const val WORD_LINES = 6
class Level : AppCompatActivity() {

    lateinit var gridView: GridView

    lateinit var letterBoxArray: ArrayList<LetterBox>
    lateinit var word : String
    lateinit var adapter : ArrayAdapter<LetterBox>
    var lettersNum : Int = 0
    var currentPosition : Int = 0
    // When you jump from line this value has to be set to know when you can't return
    // Fist element is the last position available where you can return, max is the max position where you can go
    private lateinit var lastAndMaxArrayBoxPositions : Pair<Int, Int>

    val currentInput : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        lettersNum = intent.getIntExtra("letters", 0)

        lastAndMaxArrayBoxPositions = Pair(0, lettersNum)

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

    public fun checkWord() {
        if (currentPosition == lastAndMaxArrayBoxPositions.second) {
            lastAndMaxArrayBoxPositions = lastAndMaxArrayBoxPositions.copy(first = lastAndMaxArrayBoxPositions.second, second = lastAndMaxArrayBoxPositions.second + lettersNum)
        }
    }

    public fun addLetter(letter : Char) {
        Log.d("DEBUG", "1--------------")
        if (lastAndMaxArrayBoxPositions.second > currentPosition){
            letterBoxArray[currentPosition].letter = letter
            letterBoxArray[currentPosition].setLetterState(2)
            Log.d("DEBUG", letterBoxArray[currentPosition].isCorrect.toString())
            // Log.d("DEBUG", "Letter set " + letter + " " + letterBoxArray[currentPosition].letter)
            currentPosition++
            // Refreshes the adapter data
            adapter.notifyDataSetChanged()
        }
        Log.d("DEBUG", "2--------------")
    }

    public fun deleteLetter() {
        if (lastAndMaxArrayBoxPositions.first <= currentPosition) {
            currentPosition--
            letterBoxArray[currentPosition].letter = ' '
            adapter.notifyDataSetChanged()
        }
    }
}