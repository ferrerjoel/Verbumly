package com.example.epicuntitledmobilegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView

class Level : AppCompatActivity() {

    lateinit var adapter : ArrayAdapter<String>
    lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myStringArray)

        gridView = findViewById(R.id.lettersView)
        gridView.adapter = adapter
    }
}