package com.example.todoapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewPropertyAnimator
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import com.example.todoapp.Preference
import com.example.todoapp.R
import com.example.todoapp.TOKEN
import com.example.todoapp.retrofit.ResponseInput

class SplashActivity : AppCompatActivity() {
    private var startSplash: ViewPropertyAnimator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val img = findViewById<TextView>(R.id.tes)
        val token = Preference.readStringPreference(this, TOKEN,"null")


//        if(token!="null"){
//            goHome()
//        }else{
//            goLogin()
//        }
        startSplash = img.animate().setDuration(splashDelay).alpha(1f).withEndAction {
            Log.d("TAG",token)
            if (token!="null") {
                //startActivity(Intent(this, HomeActivity::class.java))
                //finish()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                //finish()
            }
        }
    }
    override fun onDestroy() {
        startSplash?.cancel()
        super.onDestroy()
    }

    companion object {
        private var splashDelay: Long = 2_500L
    }
}