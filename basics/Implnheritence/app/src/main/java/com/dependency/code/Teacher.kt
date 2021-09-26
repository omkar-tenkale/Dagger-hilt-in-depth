package com.dependency.code

class Teacher(name:String):Person(name) {
    fun teach(topic:String){
        println("$name is teaching $topic")
    }
}