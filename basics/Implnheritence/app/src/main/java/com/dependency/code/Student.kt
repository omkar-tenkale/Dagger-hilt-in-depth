package com.dependency.code

class Student (name:String) : Person(name){
    fun study(subject:String){
        println("$name is studying $subject")
    }
}