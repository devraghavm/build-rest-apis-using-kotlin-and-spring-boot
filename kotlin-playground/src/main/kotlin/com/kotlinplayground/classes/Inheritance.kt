package com.kotlinplayground.classes

// Does not support multiple inheritance
open class User(val name: String) {
    open var isLoggedIn: Boolean = false
    open fun login() {
        println("Inside user login")
    }

    private fun secret() {
        println("Inside secret")
    }

    protected open fun logout() {
        println("Inside secret")
    }
}

class Student(name: String) : User(name) {
    override var isLoggedIn: Boolean = false

    companion object {
        const val noOfEnrolledCourses = 10
        fun country() = "USA"
    }

    override fun login() {
        println("Inside student login")
        super.login()
    }

    public override fun logout() {
        super.logout()
        println("Inside student logout")
    }
}

class Instructor(name: String) : User(name)

fun main() {
    val student = Student("Raghav")
    println("name is  : ${student.name}")
    student.login()
    student.isLoggedIn = true
    println("Logged in values is : ${student.isLoggedIn}")

    println("noOfEnrolledCourses is : ${Student.noOfEnrolledCourses}")

    val country = Student.country()
    println("Country is : $country")


    val instructor = Instructor("Dilip")
    println("name is  : ${instructor.name}")
    instructor.login()


    val student1 = Student("Raghav")
    student1.logout()

}