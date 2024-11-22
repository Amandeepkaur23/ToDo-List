package com.example.day2task.repository

import com.example.day2task.api.RetrofitClient
import com.example.day2task.db.TaskDao
import com.example.day2task.model.TaskDetail
import com.example.day2task.model.TaskFromApi
import retrofit2.Response

class ToDoRepository(private val taskDao: TaskDao) {
    private val taskApi = RetrofitClient.getTaskApi()

    var taskLiveData = taskDao.getTask()

    suspend fun insertTask(task: TaskDetail): Long{
        return taskDao.insertTask(task)
    }

    suspend fun insertAllTasks(tasks: List<TaskDetail>){
        taskDao.insertAllTasks(tasks)
    }

    suspend fun updateTask(task: TaskDetail){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskDetail){
        taskDao.deleteTask(task)
    }

    suspend fun getTaskFromApi(): Response<TaskFromApi> {
        return taskApi.getTaskFromApi()
    }
}