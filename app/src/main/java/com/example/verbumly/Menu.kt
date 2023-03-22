package com.example.verbumly

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Menu : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null

    private lateinit var logoutBtn: Button
    private lateinit var creditsBtn: Button
    private lateinit var scoreBtn: Button
    private lateinit var playBtn: Button
    private lateinit var leaderboardBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        auth= FirebaseAuth.getInstance()
        user =auth.currentUser

        logoutBtn =findViewById<Button>(R.id.logoutBtn)
        creditsBtn =findViewById<Button>(R.id.creditsBtn)
        scoreBtn =findViewById<Button>(R.id.scoreBtn)
        leaderboardBtn = findViewById<Button>(R.id.leaderboardBtn)
        playBtn =findViewById<Button>(R.id.playBtn)

        creditsBtn.setOnClickListener(){
            Toast.makeText(this,"Credits", Toast.LENGTH_SHORT).show()
        }
        scoreBtn.setOnClickListener(){
            Toast.makeText(this,"Score", Toast.LENGTH_SHORT).show()
            startActivity((Intent(this, EditProfile::class.java)))
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

    override fun onStart() {
        isLogged()
        super.onStart()
    }

    /**
     * Signs out the user
     */
    private fun logout() {
        auth.signOut()
        // Starts main screen
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Is the player is not logged it returns to the Log in / Sign up menu
     */
    private fun isLogged()
    {
        if (user !=null)
        {
            Toast.makeText(this,"Player logged"+ user!!.displayName,
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