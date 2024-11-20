package com.example.day2task.views.adapter

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
                val position = adapterPosition
                val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(taskList[position])
                Navigation.findNavController(binding.root).navigate(
                    action
                )
            }

            binding.btnDetail.setOnClickListener {
                val position = adapterPosition
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(taskList[position])
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