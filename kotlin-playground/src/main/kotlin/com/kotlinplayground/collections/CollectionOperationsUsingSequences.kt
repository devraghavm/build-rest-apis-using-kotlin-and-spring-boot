package com.kotlinplayground.collections

import com.kotlinplayground.dataset.Course
import com.kotlinplayground.dataset.CourseCategory
import com.kotlinplayground.dataset.courseList

fun main() {
    val namesListUsingSequence = listOf("alex", "ben", "chloe")
        .asSequence()
        .filter { it.length >= 4 }
        .map { it.uppercase() }
        .toList()

    println("namesListUsingSequence : $namesListUsingSequence")
    val devPredicate = { c: Course -> c.category == CourseCategory.DEVELOPEMENT }

    exploreFilterUsingSequence(courseList = courseList(), devPredicate)
    val range = 1..1000_000_000
    range
        .asSequence()
        .map { it.toDouble() }
        .take(40)
        .forEach {
            println("Value is : $it")
        }
}


fun exploreFilterUsingSequence(courseList: MutableList<Course>, predicate: (Course) -> Boolean) {
    val developmentCourses = courseList
        .asSequence()
        .filter { predicate.invoke(it) }
        .forEach {
            println("Courses : $it")
        }
}
