package com.example.todoapp.injection

import android.content.Context
import com.example.todoapp.repo.Repository
import com.example.todoapp.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository(apiService)
    }
}