package com.example.verbumly.ui_elements

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.verbumly.Leaderboard
import com.example.verbumly.R
import com.example.verbumly.data.Player

class PlayerAdapter(val players: List<Player>, private val leaderboardContext: Context) : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlayerViewHolder(layoutInflater.inflate(R.layout.player_item, parent, false), leaderboardContext)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        //This method is called for every item
        val item = players[position]
        holder.render(item)

    }

}