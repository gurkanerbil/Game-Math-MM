package com.salihaksit.mm.view

import com.salihaksit.mm.R
import com.salihaksit.mm.databinding.ActivityGameBinding
import com.salihaksit.mm.viewmodel.GameVM

class GameActivity : BaseActivity<ActivityGameBinding, GameVM>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_game
    override val viewModelClass: Class<GameVM>
        get() = GameVM::class.java

    override fun init() {
        dataBinding.viewModel = viewModel
    }

}