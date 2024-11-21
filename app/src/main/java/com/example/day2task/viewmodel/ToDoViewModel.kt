package com.example.day2task.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.day2task.db.TaskDatabase
import com.example.day2task.model.TaskDetail
import com.example.day2task.repository.ToDoRepository
import kotlinx.coroutines.launch

class ToDoViewModel(private val applicationContext: Application): AndroidViewModel(applicationContext) {

    private val toDoRepository = ToDoRepository(TaskDatabase.getDatabase(applicationContext).taskDao())
    var taskLiveData = toDoRepository.taskLiveData

    init {
        fetchTaskFromApi()
    }

    fun insertTask(task: TaskDetail){
        viewModelScope.launch {
            toDoRepository.insertTask(task)
        }
    }

    fun insertAllTasks(tasks: List<TaskDetail>) {
        viewModelScope.launch {
            toDoRepository.insertAllTasks(tasks)
        }
    }

    fun updateTask(task: TaskDetail){
        viewModelScope.launch {
            toDoRepository.updateTask(task)
        }
    }

    fun deleteTask(task: TaskDetail){
        viewModelScope.launch {
            toDoRepository.deleteTask(task)
        }
    }

    fun fetchTaskFromApi(){
        viewModelScope.launch {
            val result = toDoRepository.getTaskFromApi()
            try{
                if(result.isSuccessful && result.body() != null) {
                    result.body()?.todos?.let {
                        insertAllTasks(it)
                    }
                } else {
                    Log.d("test", "something went wrong: ${result.errorBody()}")
                }
            } catch (e: Exception){
                Log.d("test", "something went wrong: $e")
            }
        }
    }

}