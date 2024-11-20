package com.example.day2task.views.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.day2task.model.TaskDetail
import com.example.day2task.viewmodel.ToDoViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    //private val toDoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            val title = binding.addTitle.text.toString().trim()
            val description = binding.addDesc.text.toString().trim()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val taskDetail = TaskDetail(title = title, description = description)
                //val taskId = toDoViewModel.insertTask(taskDetail)
                //taskDetail.id = taskId
                setFragmentResult("requestAddTask", bundleOf("task" to taskDetail))
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Please enter title and desc!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}