package com.shiftkey.codingchallenge.di

import com.shiftkey.codingchallenge.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ScreensModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {
    companion object {
        fun create(): AndroidInjector<App> =
            DaggerApplicationComponent.create()
    }
}
