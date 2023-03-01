package com.example.epicuntitledmobilegame.ui_elements

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.epicuntitledmobilegame.R
import android.view.ViewGroup.LayoutParams


// We use AppCompatTextView for compatibility for other android versions
class LetterBox(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    val letter: Char = 'A'

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

}