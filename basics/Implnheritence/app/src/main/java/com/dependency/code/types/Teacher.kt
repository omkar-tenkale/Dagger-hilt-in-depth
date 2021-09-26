package com.dependency.code.types

import com.dependency.code.typeAbstract.Person

class Teacher(name:String): Person(name) {
    fun teach(topic:String){
        println("$name is teaching $topic")
    }
}