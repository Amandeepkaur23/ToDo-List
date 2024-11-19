package com.example.day2task.views.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.day2task.model.TaskDetail
import com.example.day2task.viewmodel.ToDoViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.Editbutton.setOnClickListener {
            val position = args.position
            val detailTitle = binding.detailTitle.text.toString().trim()
            val detailDesc = binding.detailDesc.text.toString().trim()
            val taskDetail = TaskDetail(title = detailTitle, description = detailDesc)

            val action = DetailFragmentDirections.actionDetailFragmentToEditFragment(taskDetail, position)

            Navigation.findNavController(binding.root).navigate(
                action
            )
        }

        val taskDetails = args.task
        binding.detailTitle.setText(taskDetails.title)
        binding.detailDesc.setText(taskDetails.description)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}