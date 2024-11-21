package com.example.day2task.model

data class TaskFromApi(
    val limit: Int,
    val skip: Int,
    val todos: List<TaskDetail>,
    val total: Int
)