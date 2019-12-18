package com.salihaksit.core

data class GameEntity(
    val endNumber: String,
    val firstNumber : String,
    val moveCount : Int,
    val steps: List<String> = mutableListOf(),
    val uniqueElements: List<String> = mutableListOf()
)