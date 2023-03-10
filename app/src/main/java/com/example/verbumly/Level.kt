package com.example.verbumly

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.example.verbumly.data.WordData
import com.example.verbumly.ui_elements.LetterBox
import com.example.verbumly.ui_elements.LetterBoxAdapter


const val WORD_LINES = 6

class Level : AppCompatActivity() {

    private lateinit var gridView: GridView

    private lateinit var letterBoxArray: ArrayList<LetterBox>
    lateinit var word: String
    lateinit var adapter: ArrayAdapter<LetterBox>
    private var lettersNum: Int = 0
    private var currentPosition: Int = 0

    // When you jump from line this value has to be set to know when you can't return
    // Fist element is the last position available where you can return, max is the max position where you can go
    private lateinit var lastAndMaxArrayBoxPositions: Pair<Int, Int>

    private lateinit var reversedWordArray: CharArray

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
        // Get the saved parameter that is going to be the number of letters the word is going to have
        lettersNum = intent.getIntExtra("letters", 0)

        lastAndMaxArrayBoxPositions = Pair(0, lettersNum - 1)

        WordData.init(context = this)
        word = WordData.getRandomWord(lettersNum)

        Log.d("DEBUG", "CHOSEN WORD: $word")
        // This is useful for checking the positions of the letters and the word
        reversedWordArray = word.reversed().toCharArray()
        letterBoxArray = ArrayList<LetterBox>()

        for (i in 1..lettersNum) {
            for (j in 1..WORD_LINES) {
                letterBoxArray.add(LetterBox(this, null))
            }
        }

        // Initialize the adapter, with the array of text view
        adapter = LetterBoxAdapter(this, letterBoxArray)

        gridView = findViewById(R.id.lettersView)
        gridView.numColumns = lettersNum

        gridView.adapter = adapter
    }

    /**
     * Makes all checks to the current inputted word. Checks if the word exists and marks each box with the corresponding color.
     * Gray if the letter doesn't exists, yellow if the letter exists in the word but is not in the correct position
     * and blue if the letter is in the correct position
     */
    fun checkWord() {
        var boxLetter: Char
        var inputtedWord: String = ""
        // If we don't do this check here we may get a out of bounds error (because the word is not completed, for example)
        if (currentPosition == lastAndMaxArrayBoxPositions.second + 1) {
            // Get the inputted word on the corresponding line
            for (i in lettersNum downTo 1) {
                inputtedWord += letterBoxArray[currentPosition - i].letter
            }
            // Check if the word can be inputted
            if (WordData.wordExists(inputtedWord)) {
                for (i in lettersNum downTo 1) {
                    boxLetter = letterBoxArray[currentPosition - i].letter
//                    Log.d("DEBUG", "Letter: $boxLetter")
                    if (boxLetter.lowercaseChar() == reversedWordArray[i - 1]) {
                        letterBoxArray[currentPosition - i].setLetterState(2)
                        // Log.d("DEBUG", "Correct position " + (currentPosition - i))
                    } else if (word.contains(boxLetter, ignoreCase = true)) {
                        // Log.d("DEBUG", "Exists $currentPosition")
                        letterBoxArray[currentPosition - i].setLetterState(1)
                    } else {
                        letterBoxArray[currentPosition - i].setLetterState(0)
                        findViewById<MyKeyboard>(R.id.keyboard).grayOutKey(boxLetter)
                    }
                }
                // Set the new margin of positions where the user can move on the array
                lastAndMaxArrayBoxPositions = lastAndMaxArrayBoxPositions.copy(
                    first = lastAndMaxArrayBoxPositions.second + 1,
                    second = lastAndMaxArrayBoxPositions.second + lettersNum
                )

                adapter.notifyDataSetChanged()

                if (inputtedWord.lowercase() == word) {
                    Log.d("DEBUG", "HEY")
                    showPopUp()
                }
            }
        }

    }

    fun addLetter(letter: Char) {

        if (lastAndMaxArrayBoxPositions.second >= currentPosition) {
            letterBoxArray[currentPosition].letter = letter
            // Log.d("DEBUG", letterBoxArray[currentPosition].isCorrect.toString())
            // Log.d("DEBUG", "Letter set " + letter + " " + letterBoxArray[currentPosition].letter)
            currentPosition++
            // Refreshes the adapter data
            adapter.notifyDataSetChanged()
        }

    }

    fun deleteLetter() {
        if (lastAndMaxArrayBoxPositions.first <= currentPosition - 1) {
            currentPosition--
            letterBoxArray[currentPosition].letter = ' '
            adapter.notifyDataSetChanged()
        }
    }

    private fun showPopUp() {

        // inflate the layout of the popup window
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.level_popup, null)

        // create the popup window
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT

        val popupWindow = PopupWindow(popupView, width, height, false)

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken

        popupWindow.showAtLocation(mainView, Gravity.CENTER, 0, 0)
    }
}