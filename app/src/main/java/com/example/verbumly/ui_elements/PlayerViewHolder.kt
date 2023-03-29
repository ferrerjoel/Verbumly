package com.example.verbumly.ui_elements

import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.verbumly.EditProfile
import com.example.verbumly.Level
import com.example.verbumly.R
import com.example.verbumly.data.Player
import com.squareup.picasso.Picasso

class PlayerViewHolder(view: View, private val leaderboardContext: Context) :
    RecyclerView.ViewHolder(view) {
    // Variables that point to the content of the layout
    private val playerName = view.findViewById<TextView>(R.id.playerName)
    private val maxStreak = view.findViewById<TextView>(R.id.playerMaxStreak)
    private val playerPhoto = view.findViewById<ImageView>(R.id.playerPhoto)

    fun render(player: Player) {

        //Called for every player
        playerName.text = player.name
        // We need the current activity context to get a string, therefore we need to pass the context from Leaderboard -> PlayerAdapter -> PlayerViewHolder
        maxStreak.text =
            leaderboardContext.getString(R.string.leaderboardMaxStreak, player.maxStreak)
        // If the user doesn't have an image we will use the default one
        if (player.photo != null) {
            Picasso.get().load(player.photo).resize(100, 100).into(playerPhoto)
        } else {
            playerPhoto.setImageResource(R.drawable.no_profile_user)
        }

        playerPhoto.setOnClickListener() {
            Toast.makeText(playerPhoto.context, playerName.text, Toast.LENGTH_LONG).show()
            val levelIntent = Intent(leaderboardContext, EditProfile::class.java)
            levelIntent.putExtra("UID", player.uid)
            leaderboardContext.startActivity(levelIntent)
        }

    }
}
