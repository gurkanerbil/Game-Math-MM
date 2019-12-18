package com.salihaksit.mm

import com.salihaksit.domain.di.DaggerDomainComponent
import com.salihaksit.domain.di.DomainComponent
import com.salihaksit.mm.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    private val domainComponent: DomainComponent by lazy {
        DaggerDomainComponent
            .builder()
            .context(applicationContext)
            .build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .setDomainComponent(domainComponent)
            .build()
    }
}