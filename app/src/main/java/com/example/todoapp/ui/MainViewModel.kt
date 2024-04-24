package com.example.todoapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.repo.Repository
import com.example.todoapp.retrofit.RequestInput
import com.example.todoapp.retrofit.RequestLogin
import com.example.todoapp.retrofit.ResponseInput
import com.example.todoapp.retrofit.ResponseLogin

class MainViewModel(private val repo: Repository):ViewModel() {
    val isLoading: LiveData<Boolean> = repo.isLoading

    var userlogin: LiveData<ResponseLogin> = repo.userlogin
    var input: LiveData<ResponseInput> = repo.input

    fun getResponseLogin(requestLogin: RequestLogin) {
        repo.getResponseLogin(requestLogin)
    }

    fun inputData(requestInput: RequestInput,token:String){
        repo.getResponseInput(requestInput,token)
    }
}