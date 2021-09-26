package com.dependency.code

class Student(val name:String) {

    fun think(){
        println("$name is thinking")
    }

    fun study(topic:String){
        println("$name is studying $topic")
    }

}