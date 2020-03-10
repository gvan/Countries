package com.gvan.mapstest.di

import com.gvan.mapstest.data.api.CountriesService
import com.gvan.mapstest.data.local_data.LocalDataManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    val URL = "https://ajayakv-rest-countries-v1.p.rapidapi.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLogginingInterceptor: HttpLoggingInterceptor,
    localDataManager: LocalDataManager) : OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLogginingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .header("x-rapidapi-host", "ajayakv-rest-countries-v1.p.rapidapi.com")
                    .header("x-rapidapi-key", localDataManager.getRapidKey())

                val request = requestBuilder.build()
                chain.proceed(request)
            }

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideLogginingInterceptor(): HttpLoggingInterceptor {
        val interceptor =  HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideCountriesService(retrofit: Retrofit) : CountriesService {
        return retrofit.create(CountriesService::class.java);
    }

}
