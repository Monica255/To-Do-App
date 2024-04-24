package com.example.todoapp.ui

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.Preference
import com.example.todoapp.RepoViewModelFactory
import com.example.todoapp.TOKEN
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.retrofit.RequestLogin
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private var name = ""
    private var password = ""
    private var isDataValid=false
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, RepoViewModelFactory(this))[MainViewModel::class.java]
//        goHome()
        val token = Preference.readStringPreference(this, TOKEN,"null")
//        if(token!="null"){
//            goHome()
//        }


        val emailStream = RxTextView.textChanges(binding.etMasukEmail)
            .skipInitialValue()
            .map { name ->
                name.length < 6
            }
        emailStream.subscribe {
            isDataValid=!it
//            showEmailExistAlert(it)
        }

        val passwordStream = RxTextView.textChanges(binding.etMasukPassword)
            .skipInitialValue()
            .map { password ->
                password.length < 6
            }
        passwordStream.subscribe {
            isDataValid=!it
        }

        val invalidFieldsStream = Observable.combineLatest(
            emailStream,
            passwordStream,
            io.reactivex.functions.BiFunction {emailInvalid: Boolean, passwordInvalid: Boolean ->
                !emailInvalid && !passwordInvalid
            }
        )

        invalidFieldsStream.subscribe { isValid ->
            isDataValid = isValid
            if(isDataValid){
                name=binding.etMasukEmail.text.toString().trim()
                password=binding.etMasukPassword.text.toString().trim()
            }
        }

        binding.cbShowPass.setOnClickListener {
            if (binding.cbShowPass.isChecked) {
                binding.etMasukPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                binding.etMasukPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        }

        binding.btMasuk.setOnClickListener {
            if(true){
                Log.d("TAG",name+" "+password)
                viewModel.getResponseLogin(RequestLogin(name, password))
            }
        }

        viewModel.userlogin.observe(this){
            if(it.statusCode==2110){
                goHome()
                Preference.saveStringPreference(this, TOKEN,it.data.token)
            }else{
                Toast.makeText(this,"Gagal login",Toast.LENGTH_SHORT).show()
            }
        }



    }

    private fun goHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}