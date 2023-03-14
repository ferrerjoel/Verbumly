package com.example.verbumly

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Menu : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null

    lateinit var logoutBtn: Button
    lateinit var creditsBtn: Button
    lateinit var scoreBtn: Button
    lateinit var playBtn: Button
    lateinit var leaderboardBtn: Button
    lateinit var myscoreTv: TextView
    lateinit var scoreTv: TextView
    lateinit var uidTv: TextView
    lateinit var mailTv: TextView
    lateinit var nameTv: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val tf = Typeface.createFromAsset(assets,"fonts/lemon_milk/LEMONMILK-Regular.otf")

        auth= FirebaseAuth.getInstance()
        user =auth.currentUser

        logoutBtn =findViewById<Button>(R.id.logoutBtn)
        creditsBtn =findViewById<Button>(R.id.creditsBtn)
        scoreBtn =findViewById<Button>(R.id.scoreBtn)
        leaderboardBtn = findViewById<Button>(R.id.leaderboardBtn)
        playBtn =findViewById<Button>(R.id.playBtn)

        myscoreTv=findViewById(R.id.myscoreTv)
        scoreTv=findViewById(R.id.scoreTv)
        uidTv=findViewById(R.id.uidTv)
        mailTv=findViewById(R.id.mailTv)
        nameTv=findViewById(R.id.nameTv)


        logoutBtn.setTypeface(tf)
        creditsBtn.setTypeface(tf)
        scoreBtn.setTypeface(tf)
        playBtn.setTypeface(tf)

        myscoreTv.setTypeface(tf)
        scoreTv.setTypeface(tf)
        uidTv.setTypeface(tf)
        mailTv.setTypeface(tf)
        nameTv.setTypeface(tf)

        creditsBtn.setOnClickListener(){
            Toast.makeText(this,"Credits", Toast.LENGTH_SHORT).show()
        }
        scoreBtn.setOnClickListener(){
            Toast.makeText(this,"Score", Toast.LENGTH_SHORT).show()
            userProfile()
        }
        leaderboardBtn.setOnClickListener(){
            Toast.makeText(this, "Leaderboard", Toast.LENGTH_SHORT).show()
            startActivity((Intent(this, Leaderboard::class.java)))
        }
        playBtn.setOnClickListener(){
            Toast.makeText(this,"Play", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LevelSelection::class.java))
        }
        logoutBtn.setOnClickListener(){
            Toast.makeText(this,"Logout", Toast.LENGTH_SHORT).show()
            logout()
        }

    }

    private fun userProfile() {
        val intent= Intent(this, EditProfile::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        isLogged()
        super.onStart()
    }
    private fun logout() {
        auth.signOut()
        // Starts main screen
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isLogged()
    {
        if (user !=null)
        {
            Toast.makeText(this,"Player logged",
                Toast.LENGTH_SHORT).show()
        }
        else
        {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }



}