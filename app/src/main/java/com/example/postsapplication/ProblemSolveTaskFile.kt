package com.example.postsapplication

fun main(args: Array<String>) {
    val list=listOf(1,3,5,6,8,9,2,9,10,4)
   val numbers1 =findTwoElement(list.toMutableList(),list.size)
    println("$numbers1")

    // another solution
    val numbers2 =findTwoElement2(list.toMutableList(),list.size)
    println("$numbers2")
 }
fun findTwoElement(list:MutableList<Int>,size:Int):List<Int>{
    var missingNumber = 0
    var twiceNumber = 0
    list.sort()
    list.forEachIndexed list@{ index, i ->
        if (index == size-1) {
            if (i != size)
                missingNumber = size
        }  else {
            // check duplicate
            if (i == list[index + 1]) {
                twiceNumber = i
            } else if (i+1 != list[index + 1]) {
                missingNumber = i + 1
            }
        }
        if (missingNumber != 0 && twiceNumber != 0)
            return@list
    }

    return listOf(twiceNumber,missingNumber)


}

fun findTwoElement2(list:MutableList<Int>,size:Int):List<Int>{
    var list2 = (1..size).toMutableList()
    list2.removeAll(list)
    val missingNumber=list2[0]
    val set=list.groupingBy { it }.eachCount().filter { it.value>1 }.keys
    val twiceNumber=set.first()
    return listOf(twiceNumber,missingNumber)
}