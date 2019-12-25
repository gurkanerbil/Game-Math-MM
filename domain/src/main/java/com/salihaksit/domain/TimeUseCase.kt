package com.salihaksit.domain

import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TimeUseCase @Inject constructor() {

    fun getCountDownTimer(gameDuration: Long): Observable<Long> {
        return Observable
            .interval(1, TimeUnit.SECONDS)
            .take(gameDuration + 1)
            .map { gameDuration - it }
    }
}