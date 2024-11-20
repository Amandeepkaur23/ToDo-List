package com.example.day2task.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.day2task.db.TaskDatabase
import com.example.day2task.model.TaskDetail
import com.example.day2task.repository.ToDoRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ToDoViewModel(private val applicationContext: Application): AndroidViewModel(applicationContext) {

    private val toDoRepository = ToDoRepository(TaskDatabase.getDatabase(applicationContext).taskDao())

    var taskLiveData: MutableLiveData<List<TaskDetail>> = MutableLiveData()

    init {
        viewModelScope.launch {
            taskLiveData.postValue(toDoRepository.getTasks())
        }
    }

//    fun insertTask(task: TaskDetail):Long = runBlocking {
//        val id = toDoRepository.insertTask(task)
//        task.id = id
//        taskLiveData.value =  taskLiveData.value?.plus(task)
//        id
//    }

    fun insertTask(task: TaskDetail){
        viewModelScope.launch {
            val id = toDoRepository.insertTask(task) // Perform database operation
            task.id = id
            // Update LiveData on the main thread
            taskLiveData.value = taskLiveData.value?.plus(task) ?: listOf(task)
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

}