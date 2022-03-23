package com.shiftkey.codingchallenge

import com.shiftkey.codingchallenge.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<App> =
        DaggerApplicationComponent.create()
}
