package com.example.day2task

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.example.myapplication.R

class CustomEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    init {
        // Apply custom style or behavior here
        //setupCustomFont()
        setPadding(10, 10, 10, 10) // Example padding; adjust as needed
    }

    private fun setupCustomFont() {
        // Load custom font from res/font
        val typeface: Typeface? = ResourcesCompat.getFont(context, R.font.aclonica)
        setTypeface(typeface)
    }
}