package com.example.todoapp.repo

import android.util.Log
import android.view.WindowInsetsAnimation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoapp.retrofit.ApiConfig
import com.example.todoapp.retrofit.ApiService
import com.example.todoapp.retrofit.RequestInput
import com.example.todoapp.retrofit.RequestLogin
import com.example.todoapp.retrofit.ResponseData
import com.example.todoapp.retrofit.ResponseInput
import com.example.todoapp.retrofit.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val apiService: ApiService) {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _userLogin = MutableLiveData<ResponseLogin>()
    var userlogin: LiveData<ResponseLogin> = _userLogin

    private val _input= MutableLiveData<ResponseInput>()
    var input: LiveData<ResponseInput> = _input

    private val _list= MutableLiveData<ResponseData>()
    var list: LiveData<ResponseData> = _list

    fun getResponseLogin(requestLogin: RequestLogin) {

        _isLoading.value = true
        val api = ApiConfig.getApiService().fetchUser(requestLogin)
        api.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(
                call: Call<ResponseLogin>,
                response: Response<ResponseLogin>
            ) {
                _isLoading.value = false
                val responseBody = response.body()

                if (response.isSuccessful) {
                    _userLogin.value = responseBody!!

                } else {
                    Log.d("TAG","Error login1")
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                _isLoading.value = false
                Log.d("TAG","Error login222 "+t.message)
            }

        })

    }

    fun getResponseInput(requestInput: RequestInput,token:String) {

        _isLoading.value = true
        val api = ApiConfig.getApiService().input(requestInput,"Bearer $token")
        api.enqueue(object : Callback<ResponseInput> {
            override fun onResponse(
                call: Call<ResponseInput>,
                response: Response<ResponseInput>
            ) {
                _isLoading.value = false
                val responseBody = response.body()

                if (response.isSuccessful) {
                    _input.value = responseBody!!
                    Log.d("TAG",responseBody.toString())

                } else {
                    Log.d("TAG","Error login1 "+responseBody?.statusCode)
                }
            }

            override fun onFailure(call: Call<ResponseInput>, t: Throwable) {

                _isLoading.value = false
                Log.d("TAG","Error login2 "+t.message)
            }

        })

    }

    fun getResponseAll(token:String) {

        _isLoading.value = true
        val api = ApiConfig.getApiService().getAll("Bearer $token")
        api.enqueue(object : Callback<ResponseData> {
            override fun onResponse(
                call: Call<ResponseData>,
                response: Response<ResponseData>
            ) {
                _isLoading.value = false
                val responseBody = response.body()

                if (response.isSuccessful) {
                    _list.value = responseBody!!
                    Log.d("TAG",responseBody.toString())

                } else {
                    Log.d("TAG","Error login1 "+responseBody?.statusCode)
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {

                _isLoading.value = false
                Log.d("TAG","Error login2 "+t.message)
            }

        })

    }
}