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
import androidx.navigation.fragment.navArgs
import com.example.day2task.model.TaskDetail
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEditBinding

class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val args : EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val taskDetails = args.task


        Log.d("test", "test agrs ${taskDetails.title}")

        binding.edtTitle.setText(taskDetails.title)
        binding.editDesc.setText(taskDetails.description)

        binding.editButton.setOnClickListener {
            val editTitle = binding.edtTitle.text.toString().trim()
            val editDesc = binding.editDesc.text.toString().trim()
            val position = args.position

            if(editTitle.isNotEmpty() && editDesc.isNotEmpty()) {
                val taskDetail = TaskDetail(editTitle, editDesc)

                setFragmentResult("requestEditTask", bundleOf("editTask" to taskDetail, "position" to position))
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}