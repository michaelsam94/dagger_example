package com.example.dagger_example

import android.app.Application

class MyApplication: Application() {

    val appComponent by lazy {
        initAppComponent()
    }

    open fun initAppComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}