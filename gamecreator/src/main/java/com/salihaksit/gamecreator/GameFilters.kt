package com.salihaksit.gamecreator

import javax.inject.Inject

class GameFilters @Inject constructor() {

    fun filters(): List<BaseFilter> {
        return arrayListOf(
            FirstNumberDistinctFromAimFilter(),
            AimBiggerThanOneFilter(),
            MoreMoveThanDistinctElementsCountFilter(),
            AtLeastTwoDistinctElementFilter()
        )
    }
}