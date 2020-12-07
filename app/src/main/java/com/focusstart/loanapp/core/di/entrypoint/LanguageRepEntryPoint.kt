package com.focusstart.loanapp.core.di.entrypoint

import com.focusstart.loanapp.core.data.repository.LanguageRepositoryImpl
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LanguageRepEntryPoint {
    val repository: LanguageRepositoryImpl
}