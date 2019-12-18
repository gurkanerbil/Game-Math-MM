package com.salihaksit.mm.di.module

import com.salihaksit.mm.view.GameActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeGameActivity(): GameActivity
}