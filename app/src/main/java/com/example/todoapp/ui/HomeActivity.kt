package com.example.todoapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.Preference
import com.example.todoapp.R
import com.example.todoapp.RepoViewModelFactory
import com.example.todoapp.TOKEN
import com.example.todoapp.databinding.ActivityHomeBinding
import com.example.todoapp.retrofit.Data2
import com.example.todoapp.retrofit.RequestInput
import okhttp3.internal.notify

class HomeActivity : AppCompatActivity() {
    lateinit var binding:ActivityHomeBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter:Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, RepoViewModelFactory(this))[MainViewModel::class.java]
        val token = Preference.readStringPreference(this, TOKEN,"null")
//        if(token!="null"){
//            goHome()
//        }
        adapter = Adapter()
        viewModel.getAll(token)

        viewModel.list.observe(this){
            if(it.statusCode==2100){
                setUserData(it.data)
            }else{
                Toast.makeText(this,"Gagal mengambil data",Toast.LENGTH_SHORT).show()
            }
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rv.layoutManager=layoutManager

        binding.btnInput.setOnClickListener{
            val text=binding.etInput.text.toString().trim()
            if(text!=""){
                binding.etInput.setText("")
                viewModel.inputData(RequestInput(text),token)
            }
        }

        viewModel.input.observe(this){
            if(it.statusCode==2000){
                viewModel.getAll(token)
                adapter.notifyDataSetChanged()
                // TODO refresh adapter
                Toast.makeText(this,"Berhasil menyimpan",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Gagal menyimpan",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun goHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setUserData(user: List<Data2>) {
        adapter.setData(user)
        binding.rv.adapter = adapter

        adapter.setOnItemClickCallback(object : Adapter.OnItemClickCallback {
            override fun onItemClicked(data: Data2) {
                //TODO intent to detail
            }
        })
    }
}