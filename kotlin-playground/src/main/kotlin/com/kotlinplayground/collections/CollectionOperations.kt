package com.kotlinplayground.collections

import com.kotlinplayground.dataset.Course
import com.kotlinplayground.dataset.courseList

fun main() {
    val courseList = courseList()

    /*    val devPredicate = { c: Course -> c.category == CourseCategory.DEVELOPEMENT }
        val desPredicate = { c: Course -> c.category == CourseCategory.DESIGN }

    //    exploreFilter(courseList, desPredicate)

    //    exploreMap(courseList, devPredicate)
        val list = listOf(listOf(1, 2, 3), listOf(4, 5, 6))
        val mapResult = list.map { outerList ->
            outerList.map {
                it.toDouble()
            }
        }
        println("mapResult : $mapResult")

        val flatMapResult = list.flatMap { outerList ->
            outerList.map {
                it.toDouble()
            }
        }
        println("flatMapResult : $flatMapResult")

        val courses = exploreFlatMap(courseList, KAFKA)
        println("courses : $courses")*/

//    exploreHashMap()
    collections_nullability()
}

fun collections_nullability() {
    var list: MutableList<String>? = null
    list = mutableListOf()
    list?.add("Raghav")
    list?.forEach {
        println("Value is : $it")
    }

    val list1: List<String?> = listOf("Raghav", null, "Alex")
    list1.forEach {
        println("Value Length is : ${it?.length}")
    }
}

fun exploreHashMap() {
    val nameAgeMutableMap = mutableMapOf("Raghav" to 37, "Nikitha" to 31)
    nameAgeMutableMap
        .forEach { (k, v) ->
            println("key is $k and the values is $v")

        }

    val value = nameAgeMutableMap.getOrElse("Raghav1") { "abc" }
    println("Value is $value")

    val result = nameAgeMutableMap.containsKey("Raghav")
    println("result is $result")

    val filteredMap = nameAgeMutableMap.filterKeys { it.length > 5 }
        .map { it.key.uppercase() }
    println("filteredMap is $filteredMap")

    val maxAge = nameAgeMutableMap.maxByOrNull { it.value }
    println("maxAge is $maxAge")
}

fun exploreFlatMap(courseList: MutableList<Course>, kafka: String): List<String> {
    val kafkaCourses = courseList.flatMap { course ->
        val courseName = course.name
        course.topicsCovered.filter {
            it == kafka
        }.map {
            courseName
        }
    }

    return kafkaCourses
}

fun exploreMap(courseList: MutableList<Course>, predicate: (Course) -> Boolean) {
    val courses = courseList
        .filter(predicate)
        .map { "${it.name} - ${it.category}" }
        .forEach { println(it) }
    println("courses: $courses")
}

fun exploreFilter(courseList: MutableList<Course>, predicate: (Course) -> Boolean) {
    val developmentCourses = courseList
        .filter { predicate.invoke(it) }
        .forEach {
            println("Courses : $it")
        }
}
