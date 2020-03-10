package com.gvan.mapstest.di

import com.gvan.mapstest.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
ApiModule::class,
RepositoryModule::class,
LocalDataModule::class,
UiModule::class,
ViewModelModule::class
        ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApp(app: App) : Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)

}