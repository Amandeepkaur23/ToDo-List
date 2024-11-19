package com.example.day2task.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.day2task.db.TaskDao
import com.example.day2task.model.TaskDetail

class ToDoRepository(private val taskDao: TaskDao) {

    suspend fun getTasks(): List<TaskDetail> {
        return taskDao.getTask()
    }

    suspend fun insertTask(task: TaskDetail): Long{
        return taskDao.insertTask(task)
    }

    suspend fun updateTask(task: TaskDetail){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskDetail){
        taskDao.deleteTask(task)
    }
}