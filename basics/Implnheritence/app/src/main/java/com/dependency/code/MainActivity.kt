package com.dependency.code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dependency.code.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val studentsList : ArrayList<Student> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.apply {
            btnAddName.setOnClickListener {
                val studentName = edtTxtPersonName.text.toString()
                studentsList.add(Student(studentName))
            }
            btnPrintNames.setOnClickListener {
                printNames()
            }
            btnClearNames.setOnClickListener {
                studentsList.clear()
            }
        }
    }

    private fun printNames() {
        studentsList.forEach {
            it.name
        }
    }

}