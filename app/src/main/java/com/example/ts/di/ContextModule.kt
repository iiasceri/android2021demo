package com.example.ts.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn


@Module
@InstallIn
class ContextModule(private val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }
}