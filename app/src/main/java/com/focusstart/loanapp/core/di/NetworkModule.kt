package com.focusstart.loanapp.core.di

import com.focusstart.loanapp.BuildConfig
import com.focusstart.loanapp.core.data.network.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

/**
* [Module], предоставляющий сетевые компоненты
 * (в данном случае Retrofit).
* */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://focusapp-env.eba-xm2atk2z.eu-central-1.elasticbeanstalk.com/"

    @Provides
    @Singleton
    fun provideRetrofit(
            authInterceptor: AuthInterceptor
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client(authInterceptor))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    private fun client(
            authInterceptor: AuthInterceptor
    ): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(authInterceptor)
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

}