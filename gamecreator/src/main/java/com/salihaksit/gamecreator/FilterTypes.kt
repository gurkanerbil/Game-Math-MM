package com.salihaksit.gamecreator

abstract class BaseFilter {
    abstract fun isValid(game: Pair<Int, List<String>>): Boolean
}

class AimBiggerThanOneFilter : BaseFilter() {
    override fun isValid(game: Pair<Int, List<String>>): Boolean {
        return game.first > 1
    }
}

class FirstNumberDistinctFromAimFilter : BaseFilter() {
    override fun isValid(game: Pair<Int, List<String>>): Boolean {
        return game.first != game.second[0].toInt()
    }
}

class MoreMoveThanDistinctElementsCountFilter : BaseFilter() {
    override fun isValid(game: Pair<Int, List<String>>): Boolean {
        return game.second.size != game.second.distinct().size
    }
}

class AtLeastTwoDistinctElementFilter : BaseFilter() {
    override fun isValid(game: Pair<Int, List<String>>): Boolean {
        return game.second.slice(1 until game.second.size).distinct().size > 1
    }
}

class NotSolvableWithOneMoveFilter(private val play: Play) : BaseFilter() {

    override fun isValid(game: Pair<Int, List<String>>): Boolean {
        val firstNumber = game.second[0]
        val distinctElements = game.second.slice(1 until game.second.size)

        distinctElements.forEach {
            if (play.calculate(firstNumber, it) == game.first.toString())
                return false
        }

        return true
    }
}