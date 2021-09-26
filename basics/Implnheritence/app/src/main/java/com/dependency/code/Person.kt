package com.dependency.code

abstract class Person(val name:String) {
    fun think(){
        println("$name is thinking")
    }
}