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

class Leaderboard : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var stRef: StorageReference

    val players = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        stRef = FirebaseStorage.getInstance().reference

        recoverPlayers()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerLeaderboard)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PlayerAdapter(players, this)
    }

    private fun recoverPlayers() {

        var userImage : Uri? = null

        database.get().addOnSuccessListener {

            for (player in it.children){
                userImage = null
                stRef.child("avatars/" + player.child("Uid").value.toString()).downloadUrl.addOnSuccessListener {
                    OnSuccessListener<Uri?> { uri ->
                        userImage = uri
                        players.add(Player(player.child("Name").value.toString(), player.child("Stats").child("MaxStreak").value as Long, userImage))
                    }
                }.addOnFailureListener {
                    Log.d("DEBUG", "The user doesn't have an image")
                    players.add(Player(player.child("Name").value.toString(), player.child("Stats").child("MaxStreak").value as Long, userImage))
                }


            }

            initRecyclerView()

        } // Get the value from Firebase
    }

}