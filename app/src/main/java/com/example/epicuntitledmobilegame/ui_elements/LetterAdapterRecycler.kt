package com.example.epicuntitledmobilegame.ui_elements

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class LetterAdapterRecycler : RecyclerView.Adapter<LetterAdapterRecycler.ViewHolder>() {

    private lateinit var letterBoxArray: ArrayList<LetterBox>

    inner class ViewHolder(itemView: View, letterBoxArray : ArrayList<LetterBox>) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LetterAdapterRecycler.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: LetterAdapterRecycler.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}