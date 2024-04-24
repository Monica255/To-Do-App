package com.example.todoapp.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun fetchUser(@Body requestLogin: RequestLogin): Call<ResponseLogin>

    @POST("checklist")
    fun input(
        @Body requestInput: RequestInput,
        @Header("Authorization") token: String): Call<ResponseInput>
    @GET("checklist")
    fun getAll(
        @Header("Authorization") token: String): Call<ResponseData>
}