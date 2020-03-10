package com.gvan.mapstest.di

import com.gvan.mapstest.ui.main.MainActivity
import com.gvan.mapstest.ui.map.MapActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindMapActivity(): MapActivity

}