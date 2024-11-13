package com.example.day2task.views.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.day2task.views.adapter.ToDoAdapter
import com.example.day2task.views.customviews.CustomDialog
import com.example.day2task.views.customviews.TransferDataListener
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private var taskList = mutableListOf<String>()
    private lateinit var taskAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.statusBarColor = getColor(R.color.green)

        taskAdapter = ToDoAdapter(taskList, this)
        binding.rvList.adapter = taskAdapter

//        binding.btnSubmit.setOnClickListener {
//            val res = binding.edtText.text.toString().trim()
//            if(res.isNotEmpty()) {
//                taskList.add(res)
//                binding.edtText.text?.clear()
//                taskAdapter.notifyItemChanged(taskList.size-1)
//            } else {
//                Toast.makeText(this, "Please Enter some text!!", Toast.LENGTH_SHORT).show()
//            }
//        }

        binding.btnAdd.setOnClickListener {
            val customDialog = CustomDialog(this)
            customDialog.transferDataListener = object : TransferDataListener{
                override fun receiveData(task: String) {
                    taskList.add(task)
                    taskAdapter.notifyItemChanged(taskList.size - 1)
                }
            }
            customDialog.show()
        }

    }

//    override fun receiveData(task: String) {
//
//    }
}