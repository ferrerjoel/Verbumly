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
    lateinit var reversedWordArray : CharArray

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        lettersNum = intent.getIntExtra("letters", 0)

        lastAndMaxArrayBoxPositions = Pair(0, lettersNum-1)

        word = WordData.getRandomWord(lettersNum)
        Log.d("DEBUG", "CHOSEN WORD: $word")
        // This is useful for checking the positions of the letters and the word
        reversedWordArray = word.reversed().toCharArray()
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
        var boxLetter : Char
        Log.d("DEBUG", currentPosition.toString())
        if (currentPosition == lastAndMaxArrayBoxPositions.second+1) {
            for (i in lettersNum downTo  1) {
                boxLetter = letterBoxArray[currentPosition - i].letter
                Log.d("DEBUG", "Letter: $boxLetter")
                if (boxLetter.lowercaseChar() == reversedWordArray[i-1]) {
                    letterBoxArray[currentPosition - i].setLetterState(2)
                    Log.d("DEBUG", "Correct position " + (currentPosition - i))
                } else if (word.contains(boxLetter, ignoreCase = true)){
                    Log.d("DEBUG", "Exists $currentPosition")
                    letterBoxArray[currentPosition - i].setLetterState(1)
                } else {
                    letterBoxArray[currentPosition - i].setLetterState(0)
                }
            }

            lastAndMaxArrayBoxPositions = lastAndMaxArrayBoxPositions.copy(first = lastAndMaxArrayBoxPositions.second, second = lastAndMaxArrayBoxPositions.second + lettersNum)

            adapter.notifyDataSetChanged()
        }
    }

    public fun addLetter(letter : Char) {

        if (lastAndMaxArrayBoxPositions.second >= currentPosition){
            letterBoxArray[currentPosition].letter = letter
            // Log.d("DEBUG", letterBoxArray[currentPosition].isCorrect.toString())
            // Log.d("DEBUG", "Letter set " + letter + " " + letterBoxArray[currentPosition].letter)
            currentPosition++
            // Refreshes the adapter data
            adapter.notifyDataSetChanged()
        }

    }

    public fun deleteLetter() {
        if (lastAndMaxArrayBoxPositions.first < currentPosition-1) {
            currentPosition--
            letterBoxArray[currentPosition].letter = ' '
            adapter.notifyDataSetChanged()
        }
    }
}