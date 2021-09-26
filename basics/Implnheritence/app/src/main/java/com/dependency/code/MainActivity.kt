package com.dependency.code

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.children
import com.dependency.code.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val pplLst : ArrayList<Person> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.apply {
            btnAddName.setOnClickListener {
                val name = edtTxtPersonName.text.toString()
                addFunctionality(name)
                //studentsList.add(Student(studentName))
            }
            btnPrintNames.setOnClickListener {
                printNames()
            }
            btnClearNames.setOnClickListener {
                pplLst.clear()
            }
        }
    }

    private fun addFunctionality(name: String) {
        binding.personGroupId.children
            .toList().filter { (it as Chip).isChecked }
            .forEach {
                val type =(it as Chip).text.toString()
                when {
                    type.equals(resources.getString(R.string.student),true) -> {
                        pplLst.add(Student(name))
                    }
                    type.equals(resources.getString(R.string.musician),true) -> {
                        pplLst.add(Musician(name))
                    }
                    type.equals(resources.getString(R.string.teacher),true) -> {
                        pplLst.add(Teacher(name))
                    }
                }
            }
    }

    private fun printNames() {
        pplLst.forEach {
            println(it.name)
        }
    }


}