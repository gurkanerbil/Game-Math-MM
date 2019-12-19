package com.salihaksit.gamecreator

import kotlin.random.Random

sealed class Operator(open val type: String = "", open val calculate: (Int, Int) -> Int)

data class ADDITION(
    override val type: String = "+",
    override val calculate: (Int, Int) -> Int = { i: Int, i1: Int -> i + i1 }
) : Operator(type, calculate)

data class SUBTRACTION(
    override val type: String = "-",
    override val calculate: (Int, Int) -> Int = { i: Int, i1: Int -> i - i1 }
) : Operator(type, calculate)

data class MULTIPLICATION(
    override val type: String = "*",
    override val calculate: (Int, Int) -> Int = { i: Int, i1: Int -> i * i1 }
) : Operator(type, calculate)

data class DIVISION(
    override val type: String = "/",
    override val calculate: (Int, Int) -> Int = { i: Int, i1: Int -> i / i1 }
) : Operator(type, calculate)

data class CUT(
    override val type: String = "<<",
    override val calculate: (Int, Int) -> Int = { i: Int, _: Int -> i / 10 }
) : Operator(type, calculate)


fun String.toOperator(): Operator {
    return when (this) {
        "+" -> ADDITION()
        "-" -> SUBTRACTION()
        "*" -> MULTIPLICATION()
        "/" -> DIVISION()
        "<<" -> CUT()
        else -> ADDITION()
    }
}

fun Operator.isReverseWith(operator: Operator): Boolean {
    return when (this) {
        is ADDITION -> operator == SUBTRACTION()
        is SUBTRACTION -> operator == ADDITION()
        is MULTIPLICATION -> operator == DIVISION()
        is DIVISION -> operator == MULTIPLICATION()
        else -> false
    }
}

fun Operator.getNonReversedOperator(): Operator {
    val strongOperators = arrayListOf(MULTIPLICATION(), DIVISION(), CUT())
    val weakOperators = arrayListOf(ADDITION(), SUBTRACTION(), CUT())
    return when (this) {
        is ADDITION, is SUBTRACTION -> strongOperators[Random.nextInt(strongOperators.size)]
        is MULTIPLICATION, is DIVISION -> weakOperators[Random.nextInt(weakOperators.size)]
        else -> CUT()
    }
}
