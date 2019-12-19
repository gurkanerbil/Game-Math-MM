package com.salihaksit.mm.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.salihaksit.core.GameEntity
import com.salihaksit.domain.GetGameUseCase
import com.salihaksit.domain.PlayGameUseCase
import com.salihaksit.mm.data.CellViewEntity
import com.salihaksit.mm.view.adapter.BaseRecyclerAdapter
import com.salihaksit.mm.view.adapter.ItemClickListener
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class GameVM @Inject constructor(
    private val gameUseCase: GetGameUseCase,
    private val playGameUseCase: PlayGameUseCase
) : BaseViewModel(),
    ItemClickListener<CellViewEntity> {

    private val cellList = MutableList(9) { CellViewEntity("") }
    private lateinit var gameEntity: GameEntity

    val adapter = BaseRecyclerAdapter(cellList, this)
    val firstNumber = MutableLiveData<String>().apply { value = "" }
    val endNumber = MutableLiveData<String>().apply { value = "" }
    val remainingMoveCount = MutableLiveData<Int>().apply { value = 0 }
    val scheduleLayoutAnimation = MutableLiveData<Boolean>()
    val infoStatus = MutableLiveData<Int>().apply { value = NO_INFO }

    init {
        gameUseCase.observeGameCreation().subscribe({
            gameEntity = it
            setGame()
        }, { println(it) }).addTo(disposableList)

        newGame()
    }

    fun newGame() {
        gameUseCase.createNewGame()
    }

    fun restart() {
        setGame()
    }

    private fun setGame() {
        infoStatus.value = NO_INFO

        firstNumber.value = gameEntity.firstNumber
        endNumber.value = gameEntity.endNumber
        remainingMoveCount.value = gameEntity.moveCount

        val size = gameEntity.uniqueElements.size
        cellList.forEachIndexed { index, cellViewEntity ->
            if (index < size)
                cellViewEntity.option = gameEntity.uniqueElements[index]
            else
                cellViewEntity.option = ""
        }

        cellList.shuffle()
        adapter.setList(cellList)
        scheduleLayoutAnimation.value = true
    }

    override fun onItemClick(view: View, item: CellViewEntity) {
        if (item.option.isEmpty())
            return

        if (infoStatus.value != NO_INFO)
            return

        if (remainingMoveCount.value == 0)
            return

        val tmp = playGameUseCase.getCalculatedPhrase(
            firstNumber.value!!,
            item.option
        )

        firstNumber.value = tmp
        remainingMoveCount.value = (remainingMoveCount.value!! - 1)

        if (tmp == endNumber.value) {
            infoStatus.value = SUCCESS_INFO
        } else if (remainingMoveCount.value == 0) {
            infoStatus.value = WARNING_INFO
        }

    }

    companion object{
        const val NO_INFO = 0
        const val SUCCESS_INFO = 1
        const val WARNING_INFO = 2
    }
}