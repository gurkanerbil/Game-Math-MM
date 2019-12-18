package com.salihaksit.domain

import com.salihaksit.gamecreator.Play
import javax.inject.Inject

class PlayGameUseCase @Inject constructor(private val play: Play) {

    fun getCalculatedPhrase(firstPhrase: String, nextPhrase: String): String {
        return play.calculate(firstPhrase, nextPhrase)
    }
}