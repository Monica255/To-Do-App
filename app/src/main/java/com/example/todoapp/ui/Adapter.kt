package com.example.todoapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemDataBinding
import com.example.todoapp.retrofit.Data2

class Adapter() :
    RecyclerView.Adapter<Adapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    var listStory=listOf<Data2>()
    fun setData(list: List<Data2>){
        listStory=list
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listStory[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listStory[holder.adapterPosition])
        }
    }

    class ListViewHolder(private var binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data2) {
            binding.tvText.text=data.name
            binding.cb.isChecked=data.checklistCompletionStatus
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Data2)
    }

    override fun getItemCount(): Int = listStory.size


}