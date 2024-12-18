package com.example.day2task.views.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
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
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var taskList = mutableListOf<TaskDetail>()
    private lateinit var taskAdapter: ToDoAdapter
    private val toDoViewModel: ToDoViewModel by viewModels()

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

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }

        //Add data to db
        setFragmentResultListener("requestAddTask") { _, bundle ->
            val result = BundleCompat.getParcelable(bundle, "task", TaskDetail::class.java)
            if (result != null) {
                toDoViewModel.insertTask(result)
            } else {
                Log.d("test", "Received TaskDetail is null")
            }
        }

        toDoViewModel.taskLiveData.observe(viewLifecycleOwner, Observer {
            taskList.clear()
            taskList.addAll(it)
            taskAdapter.notifyDataSetChanged()
        })

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

                Snackbar.make(binding.rvList, "Deleted " + deletedTask.title, Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        toDoViewModel.insertTask(deletedTask)
                }.show()
            }
        }).attachToRecyclerView(binding.rvList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}