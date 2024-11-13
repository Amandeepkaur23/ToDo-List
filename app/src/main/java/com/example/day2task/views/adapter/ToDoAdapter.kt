package com.example.day2task.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.day2task.views.customviews.CustomDialog
import com.example.day2task.views.customviews.TransferDataListener
import com.example.myapplication.databinding.ItemlistToDoBinding


class ToDoAdapter(private val taskList: List<String>,
    private val context: Context) :
    RecyclerView.Adapter<ToDoAdapter.ToDOViewHolder>() {

    private var taskData: MutableList<String> = taskList as MutableList<String>

    inner class ToDOViewHolder(private val binding: ItemlistToDoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binds(task: String, index: Int) {
            binding.txtTask.setText(task)

            binding.txtTask.setOnClickListener {
                val customDialog = CustomDialog(context)
                customDialog.show()
                customDialog.transferDataListener = object: TransferDataListener{
                    override fun receiveData(task: String) {
                        binding.txtTask.text = task.trim()
                        notifyItemChanged(taskList.size - 1)
                    }
                }

            }

            binding.btnDelete.setOnClickListener {
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