package com.example.epicuntitledmobilegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class Register : AppCompatActivity() {

    lateinit var mailEt: EditText
    lateinit var passEt: EditText
    lateinit var nameEt: EditText
    lateinit var dateTv: TextView
    lateinit var registerBtn: Button

    // Load data to TV
    // Utilitzem calendar (hi ha moltes altres opcions)
    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat.getDateInstance() //or use
    val formatedDate = formatter.format(date)
    // ara la mostrem al TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mailEt = findViewById<EditText>(R.id.mailEt)
        passEt = findViewById<EditText>(R.id.passEt)
        nameEt = findViewById<EditText>(R.id.nameEt)
        dateTv = findViewById<TextView>(R.id.dateTv)
        registerBtn = findViewById<Button>(R.id.registerBtn)

        dateTv.text = formatedDate
    }


}