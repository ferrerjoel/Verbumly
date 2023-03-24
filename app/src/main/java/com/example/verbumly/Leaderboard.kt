package com.example.verbumly

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.verbumly.data.Player
import com.example.verbumly.ui_elements.PlayerAdapter
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class Leaderboard : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var stRef: StorageReference

    val players = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        auth = FirebaseAuth.getInstance()
        // Used for the user data
        database = Firebase.database.reference
        // Used for the user img
        stRef = FirebaseStorage.getInstance().reference

        recoverPlayers()
    }

    /**
     * Initializes the recycler view
     */
    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerLeaderboard)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PlayerAdapter(players, this)
    }

    /**
     * Downloads user data needed to fill the recycler view
     */
    private fun recoverPlayers() {

        var totalPlayersSize: Long = 0
        var playersDone: Long = 0

        /**
         * After fetching all users this function initializes the recycle view with the data
         */
        fun createLeaderboard() {
            playersDone++
            if (totalPlayersSize == playersDone) {
                Log.d("DEBUG", players.toString())
                players.sortWith(compareByDescending<Player> { it.maxStreak })
                Log.d("DEBUG", players.toString())
                initRecyclerView()
            }
        }
        // Gets the user data
        database.get().addOnSuccessListener {
            totalPlayersSize = it.childrenCount
            for (player in it.children) {
                // Gets the URI from the user image
                stRef.child("avatars/" + player.child("Uid").value.toString()).downloadUrl.addOnSuccessListener { imageUri ->
                    Log.d("DEBUG", player.toString())
                    players.add(
                        Player(
                            player.child("Uid").value.toString(),
                            player.child("Name").value.toString(),
                            player.child("Stats").child("MaxStreak").value as Long,
                            imageUri
                        )
                    )
                    createLeaderboard()
                }.addOnFailureListener {
                    Log.d("DEBUG", "Error uid doesn't have image!")
                    players.add(
                        Player(
                            player.child("Uid").value.toString(),
                            player.child("Name").value.toString(),
                            player.child("Stats").child("MaxStreak").value as Long,
                            null
                        )

                    )
                    createLeaderboard()
                }
            }


        } // Get the value from Firebase
    }

}