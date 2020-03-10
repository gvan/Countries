package com.gvan.mapstest.di

import androidx.lifecycle.ViewModel
import com.gvan.mapstest.di.view_model.ViewModelKey
import com.gvan.mapstest.ui.main.view_model.MainViewModel
import com.gvan.mapstest.ui.map.view_model.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(viewModel: MapViewModel) : ViewModel

}