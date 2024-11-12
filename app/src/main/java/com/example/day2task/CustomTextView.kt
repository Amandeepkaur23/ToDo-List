package com.example.day2task

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.example.myapplication.R

class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    init {
        // Apply custom style or behavior here
        //setupCustomFont()
        setPadding(10, 10, 10, 10)
    }

    private fun setupCustomFont() {
        // Load font from res/font directory
        val typeface = ResourcesCompat.getFont(context, R.font.aclonica)
        setTypeface(typeface)
    }


}