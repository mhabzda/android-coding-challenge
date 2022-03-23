package com.shiftkey.codingchallenge.di

import com.shiftkey.codingchallenge.model.ShiftApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()

    @Provides
    fun provideShiftApi(okHttpClient: OkHttpClient): ShiftApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ShiftApi::class.java)

    companion object {
        private const val BASE_URL = "https://staging-app.shiftkey.com/api/v2/"
    }
}
