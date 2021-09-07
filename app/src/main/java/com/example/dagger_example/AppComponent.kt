package com.example.dagger_example

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}