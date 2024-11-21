package com.example.day2task.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "TaskDb")
@Parcelize
data class TaskDetail(
    @field:SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @field:SerializedName("todo")
    var title: String,

    var description: String? = "",

    @field:SerializedName("completed")
    var isCompleted: Boolean? = false
) : Parcelable
