package com.salihaksit.gamecreator

import org.junit.Assert
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

        Assert.assertEquals(true, aimBiggerThanOneFilter.isValid(game))
        Assert.assertEquals(false, aimBiggerThanOneFilter.isValid(game2))
    }

    @Test
    fun test_FirstNumberDistinctFromAimFilter() {
        val game = Pair(60, arrayListOf("12", "+3", "*4"))
        val game2 = Pair(12, arrayListOf("12", "*3", "<<", "*4"))

        Assert.assertEquals(true, firstNumberDistinctFromAimFilter.isValid(game))
        Assert.assertNotEquals(true, firstNumberDistinctFromAimFilter.isValid(game2))
    }

    @Test
    fun test_MoreMoveThanDistinctElementsCountFilter() {
        val game = Pair(60, arrayListOf("12", "+3", "*4", "+3", "-3"))
        val game2 = Pair(12, arrayListOf("12", "*3", "<<", "*4"))

        Assert.assertEquals(true, moreMoveThanDistinctElementsCountFilter.isValid(game))
        Assert.assertNotEquals(true, moreMoveThanDistinctElementsCountFilter.isValid(game2))
    }

    @Test
    fun test_AtLeastTwoDistinctElementFilter() {
        val game = Pair(60, arrayListOf("12", "+3", "*4", "+3", "-3"))
        val game2 = Pair(12, arrayListOf("12", "*3", "<<", "*4"))
        val game3 = Pair(108, arrayListOf("12", "*3", "*3"))

        Assert.assertEquals(true, atLeastTwoDistinctElementFilter.isValid(game))
        Assert.assertEquals(true, atLeastTwoDistinctElementFilter.isValid(game2))
        Assert.assertNotEquals(true, atLeastTwoDistinctElementFilter.isValid(game3))
    }

    @Test
    fun test_NotSolvableWithOneMoveFilter() {
        val game = Pair(60, arrayListOf("12", "*5", "+4" , "-4"))
        val game2 = Pair(60, arrayListOf("12", "+4" , "-4", "*5"))
        val game3 = Pair(5, arrayListOf("12", "+4" , "<<", "*5" ))

        Assert.assertNotEquals(true, notSolvableWithOneMoveFilter.isValid(game))
        Assert.assertNotEquals(true, notSolvableWithOneMoveFilter.isValid(game2))
        Assert.assertEquals(true, notSolvableWithOneMoveFilter.isValid(game3))
    }
}