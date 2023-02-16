package com.kotlinplayground.classes

object Authenticate {
    @JvmStatic
    fun authenticate(userName: String, password: String) {
        println("User authenticate for userName : $userName")
    }
}

fun main() {
    Authenticate.authenticate("Raghav", "abc")
}