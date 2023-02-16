package com.kotlinplayground.functions

import java.time.LocalDate

fun main() {
    /*    val unit = printName("Raghav") // unit is equivalent to void in java
        println("unit is $unit")
        val result = addition(1, 2)
        println("Result is $result")
        val result1 = addition_approach1(1, 2)
        println("Result 1 is $result1")*/
    printPersonDetails("Raghav", "abc@gmail.com", LocalDate.parse("2000-01-01"))
    printPersonDetails(name = "Raghav", dob = LocalDate.parse("2000-01-01"))
    printPersonDetails(dob = LocalDate.parse("2000-01-01"), name = "Raghav", email = "abc@gmail.com")

}

fun addition(i1: Int, i2: Int): Int {
    return i1 + i2
}

fun addition_approach1(i1: Int, i2: Int) = i1 + i2

fun printPersonDetails(
    name: String,
    email: String = "",
    dob: LocalDate = LocalDate.now()
) {
    println("Name is $name and the email is $email and the dob is $dob")
}

fun printName(name: String) {
    println("Name is : $name")
}
