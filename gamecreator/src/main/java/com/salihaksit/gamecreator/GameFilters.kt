package com.salihaksit.gamecreator

import javax.inject.Inject

class GameFilters @Inject constructor(private val play : Play) {

    fun filters(): List<BaseFilter> {
        return arrayListOf(
            FirstNumberDistinctFromAimFilter(),
            AimBiggerThanOneFilter(),
            MoreMoveThanDistinctElementsCountFilter(),
            AtLeastTwoDistinctElementFilter(),
            NotSolvableWithOneMoveFilter(play)
        )
    }
}