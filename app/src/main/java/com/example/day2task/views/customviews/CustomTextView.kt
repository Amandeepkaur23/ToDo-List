package com.example.day2task.views.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.example.myapplication.R

class CustomTextView : AppCompatTextView{

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
        // Load font from res/font directory
        val typeface = ResourcesCompat.getFont(context, R.font.aclonica)
        setTypeface(typeface)
    }
}