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

class ToDoViewModel(private val application: Application): AndroidViewModel(application) {

    val toDoRepository = ToDoRepository(TaskDatabase.getDatabase(application).taskDao())

    var taskList: MutableLiveData<List<TaskDetail>> = MutableLiveData()

    init {
        viewModelScope.launch {
            taskList.postValue(toDoRepository.getTasks())
        }
    }

    fun insertTask(task: TaskDetail):Long = runBlocking {
        val id = toDoRepository.insertTask(task)
        task.id = id.toInt()
        taskList.value =  taskList.value?.plus(task)
        id
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