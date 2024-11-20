package com.example.day2task.views.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

        val taskDetails = args.task
        binding.detailTitle.setText(taskDetails.title)
        binding.detailDesc.setText(taskDetails.description)

        binding.Editbutton.setOnClickListener {
            val detailTitle = binding.detailTitle.text.toString().trim()
            val detailDesc = binding.detailDesc.text.toString().trim()

            taskDetails.title = detailTitle
            taskDetails.description = detailDesc

            val action = DetailFragmentDirections.actionDetailFragmentToEditFragment(taskDetails)
            Navigation.findNavController(binding.root).navigate(
                action
            )
        }

        val backPressedCallback = object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedCallback)

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}