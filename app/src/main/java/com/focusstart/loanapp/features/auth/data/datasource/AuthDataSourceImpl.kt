package com.focusstart.loanapp.features.auth.data.datasource

import com.focusstart.loanapp.core.data.network.SafeApiCall
import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.features.auth.data.model.RegisteredUserModel
import com.focusstart.loanapp.features.auth.data.network.AuthApi
import com.focusstart.loanapp.features.auth.domain.entity.User
import javax.inject.Inject

/**
 * Имплементация [AuthDataSource],
 * использующая api для получения данных.
 * */
class AuthDataSourceImpl
@Inject constructor(
    private val authApi: AuthApi,
    private val safeApiCall: SafeApiCall
) : AuthDataSource {

    override suspend fun login(user: User): Either<Failure, String> =
        safeApiCall.safeApiResult { authApi.loginAsync(user) }

    override suspend fun register(user: User): Either<Failure, RegisteredUserModel> =
        safeApiCall.safeApiResult { authApi.registerAsync(user) }
}