package com.salihaksit.mm.di.component

import android.app.Application
import com.salihaksit.domain.di.DomainComponent
import com.salihaksit.mm.di.module.ActivityBuilderModule
import com.salihaksit.mm.di.module.AppModule
import com.salihaksit.mm.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class
    ],
    dependencies = [DomainComponent::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun setDomainComponent(domainComponent: DomainComponent): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication?)
}