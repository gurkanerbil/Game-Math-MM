package com.salihaksit.mm.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.salihaksit.mm.R
import com.salihaksit.mm.databinding.FragmentGameBinding
import com.salihaksit.mm.viewmodel.GameVM

class GameFragment : BaseFragment<FragmentGameBinding, GameVM>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_game

    override fun init() {
        dataBinding.viewModel = viewModel

        viewModel.showTimeOutDialog.observe(this, Observer {
            if (it.not())
                return@Observer

            AlertDialog
                .Builder(activity!!)
                .setMessage(R.string.warning_time_out)
                .setCancelable(false)
                .setNegativeButton(R.string.warning_time_out_no) { _, _ -> findNavController().popBackStack() }
                .setPositiveButton(R.string.warning_time_out_yes) { _, _ -> viewModel.resetTimer() }
                .create()
                .show()
        })
    }

    override val viewModelClass: Class<GameVM>
        get() = GameVM::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.showTimer(arguments?.getBoolean("isGameWithTime") ?: false)
    }
}