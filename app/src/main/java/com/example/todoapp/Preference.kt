package com.example.todoapp

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

const val TOKEN="token"
class Preference {
    companion object{
        // Function to save a string preference
        fun saveStringPreference(context: Context, key: String, value: String) {
            val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putString(key, value).apply()
        }

        // Function to read a string preference
        fun readStringPreference(context: Context, key: String, defaultValue: String): String {
            val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            return sharedPreferences.getString(key, defaultValue) ?: defaultValue
        }


        // Function to delete a preference
        fun deletePreference(context: Context, key: String) {
            val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().remove(key).apply()
        }


    }
}