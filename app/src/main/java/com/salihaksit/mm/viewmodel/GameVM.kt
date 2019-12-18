package com.salihaksit.mm.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
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

    val adapter = BaseRecyclerAdapter(cellList, this)
    val firstNumber = MutableLiveData<String>().apply { value = "" }
    val endNumber = MutableLiveData<String>().apply { value = "" }

    init {
        gameUseCase.observeGameCreation().subscribe({
            firstNumber.postValue(it.firstNumber)
            endNumber.postValue(it.endNumber)

            val size = it.uniqueElements.size
            cellList.forEachIndexed { index, cellViewEntity ->
                if (index < size)
                    cellViewEntity.option = it.uniqueElements[index]
                else
                    cellViewEntity.option = ""
            }

            cellList.shuffle()
            adapter.setList(cellList)
        }, { println(it) }).addTo(disposableList)

        newGame()
    }

    fun newGame() {
        gameUseCase.createNewGame()
    }

    override fun onItemClick(view: View, item: CellViewEntity) {
        if (item.option.isEmpty())
            return

        val tmp = playGameUseCase.getCalculatedPhrase(
            firstNumber.value!!,
            item.option
        )

        firstNumber.postValue(tmp)
    }
}