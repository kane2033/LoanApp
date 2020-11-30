package com.focusstart.loanapp.features.auth.di

import com.focusstart.loanapp.features.auth.data.datasource.AuthDataSource
import com.focusstart.loanapp.features.auth.data.datasource.AuthDataSourceImpl
import com.focusstart.loanapp.features.auth.data.network.AuthApi
import com.focusstart.loanapp.features.auth.data.network.AuthApiImpl
import com.focusstart.loanapp.features.auth.data.repository.AuthRepositoryImpl
import com.focusstart.loanapp.features.auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
* Модуль для авторизации, в котором
* указываются необходимые зависимости
* */
@Module
@InstallIn(ActivityComponent::class)
object AuthModule {

    @Provides @ActivityScoped
    fun provideAuthApi(api: AuthApiImpl): AuthApi = api

    @Provides @ActivityScoped
    fun provideAuthDataSource(dataSource: AuthDataSourceImpl): AuthDataSource = dataSource

    @Provides @ActivityScoped
    fun provideAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository = repositoryImpl
}