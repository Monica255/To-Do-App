package com.example.todoapp.retrofit

data class RequestLogin (
    val username:String,
    val password:String
)


data class ResponseLogin(
    val statusCode:Int,
    val message: String,
    val errorMessage: String?,
    val data:Data

)

data class Data(
    val token:String
)


data class RequesRegister (
    val email:String,
    val username:String,
    val password:String
)

data class RequestInput(
    val name:String
)

data class ResponseInput(
    val statusCode:Int,
    val data:Data2
)

data class Data2(
    val id:Int,
    val name:String,
    val items:String?,
    val checklistCompletionStatus:Boolean
)