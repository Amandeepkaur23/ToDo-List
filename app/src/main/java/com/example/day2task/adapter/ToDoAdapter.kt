package com.example.day2task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemlistToDoBinding


class ToDoAdapter(private val taskList: List<String>) :
    RecyclerView.Adapter<ToDoAdapter.ToDOViewHolder>() {

    private var taskData: MutableList<String> = taskList as MutableList<String>

    inner class ToDOViewHolder(private val binding: ItemlistToDoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binds(task: String, index: Int) {
            binding.txtTask.text = task

            binding.btnDelete.setOnClickListener {
//                binding.txtTask.text = " "
//                binding.btnDelete.isVisible = false
                deleteTask(index)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDOViewHolder {
        val binding =
            ItemlistToDoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDOViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: ToDOViewHolder, position: Int) {
        val task = taskList[position]
        holder.binds(task, position)
    }

    fun deleteTask(index: Int){
        taskData.removeAt(index)
        notifyDataSetChanged()
    }
}