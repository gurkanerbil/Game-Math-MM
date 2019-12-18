package com.salihaksit.gamecreator

import javax.inject.Inject

class Play @Inject constructor() {

    fun calculate(firstPhrase: String, nextPhrase: String): String {

        val operator =
            if (nextPhrase.contains("<<")) "<<".toOperator() else nextPhrase[0].toString().toOperator()
        val nextNumber = if (nextPhrase.contains("<<")) 0 else nextPhrase[1].toString().toInt()

        return "" + operator.calculate(firstPhrase.toInt(), nextNumber)
    }

}