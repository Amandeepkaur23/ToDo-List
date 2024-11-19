package com.example.day2task.views.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.BundleCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.day2task.model.TaskDetail
import com.example.day2task.viewmodel.ToDoViewModel
import com.example.day2task.views.adapter.ToDoAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    //binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    //taskList and adapter
    private var taskList = mutableListOf<TaskDetail>()
    private lateinit var taskAdapter: ToDoAdapter
    //viewModel
    val toDoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskAdapter = ToDoAdapter(taskList)
        binding.rvList.adapter = taskAdapter

        //Add task to RV
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }

        toDoViewModel.taskList.observe(viewLifecycleOwner, Observer {
            taskList.clear()
            taskList.addAll(it)
            taskAdapter.notifyDataSetChanged()
            Log.d("test", "check observer called.....")
        })

//        setFragmentResultListener("requestEditTask") { _, bundle ->
//            val editTask = bundle.getParcelable<TaskDetail>("editTask")
//            val position = bundle.getInt("position")
//            if (editTask != null) {
//                taskList[position] = editTask
//                taskAdapter.notifyItemChanged(position)
//            }
//        }

        //Add data to db

        setFragmentResultListener("requestAddTask") { requestKey, bundle ->

            val result = BundleCompat.getParcelable(bundle, "task", TaskDetail::class.java)
            if (result != null) {
                taskList.add(result)
                taskAdapter.notifyItemChanged(taskList.size - 1)
            } else {
                Log.d("test", "Received TaskDetail is null")
            }
        }

        //delete item in recyclerView
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedTask: TaskDetail = taskList[position]

                toDoViewModel.deleteTask(deletedTask)
                taskAdapter.notifyItemRemoved(position)

//                Snackbar.make(binding.rvList, "Deleted " + deletedTask.title, Snackbar.LENGTH_LONG)
//                    .setAction("Undo") {
//
//                        // adding on click listener to our action of snack bar.
//                        // below line is to add our item to array list with a position.
//
//                        val task = TaskDetail(position, deletedTask.title, deletedTask.description)
//                        toDoViewModel.insertTask(task)
//                        //taskList.add(position, deletedTask)
//
//                        // below line is to notify item is
//                        // added to our adapter class.
//                        taskAdapter.notifyItemInserted(position)
//                    }.show()
            }
        }).attachToRecyclerView(binding.rvList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}