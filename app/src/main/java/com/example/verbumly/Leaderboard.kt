package com.example.verbumly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.verbumly.data.Player
import com.example.verbumly.ui_elements.PlayerAdapter

class   Leaderboard : AppCompatActivity() {

    val players = listOf<Player>(
        Player("Pepe",12,"https://www.kasandbox.org/programming-images/avatars/piceratops-tree.png"),
        Player("Juanito",102,"https://www.kasandbox.org/programming-images/avatars/leafers-seed.png"),
        Player("Jaimito",18,"https://www.kasandbox.org/programming-images/avatars/leaf-yellow.png"),
        Player("Jorgito",98,"https://www.kasandbox.org/programming-images/avatars/leaf-blue.png")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerLeaderboard)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=PlayerAdapter(players)
    }

}