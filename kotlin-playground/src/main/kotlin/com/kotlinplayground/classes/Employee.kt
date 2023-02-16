package com.kotlinplayground.classes

data class Employee(
    val id: Int,
    val name: String
)

fun main() {
    val employee = Employee(1, "Raghav")
    println(employee)
    val employee1 = Employee(1, "Raghav")
    println("Both objects are same: ${employee == employee1}")
    val employee2 = employee.copy(3, "Nikki")
    println(employee2)
}