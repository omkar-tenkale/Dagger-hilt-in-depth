package com.dependency.code.types

import com.dependency.code.typeInterface.Named

class Cat(override val name:String): Named {
    fun meow(){
        println("$name is meowing...")
    }
}