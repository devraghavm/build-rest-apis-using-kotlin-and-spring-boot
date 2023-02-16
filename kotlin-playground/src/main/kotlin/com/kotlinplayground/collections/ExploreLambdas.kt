package com.kotlinplayground.collections

/**
 * Higher Order Function (A function that takes function as an argument)
 */
fun calculate(
    x: Int, y: Int, op: (x: Int, y: Int) -> Int
): Int {
    return op(x, y)
}

fun main() {
    val addLambda = { x: Int -> x + x }

    val addResult = addLambda(3)
    println("addResult : $addResult")

    val multiplyLambda = { x: Int, y: Int ->
        println("x is $x and y is $y")
        x * y
    }

    val multiplyResult = multiplyLambda(3, 5)
    println("multiplyResult : $multiplyResult")

    val result = calculate(2, 5) { a, b -> a * b }
    println("Result is $result")

    val add = calculate(2, 5) { a, b -> a + b }
    println("Add result is $add")

}