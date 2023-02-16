package com.kotlinplayground.collections

fun main() {
    val names = listOf("Alex", "Ben", "Chloe")
    println("Names : $names")
//    names.add // cannot do that as this is immutable collection

    val namesMutable = mutableListOf("Alex", "Ben", "Chloe")
    println("namesMutable before : $namesMutable")
    namesMutable.add("Adam")
    println("namesMutable after : $namesMutable")

    val set = setOf("Alex", "Ben", "Chloe")
    println("set : $set")

    val mutableSet = mutableSetOf("Alex", "Ben", "Chloe")
    println("mutableSet : $mutableSet")
    mutableSet.add("Adam")
    println("mutableSet after : $mutableSet")

    val nameAgeMap = mapOf("Raghav" to 37, "Nikitha" to 31)
    println("nameAgeMap : $nameAgeMap")

    val mutableNameAgeMap = mutableMapOf("Raghav" to 37, "Nikitha" to 31)
    println("mutableNameAgeMap : $mutableNameAgeMap")
    mutableNameAgeMap["Honey"] = 23
    println("mutableNameAgeMap after : $mutableNameAgeMap")
}