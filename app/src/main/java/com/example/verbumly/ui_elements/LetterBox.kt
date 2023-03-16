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
import androidx.core.content.res.ResourcesCompat
import com.example.verbumly.R


// We use AppCompatTextView for compatibility for other android versions
class LetterBox(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    var letter: Char = ' '

    var isCorrect = false
    var isInTheWord = false


    init {
        // This maybe should be done with a LayoutInflater
        setBackgroundResource(R.drawable.border_letter)
        textAlignment = TEXT_ALIGNMENT_CENTER
        width =
            (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56F, resources.displayMetrics)
                .toInt())
        height =
            (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56F, resources.displayMetrics)
                .toInt())
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 38F)
        setTextColor(ContextCompat.getColor(context, R.color.white))
        setTypeface(null, Typeface.BOLD)
        typeface = ResourcesCompat.getFont(context, R.font.lemonmilk)
    }

    /**
     * 0 -> It's not in the word
     * 1 -> It's in the word
     * 2 -> Correct position
     */
    fun setLetterState(state: Int) {
        when (state) {
            0 -> {
                isCorrect = false
                isInTheWord = false
            }
            1 -> {
                isCorrect = false
                isInTheWord = true
            }
            2 -> {
                isCorrect = true
                isInTheWord = false
            }
            else -> {
                isCorrect = false
                isInTheWord = false
            }
        }
        // Log.d("DEBUG", "isCorrect $isCorrect isInTheWord $isInTheWord")
    }


}