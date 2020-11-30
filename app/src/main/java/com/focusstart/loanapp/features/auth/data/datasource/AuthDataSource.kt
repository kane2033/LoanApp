package com.focusstart.loanapp.features.auth.data.datasource

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.features.auth.data.model.RegisteredUserModel
import com.focusstart.loanapp.features.auth.domain.entity.User
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.core.domain.exception.Failure.RequestFailure

interface AuthDataSource {

    suspend fun login(user: User): Either<Failure, String>

    suspend fun register(user: User): Either<Failure, RegisteredUserModel>
}