package com.salihaksit.gamecreator

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val game = Game()
        for(i in 0..4)
            game.getOptimumGame()
        assertEquals(4, 2 + 2)
    }
}
