package com.dependency.code

class Musician(name:String):Person(name) {
    fun performTask(instrument:String){
        println("$name is playing $instrument")
    }
}