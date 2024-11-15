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

//            //edit task
//            binding.txtTask.setOnClickListener {
//                val task = binding.txtTask.text.toString()
//                val customDialog = CustomDialog(context, true, task)
//                customDialog.show()
//                customDialog.transferDataListener = object: TransferDataListener{
//                    override fun receiveData(task: String) {
//                        taskList[adapterPosition] = task.trim()
//                        notifyItemChanged(adapterPosition)
//                    }
//                }
//
//            }

//            binding.btnDelete.setOnClickListener {
//                deleteTask(index)
//            }

            binding.btnEdit.setOnClickListener {
                val editTitle = binding.txtTask.text.toString().trim()
                val editDesc =  taskList[adapterPosition].description
                val taskDetail = TaskDetail(editTitle, editDesc)
                val position = adapterPosition
                val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(taskDetail, position)

                // this will navigate the current fragment i.e
                // Registration to the Detail fragment
                Navigation.findNavController(binding.root).navigate(
                    action
                )
            }

            binding.btnDetail.setOnClickListener {

                val detailTitle = binding.txtTask.text.toString().trim()
                Log.d("test", "check ${detailTitle}")
                val detailDesc = taskList[adapterPosition].description
                val taskDetail = TaskDetail(detailTitle, detailDesc)
                val position = adapterPosition
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(taskDetail, position)

                // this will navigate the current fragment i.e
                // Registration to the Detail fragment
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

//    fun deleteTask(index: Int){
//        taskList.removeAt(index)
//        notifyDataSetChanged()
//    }
}