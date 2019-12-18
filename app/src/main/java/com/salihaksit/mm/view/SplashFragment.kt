package com.salihaksit.mm.view

import androidx.navigation.fragment.findNavController
import com.salihaksit.mm.R
import com.salihaksit.mm.databinding.FragmentSplashBinding
import com.salihaksit.mm.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : BaseFragment<FragmentSplashBinding, BaseViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_splash

    override fun init() {
        btnPlay.setOnClickListener { findNavController().navigate(R.id.action_splashFragment_to_gameFragment) }
    }

    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java

}