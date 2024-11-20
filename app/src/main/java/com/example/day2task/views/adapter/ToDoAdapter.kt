package com.example.day2task.views.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.day2task.model.TaskDetail
import com.example.day2task.views.ui.HomeFragmentDirections
import com.example.myapplication.databinding.ItemlistToDoBinding

class ToDoAdapter(private val taskList: MutableList<TaskDetail>) :
    RecyclerView.Adapter<ToDoAdapter.ToDOViewHolder>() {

    inner class ToDOViewHolder(private val binding: ItemlistToDoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binds(task: TaskDetail) {
            binding.txtTask.setText(task.title)

            binding.btnEdit.setOnClickListener {
                val editTitle = binding.txtTask.text.toString().trim()
                val editDesc =  taskList[adapterPosition].description
                val position = adapterPosition
                Log.d("test", "in adapter ${position}")
                val taskDetail = TaskDetail(title = editTitle, description = editDesc)
                val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(taskList[position], position)

                Navigation.findNavController(binding.root).navigate(
                    action
                )
            }

            binding.btnDetail.setOnClickListener {
                val detailTitle = binding.txtTask.text.toString().trim()
                Log.d("test", "check ${detailTitle}")
                val detailDesc = taskList[adapterPosition].description
                val taskDetail = TaskDetail(title = detailTitle, description = detailDesc)
                val position = adapterPosition
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(taskDetail, position)

                Navigation.findNavController(binding.root).navigate(
                    action
                )
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
        holder.binds(task)
    }

}