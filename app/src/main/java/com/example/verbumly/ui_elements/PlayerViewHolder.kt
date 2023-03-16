package com.example.verbumly.ui_elements

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.verbumly.R
import com.example.verbumly.data.Player

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Variables that point to the content of the layout
    val playerName = view.findViewById<TextView>(R.id.playerName)
    val maxStreak = view.findViewById<TextView>(R.id.playerMaxStreak)
    val playerPhoto = view.findViewById<ImageView>(R.id.playerPhoto)

    fun render(player: Player) {
        //Called for every player
        playerName.text= player.name
        maxStreak.text= player.maxStreak.toString()

    }
}
