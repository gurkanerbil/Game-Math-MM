package com.salihaksit.domain.di

import com.salihaksit.domain.GetGameUseCase
import com.salihaksit.domain.PlayGameUseCase
import com.salihaksit.domain.TimeUseCase
import com.salihaksit.gamecreator.Game
import com.salihaksit.gamecreator.Play
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetGameUseCase(game: Game): GetGameUseCase {
        return GetGameUseCase(game)
    }

    @Provides
    fun providePlayGameUseCase(play: Play): PlayGameUseCase {
        return PlayGameUseCase(play)
    }

    @Provides
    fun provideTimeUseCase(): TimeUseCase {
        return TimeUseCase()
    }
}