package com.example.verbumly

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.verbumly.data.WordData
import com.example.verbumly.ui_elements.LetterBox
import com.example.verbumly.ui_elements.LetterBoxAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

const val WORD_LINES = 6

class Level : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

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

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference

    }

    /**
     * Makes all checks to the current inputted word. Checks if the word exists and marks each box with the corresponding color.
     * Gray if the letter doesn't exists, yellow if the letter exists in the word but is not in the correct position
     * and blue if the letter is in the correct position
     */
    fun checkWord() {
        var boxLetter: Char
        var inputtedWord = ""
        // If we don't do this check here we may get a out of bounds error (because the word is not completed, for example)
        if (currentPosition == lastAndMaxArrayBoxPositions.second + 1 && currentPosition <= letterBoxArray.size) {
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
                    updateDataBaseValue(true)
                    showPopUp()
                } else if (currentPosition == letterBoxArray.size) {
                    updateDataBaseValue(false)
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

    /**
     * Deletes the previous letter if possible
     */
    fun deleteLetter() {
        if (lastAndMaxArrayBoxPositions.first <= currentPosition - 1) {
            currentPosition--
            letterBoxArray[currentPosition].letter = ' '
            adapter.notifyDataSetChanged()
        }
    }

    private fun showPopUp() {
        // The next line requires API 23, which is not supported in this project
        // findViewById<View>(android.R.id.content).foreground.alpha = 255
        // inflate the layout of the popup window
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.level_popup, null)

        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT

        val popupWindow = PopupWindow(popupView, width, height, false)

        initializePopUpButtons(popupView, popupWindow)

        popupView.findViewById<TextView>(R.id.popup_word).text = getString(R.string.popupWord, word)

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)
    }

    /**
     * Initialize the function of the buttons
     */
    private fun initializePopUpButtons(v: View, win: PopupWindow) {
        v.findViewById<Button>(R.id.playAgainBtn).setOnClickListener {
            // Dismisses the popup
            win.dismiss()
            recreate()
        }

        v.findViewById<Button>(R.id.returnBtn).setOnClickListener {
            returnToMenu()
        }
    }

    private fun returnToMenu() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun updateDataBaseValue(hasWon: Boolean) {
        // Get database stats
        database.child(auth.uid!!).child("Stats").get().addOnSuccessListener {
            val statsSet = database.child(auth.uid!!).child("Stats")
            // Recover base stats
            val plays = it.child("Plays").value as Long
            val currentStreak = it.child("CurrentStreak").value as Long + 1
            val maxStreak = it.child("MaxStreak").value as Long

            statsSet.child("Plays").setValue(plays + 1)

            if (hasWon) {

                statsSet.child("CurrentStreak").setValue(currentStreak)

                if (maxStreak < currentStreak) {
                    statsSet.child("MaxStreak").setValue(currentStreak)
                }

            } else {
                statsSet.child("CurrentStreak").setValue(0)
            }
        } // Get the value from Firebase


    }

}
