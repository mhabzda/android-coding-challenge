package com.shiftkey.codingchallenge.di

import com.shiftkey.codingchallenge.App
import com.shiftkey.codingchallenge.di.viewmodel.ViewModelsModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ScreensModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelsModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {
    companion object {
        fun create(): AndroidInjector<App> =
            DaggerApplicationComponent.create()
    }
}
