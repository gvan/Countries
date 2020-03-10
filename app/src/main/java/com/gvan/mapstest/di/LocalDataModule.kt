package com.gvan.mapstest.di

import android.content.Context
import com.gvan.mapstest.data.local_data.LocalDataManager
import com.gvan.mapstest.data.local_data.LocalDataManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun provideLocalDataManager(context: Context) : LocalDataManager {
        return LocalDataManagerImpl(context)
    }

}