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
        firstNumber.postValue(gameEntity.firstNumber)
        endNumber.postValue(gameEntity.endNumber)
        remainingMoveCount.postValue(gameEntity.moveCount)

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

        if (remainingMoveCount.value == 0)
            return

        val tmp = playGameUseCase.getCalculatedPhrase(
            firstNumber.value!!,
            item.option
        )

        firstNumber.postValue(tmp)
        remainingMoveCount.postValue((remainingMoveCount.value!! - 1))
    }
}