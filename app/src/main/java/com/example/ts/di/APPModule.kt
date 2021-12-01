package com.example.ts.di

import android.content.Context
import com.example.ts.db.AppDatabase
import com.example.ts.db.LibraryDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APPModule {

    @Singleton
    @Provides
    fun getAppDB(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun getLibraryDao(appDatabase: AppDatabase): LibraryDataDao {
        return appDatabase.libraryDataDao()
    }
}