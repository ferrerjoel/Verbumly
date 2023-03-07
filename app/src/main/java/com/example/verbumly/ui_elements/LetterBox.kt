package com.example.verbumly.ui_elements

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.verbumly.R


// We use AppCompatTextView for compatibility for other android versions
class LetterBox(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    var letter: Char = ' '

    init {
        // This maybe should be done with a LayoutInflater
        setBackgroundResource(R.drawable.border_letter)
        textAlignment = TEXT_ALIGNMENT_CENTER
        width = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56F, resources.displayMetrics).toInt())
        height = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56F, resources.displayMetrics).toInt())
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 38F)
        setTextColor(ContextCompat.getColor(context, R.color.white))
        setTypeface(null, Typeface.BOLD)
    }

    /**
     * Receives a number as the state of the color of the background of the letter box
     * 0: No background
     * 1: Gray (default)
     * 2: Yellow
     * 3: Blue / Green
     */
    fun setState(state : Int) {
        setBackgroundResource(
            when (state) {
                0 -> R.drawable.border_letter_cian
                1 -> R.drawable.border_letter_cian
                2 -> R.drawable.border_letter_cian
                3 -> R.drawable.border_letter_cian
                else -> R.drawable.border_letter_cian
            }
        )
        Log.d("DEBUG", "COLOR CHANGED")
    }

}