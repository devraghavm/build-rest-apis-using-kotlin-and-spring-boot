package com.kotlinplayground.scopefunctions

import com.kotlinplayground.classes.Course
import com.kotlinplayground.classes.CourseCategory

fun main() {
//    exploreApply()
//    exploreAlso()
//    exploreLet()
//    exploreWith()
    exploreRun()
}

fun exploreRun() {
    var numbers: MutableList<Int>? = null
    val result = numbers.run {
        numbers = mutableListOf(1, 2, 3)
        numbers?.sum()
    }
    println("Run Result is : $result")

    val length = run {
        val name = "Raghav"
        println(name)
        name.length
    }
    println("Run length is : $length")
}

fun exploreWith() {
    val numbers = mutableListOf(1, 2, 3, 4, 5)
    val result = with(numbers) {
/*        println("Size is ${numbers.size}")
        val list = numbers.plus(6)
        list.sum()*/
        println("Size is $size}")
        plus(6)
        sum()
    }

    println("With Result is : $result")
}


fun exploreLet() {
    val numbers = mutableListOf(1, 2, 3, 4, 5)
    val result = numbers.map { it * 2 }.filter { it > 5 }.let {
        println(it)
        it.sum()
    }
    println(result)

    var name: String? = null
    name = "Raghav"
    val result1 = name?.let {
        println(it)
        it.uppercase()
    }
    println(result1)
}

fun exploreApply() { // Apply for any configuration changes
    val course = Course(1, "Python 3", "Raghav").apply {
        courseCategory = CourseCategory.DESIGN
    }
    println("course : $course")
}

fun exploreAlso() { // Also only for additional effects upon object construction
    val course = Course(1, "Python 3", "Raghav")
        .apply {
            courseCategory = CourseCategory.DESIGN
        }
        .also {
//        it.courseCategory = CourseCategory.DESIGN
            println("Course is $it")
        }
}
