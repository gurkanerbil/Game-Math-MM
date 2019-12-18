package com.salihaksit.mm.di.module

import com.salihaksit.mm.view.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun injectSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    internal abstract fun injectGameFragment(): GameFragment

}