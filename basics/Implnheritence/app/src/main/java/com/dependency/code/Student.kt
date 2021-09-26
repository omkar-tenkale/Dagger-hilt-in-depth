package com.dependency.code

class Student(val name:String) {

    fun study(topic:String){
        println("$name is studying $topic")
    }

    fun think(){
        println("$name is thinking")
    }

}