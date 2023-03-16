package com.example.verbumly.ui_elements

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.verbumly.R
import com.example.verbumly.data.Player
import com.squareup.picasso.Picasso

class PlayerViewHolder(view: View, private val leaderboardContext : Context) : RecyclerView.ViewHolder(view) {
    // Variables that point to the content of the layout
    val playerName = view.findViewById<TextView>(R.id.playerName)
    val maxStreak = view.findViewById<TextView>(R.id.playerMaxStreak)
    val playerPhoto = view.findViewById<ImageView>(R.id.playerPhoto)

    fun render(player: Player) {
        //Called for every player

        playerName.text = player.name
        // We need the current activity context to get a string, therefore we need to pass the context from Leaderboard -> PlayerAdapter -> PlayerViewHolder
        maxStreak.text = leaderboardContext.getString(R.string.leaderboardMaxStreak, player.maxStreak)

        Picasso.get().load(player.photo).resize(150, 150).into(playerPhoto)

        playerPhoto.setOnClickListener() {
            Toast.makeText(playerPhoto.context, playerName.text, Toast.LENGTH_LONG).show()
        }

    }
}
