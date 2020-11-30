package com.focusstart.loanapp.core.di

import android.content.Context
import android.content.SharedPreferences
import com.focusstart.loanapp.core.data.repository.TokenRepositoryImpl
import com.focusstart.loanapp.core.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
* [Module], который используется всем приложением.
* */
@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    private const val REPOSITORY_KEY = "REPOSITORY"

    // SharedPreferences для репозитория токенов
    @Provides @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context):
            SharedPreferences = context.getSharedPreferences(REPOSITORY_KEY, Context.MODE_PRIVATE)

    // Репозиторий Bearer токенов реализуется с помощью SharedPreferences
    @Provides @Singleton
    fun provideTokenRepository(tokenRepository: TokenRepositoryImpl):
            TokenRepository = tokenRepository

}