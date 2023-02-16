package com.kotlinplayground.basics

import com.kotlinplayground.functions.courseName
import com.kotlinplayground.functions.topLevelFunction

fun main() {
    val name = "Raghav"
    println(name)
//    name = "Ra"

    var age = 37
    println(age)
    age = 36
    println(age)

    val salary = 30000L
    println(salary)

    val course = "Kotlin Spring"
    println("course : $course and the course length is ${course.length}")

    val multiLine = "ABC \n DEF"
    println(multiLine)

    val multiLine1 = """
        ABC
        DEF
    """.trimIndent()
    val num = topLevelFunction()
    println("Num is : $num")
    println("courseName : $courseName")
}