package com.salihaksit.domain

import com.salihaksit.core.GameEntity
import com.salihaksit.gamecreator.Game
import io.reactivex.Observable
import javax.inject.Inject

class GetGameUseCase @Inject constructor(private val game: Game) {

    fun createNewGame() {
        game.getOptimumGame()
    }

    fun observeGameCreation(): Observable<GameEntity> {
        return game.source.map {
            GameEntity(
                it.first.toString(),
                it.second[0],
                it.second,
                it.second.subList(1, it.second.size).distinct()
            )
        }
    }

}