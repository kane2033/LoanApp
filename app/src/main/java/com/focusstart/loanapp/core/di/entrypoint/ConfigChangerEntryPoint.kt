package com.focusstart.loanapp.core.di.entrypoint

import com.focusstart.loanapp.core.infrastructure.ConfigChangerImpl
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ConfigChangerEntryPoint {
    val configChanger: ConfigChangerImpl
}