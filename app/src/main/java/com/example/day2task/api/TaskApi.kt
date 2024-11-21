package com.example.day2task.api

import com.example.day2task.model.TaskFromApi
import retrofit2.Response
import retrofit2.http.GET

interface TaskApi {
    @GET("todos")
    suspend fun getTaskFromApi(): Response<TaskFromApi>
}