package com.example.day2task.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskDetail(
    val title: String,
    val description: String
) : Parcelable
