package com.salihaksit.domain.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [UseCaseModule::class]
)
interface DomainComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): DomainComponent
    }

    fun inject(context: Context)
}