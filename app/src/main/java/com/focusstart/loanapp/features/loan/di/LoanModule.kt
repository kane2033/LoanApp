package com.focusstart.loanapp.features.loan.di

import com.focusstart.loanapp.features.loan.data.datasource.LoanDataSource
import com.focusstart.loanapp.features.loan.data.datasource.LoanDataSourceImpl
import com.focusstart.loanapp.features.loan.data.network.LoanApi
import com.focusstart.loanapp.features.loan.data.network.LoanApiImpl
import com.focusstart.loanapp.features.loan.data.repository.LoanRepositoryImpl
import com.focusstart.loanapp.features.loan.domain.repository.LoanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped


@Module
@InstallIn(ActivityComponent::class)
object LoanModule {

    @Provides
    @ActivityScoped
    fun provideLoanApi(api: LoanApiImpl): LoanApi = api

    @Provides
    @ActivityScoped
    fun provideLoanDataSource(dataSource: LoanDataSourceImpl): LoanDataSource = dataSource

    @Provides
    @ActivityScoped
    fun provideLoanRepository(repositoryImpl: LoanRepositoryImpl): LoanRepository = repositoryImpl
}