package com.salihaksit.gamecreator

import org.junit.Assert.*
import org.junit.Test

class FilterTypesTest {

    private val aimBiggerThanOneFilter = AimBiggerThanOneFilter()
    private val firstNumberDistinctFromAimFilter = FirstNumberDistinctFromAimFilter()
    private val moreMoveThanDistinctElementsCountFilter = MoreMoveThanDistinctElementsCountFilter()
    private val atLeastTwoDistinctElementFilter = AtLeastTwoDistinctElementFilter()

    private val play = Play()
    private val notSolvableWithOneMoveFilter = NotSolvableWithOneMoveFilter(play)

    @Test
    fun test_AimBiggerThanOneFilter() {
        val game = Pair(60, arrayListOf("12", "+3", "*4"))
        val game2 = Pair(1, arrayListOf("12", "/3", "-3"))

        assertEquals(true, aimBiggerThanOneFilter.isValid(game))
        assertEquals(false, aimBiggerThanOneFilter.isValid(game2))
    }

    @Test
    fun test_FirstNumberDistinctFromAimFilter() {
        val game = Pair(60, arrayListOf("12", "+3", "*4"))
        val game2 = Pair(12, arrayListOf("12", "*3", "<<", "*4"))

        assertEquals(true, firstNumberDistinctFromAimFilter.isValid(game))
        assertNotEquals(true, firstNumberDistinctFromAimFilter.isValid(game2))
    }

    @Test
    fun test_MoreMoveThanDistinctElementsCountFilter() {
        val game = Pair(60, arrayListOf("12", "+3", "*4", "+3", "-3"))
        val game2 = Pair(12, arrayListOf("12", "*3", "<<", "*4"))

        assertEquals(true, moreMoveThanDistinctElementsCountFilter.isValid(game))
        assertNotEquals(true, moreMoveThanDistinctElementsCountFilter.isValid(game2))
    }

    @Test
    fun test_AtLeastTwoDistinctElementFilter() {
        val game = Pair(60, arrayListOf("12", "+3", "*4", "+3", "-3"))
        val game2 = Pair(12, arrayListOf("12", "*3", "<<", "*4"))
        val game3 = Pair(108, arrayListOf("12", "*3", "*3"))

        assertEquals(true, atLeastTwoDistinctElementFilter.isValid(game))
        assertEquals(true, atLeastTwoDistinctElementFilter.isValid(game2))
        assertNotEquals(true, atLeastTwoDistinctElementFilter.isValid(game3))
    }

    @Test
    fun test_NotSolvableWithOneMoveFilter() {
        val game = Pair(60, arrayListOf("12", "*5", "+4" , "-4"))
        val game2 = Pair(60, arrayListOf("12", "+4" , "-4", "*5"))
        val game3 = Pair(5, arrayListOf("12", "+4" , "<<", "*5" ))

        assertNotEquals(true, notSolvableWithOneMoveFilter.isValid(game))
        assertNotEquals(true, notSolvableWithOneMoveFilter.isValid(game2))
        assertEquals(true, notSolvableWithOneMoveFilter.isValid(game3))
    }
}