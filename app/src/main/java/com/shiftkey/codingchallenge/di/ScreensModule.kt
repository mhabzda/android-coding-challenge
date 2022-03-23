package com.shiftkey.codingchallenge.di

import com.shiftkey.codingchallenge.ui.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ScreensModule {
    @ContributesAndroidInjector
    abstract fun provideListFragment(): ListFragment
}
