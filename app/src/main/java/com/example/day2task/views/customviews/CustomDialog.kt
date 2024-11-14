package com.example.day2task.views.customviews

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import com.example.myapplication.databinding.CustomDialogBinding

interface TransferDataListener {
    fun receiveData(task: String)
}

class CustomDialog(context: Context,
    private val isEdit: Boolean,
    private val addedTask: String
): Dialog(context) {
    private lateinit var binding: CustomDialogBinding

    var transferDataListener: TransferDataListener? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = CustomDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(isEdit){
            binding.customTextView.text = "Edit Task"
            binding.edtTask.setText(addedTask)
        } else {
            binding.customTextView.text = "Add Task"
        }

        binding.btnSave.setOnClickListener {
            val task = binding.edtTask.text.toString().trim()
            if(task.isNotEmpty()) {
                transferDataListener?.receiveData(task)
                dismiss()
            } else {
                Toast.makeText(context, "Please Enter Task Here...", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}