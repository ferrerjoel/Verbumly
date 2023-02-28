package com.example.epicuntitledmobilegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import com.example.epicuntitledmobilegame.data.WordData
import com.example.epicuntitledmobilegame.ui_elements.LetterBox

const val WORD_LINES = 6
class Level : AppCompatActivity() {

    lateinit var adapter : ArrayAdapter<LetterBox>
    lateinit var gridView: GridView

    lateinit var letterBoxArray: ArrayList<LetterBox>
    lateinit var word : String
    var letters : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        letters = intent.getIntExtra("letters", 0)

        word = WordData.getRandomWord(letters)

        for (i in 1..letters) {

            for (j in 1 .. WORD_LINES) {
                letterBoxArray.add(LetterBox(this, null))
            }

        }

        // Initialize the adapter, with the array of text view
        adapter = ArrayAdapter<LetterBox>(this, android.R.layout.simple_list_item_1, letterBoxArray)

        gridView = findViewById(R.id.lettersView)
        gridView.adapter = adapter
    }
}