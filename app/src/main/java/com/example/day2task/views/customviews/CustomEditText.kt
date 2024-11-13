package com.example.day2task.views.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.example.myapplication.R

class CustomEditText : AppCompatEditText {

    constructor(context: Context): super(context){
        setupCustomFont()
    }

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet){
        setupCustomFont()
    }

    constructor(context: Context, attributeSet: AttributeSet, defAttr: Int): super(context, attributeSet, defAttr){
        setupCustomFont()
    }

    private fun setupCustomFont() {
        // Load custom font from res/font
        val typeface: Typeface? = ResourcesCompat.getFont(context, R.font.aclonica)
        setTypeface(typeface)
    }
}