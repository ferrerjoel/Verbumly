package com.example.verbumly.ui_elements

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.verbumly.R

class LetterBoxAdapter(context: Context, letterBoxArray: ArrayList<LetterBox>) :
    ArrayAdapter<LetterBox>(context, 0, letterBoxArray) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val letterBox = getItem(position)

        if (view == null) {
            view = LetterBox(context, null)
        }

        (view as LetterBox).text = letterBox?.letter.toString()

        if (letterBox != null) {
            if (letterBox.isCorrect) {
                view.setBackgroundResource(R.drawable.border_letter_cian)
            //    Log.d("DEBUG", "CORRECT")
            } else if (view.isInTheWord){
                view.setBackgroundResource(R.drawable.border_letter_cian)
            //    Log.d("DEBUG", "IN THE WORD")
            } else {
                view.setBackgroundResource(R.drawable.border_letter)
            //    Log.d("DEBUG", "ELSE")
            }
        }

        return view
    }

}