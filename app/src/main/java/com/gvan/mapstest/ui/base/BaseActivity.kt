package com.gvan.mapstest.ui.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gvan.mapstest.di.view_model.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, VM : ViewModel> : DaggerAppCompatActivity(){

    lateinit var binding: T
    lateinit var viewModel: VM
    @Inject
    lateinit var viewModeFactory : ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, provideBinding())
        viewModel = ViewModelProvider(this, viewModeFactory).get(provideViewModel())
        setupUI()
        setupViewModel()
    }

    abstract fun provideBinding(): Int

    abstract fun provideViewModel() : Class<VM>

    abstract fun setupUI()

    abstract fun setupViewModel()

}