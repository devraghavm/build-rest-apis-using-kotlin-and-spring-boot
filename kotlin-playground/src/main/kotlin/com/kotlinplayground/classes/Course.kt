@file:JvmName("CourseUtils")

package com.kotlinplayground.classes

import com.raghav.kotlinplayground.CourseJava

data class Course @JvmOverloads
constructor(
    val id: Int,
    val name: String,
    val author: String,
    var courseCategory: CourseCategory = CourseCategory.DEVELOPMENT
) {
    @JvmField // makes this a field instead of an instance variable
    var noOfCourses = 10

    companion object {
        @JvmStatic
        fun printName2(name: String = "default") {
            println("name: $name")
        }
    }
}

enum class CourseCategory {
    DEVELOPMENT,
    BUSINESS,
    DESIGN,
    MARKETING
}

@JvmName("printName1")
@JvmOverloads
fun printName(name: String = "default") {
    println("name: $name")
}

fun main() {
    val course = Course(1, "Python 3", "Raghav")
    println(course)
    val course1 = Course(2, "Python 3", "Raghav")
    println("checking object equality : ${course == course1}")

    val course3 = course1.copy(id = 3, author = "Nikitha")
    println(course3)

    val marketingCourse = Course(1, "DevOps", "Raghav", CourseCategory.MARKETING)
    println(marketingCourse)

    val courseJava = CourseJava(2, "Python 3", "Raghav")
    println("courseJava : $courseJava")
    courseJava.id = 3
    courseJava.name = "abc"
    println("courseJava : $courseJava")


}