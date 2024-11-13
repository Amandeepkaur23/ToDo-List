package com.example.day2task.views.customviews

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import com.example.day2task.views.adapter.ToDoAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.CustomDialogBinding

interface TransferDataListener {
    fun receiveData(task: String)
}

class CustomDialog(context: Context): Dialog(context) {
    private lateinit var binding: CustomDialogBinding

    var transferDataListener: TransferDataListener? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = CustomDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

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