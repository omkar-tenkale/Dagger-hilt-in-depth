package com.dependency.code.typeAbstract

import com.dependency.code.typeInterface.Named

abstract class Person(override val name:String) : Named {
    fun printNames(){
        println(name)
    }
}