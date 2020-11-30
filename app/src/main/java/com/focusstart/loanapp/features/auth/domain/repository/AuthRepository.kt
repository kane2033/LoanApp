package com.focusstart.loanapp.features.auth.domain.repository

import com.focusstart.loanapp.features.auth.domain.entity.User
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.core.domain.exception.Failure

interface AuthRepository {

    suspend fun login(user: User): Either<Failure, String>

    suspend fun register(newUser: User): Either<Failure, Unit>
}