package com.example.day2task.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.day2task.model.TaskDetail

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: TaskDetail): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTasks(tasks: List<TaskDetail>)

    @Delete
    suspend fun deleteTask(task: TaskDetail)

    @Update
    suspend fun updateTask(task: TaskDetail)

    @Query("SELECT * FROM Taskdb")
    fun getTask(): LiveData<List<TaskDetail>>
}