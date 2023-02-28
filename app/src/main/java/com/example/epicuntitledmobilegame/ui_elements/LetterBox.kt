package com.example.epicuntitledmobilegame.ui_elements

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import com.example.epicuntitledmobilegame.R

// We use AppCompatTextView for compatibility for other android versions
class LetterBox(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    val letter: Char = ' '

    init {
        // This maybe should be done with a LayoutInflater
        setBackgroundResource(R.drawable.border_letter)
        textAlignment = TEXT_ALIGNMENT_CENTER
        maxWidth = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48F, resources.displayMetrics).toInt())
        maxHeight = (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48F, resources.displayMetrics).toInt())
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 48F)

    }

}