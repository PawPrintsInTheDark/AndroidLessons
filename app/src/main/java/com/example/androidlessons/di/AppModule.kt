package com.example.androidlessons.di

import com.example.androidlessons.CurrencyRepository
import com.example.androidlessons.CurrencyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCurrencyRepository(): CurrencyRepository {
        return CurrencyRepositoryImpl()
    }
}
