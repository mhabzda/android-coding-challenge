package com.shiftkey.codingchallenge.di

import com.shiftkey.codingchallenge.model.ShiftApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideShiftApi(): ShiftApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ShiftApi::class.java)

    companion object {
        private const val BASE_URL = "https://staging-app.shiftkey.com/api/v2/"
    }
}
