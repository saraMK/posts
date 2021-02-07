package com.example.postsapplication.strategyDP

import android.util.Log

/*
* behavior design pattern
* some functions do the same thing but in different ways so enable selecting an algorithm in runtime
* achieve open-close principle (SOLID) >> open class for extention but closed for modification
* */
fun main() {
 val addNums :Operations= AddOperation()
 val substractNums :Operations= SubstractOperation()
 val multiplyNums :Operations= MultiplyOperation()

 addNums.doOperation(10,2)
 substractNums.doOperation(10,2)
 multiplyNums.doOperation(10,2)
}

interface Operations {

    fun doOperation(num1: Int, num2: Int)
}

class AddOperation : Operations {
    override fun doOperation(num1: Int, num2: Int) {
        println(num1 + num2)
    }
}

class SubstractOperation : Operations {
 override fun doOperation(num1: Int, num2: Int) {
     println(num1 - num2)
 }
}

class MultiplyOperation : Operations {
 override fun doOperation(num1: Int, num2: Int) {
     println(num1 * num2)
 }
}