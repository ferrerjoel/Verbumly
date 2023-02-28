package com.example.epicuntitledmobilegame.ui_elements

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.epicuntitledmobilegame.R

class LetterBoxAdapter(context: Context, letterBoxArray: ArrayList<LetterBox>) :
    ArrayAdapter<LetterBox>(context, 0, letterBoxArray) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val letterBox = getItem(position)

        if (view == null) {
            view = LetterBox(context, null)
        }

        (view as LetterBox).text = letterBox?.letter.toString()

        return view
    }

}