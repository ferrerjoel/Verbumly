package com.example.verbumly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.verbumly.data.Player
import com.example.verbumly.ui_elements.PlayerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Leaderboard : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    val players = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference

        recoverPlayers()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerLeaderboard)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PlayerAdapter(players, this)
    }

    private fun recoverPlayers() {

        database.get().addOnSuccessListener {

            for (player in it.children){
                players.add(Player(player.child("Name").value.toString(), player.child("Stats").child("MaxStreak").value as Long, "A"))
            }

            initRecyclerView()

        } // Get the value from Firebase
    }

}