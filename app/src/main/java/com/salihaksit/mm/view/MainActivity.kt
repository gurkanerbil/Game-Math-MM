package com.salihaksit.mm.view

import com.salihaksit.mm.R
import com.salihaksit.mm.databinding.ActivityMainBinding
import com.salihaksit.mm.viewmodel.BaseViewModel

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModelClass: Class<BaseViewModel>
        get() = BaseViewModel::class.java

    override fun init() {

    }

}