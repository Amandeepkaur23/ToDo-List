package com.example.day2task.views.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.day2task.model.TaskDetail
import com.example.myapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args : DetailFragmentArgs by navArgs()

    private var position: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val taskDetails = args.task
        binding.detailTitle.setText(taskDetails.title)
        binding.detailDesc.setText(taskDetails.description)

        binding.Editbutton.setOnClickListener {
            val position = args.position
            val detailTitle = binding.detailTitle.text.toString().trim()
            val detailDesc = binding.detailDesc.text.toString().trim()
            val taskDetail = TaskDetail(detailTitle, detailDesc)

            val action = DetailFragmentDirections.actionDetailFragmentToEditFragment(taskDetail, position)
            Navigation.findNavController(binding.root).navigate(
                action
            )
        }

        setFragmentResultListener("requestEditTask"){ requestKey, bundle ->
            val editTask = bundle.getParcelable<TaskDetail>("editTask")
            position = bundle.getInt("position")

            binding.detailTitle.text = editTask?.title
            binding.detailDesc.text = editTask?.description
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                val editTitle = binding.detailTitle.text.toString().trim()
                val editDesc = binding.detailDesc.text.toString().trim()

                val taskDetail = TaskDetail(editTitle, editDesc)

                // Handle the back button event
                if (taskDetail != null) {
                    setFragmentResult("requestDetailEditTask", bundleOf("detailTask" to taskDetail, "position" to position))
                    findNavController().popBackStack()
                } else {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}