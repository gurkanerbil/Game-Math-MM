package com.salihaksit.mm.view

import android.os.Bundle
import com.salihaksit.mm.R
import com.salihaksit.mm.databinding.FragmentGameBinding
import com.salihaksit.mm.viewmodel.GameVM

class GameFragment : BaseFragment<FragmentGameBinding, GameVM>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_game

    override fun init() {
        dataBinding.viewModel = viewModel
    }

    override val viewModelClass: Class<GameVM>
        get() = GameVM::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setTimer(arguments?.getBoolean("isGameWithTime") ?: false)
    }
}