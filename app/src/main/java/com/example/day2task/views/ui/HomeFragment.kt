package com.example.day2task.views.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.day2task.model.TaskDetail
import com.example.day2task.views.adapter.ToDoAdapter
import com.example.day2task.views.customviews.CustomDialog
import com.example.day2task.views.customviews.TransferDataListener
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var taskList = mutableListOf<TaskDetail>()
    private lateinit var taskAdapter: ToDoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskAdapter = ToDoAdapter(taskList)
        binding.rvList.adapter = taskAdapter


//        //add task
//        binding.btnAdd.setOnClickListener {
//
//            val customDialog = CustomDialog(requireContext(), false, "")
//            customDialog.transferDataListener = object : TransferDataListener {
//                override fun receiveData(task: String) {
//                    taskList.add(task)
//                    taskAdapter.notifyItemChanged(taskList.size - 1)
//                }
//            }
//            customDialog.show()
//        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }

        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getParcelable<TaskDetail>("task")
            Log.d("test", "Received TaskDetail: $result")

            if (result != null) {
                taskList.add(result)
                taskAdapter.notifyItemChanged(taskList.size - 1)
            } else {
                Log.d("test", "Received TaskDetail is null")
            }
        }

        setFragmentResultListener("requestEditTask") { requestKey, bundle ->
            val editTask = bundle.getParcelable<TaskDetail>("editTask")
            val position = bundle.getInt("position")
            if(editTask != null){
                taskList[position] = editTask
                taskAdapter.notifyItemChanged(position)
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}