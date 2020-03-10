package com.gvan.mapstest.di

import android.content.Context
import com.gvan.mapstest.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(app: App) : Context {
        return app.applicationContext
    }

}