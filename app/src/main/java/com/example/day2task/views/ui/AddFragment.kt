package com.example.day2task.views.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.day2task.model.TaskDetail
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

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

            Log.d("test", title)

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val taskDetail = TaskDetail(title, description)

                Log.d("test", " taskDetail is ${taskDetail.description}")
                setFragmentResult("requestKey", bundleOf("task" to taskDetail))
                findNavController().popBackStack()
            } else {
                Log.d("test", "field are empty")
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}