package com.shiftkey.codingchallenge.di

import com.shiftkey.codingchallenge.model.ShiftRepository
import com.shiftkey.codingchallenge.model.ShiftRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun provideShiftRepository(impl: ShiftRepositoryImpl): ShiftRepository
}
