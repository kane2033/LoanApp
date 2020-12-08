package com.focusstart.loanapp.features.auth.domain.interactor

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.core.domain.interactor.UseCase
import com.focusstart.loanapp.features.auth.domain.entity.User
import com.focusstart.loanapp.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * [UseCase], использующийся для регистрации:
 * [AuthRepository] регистрирует пользователя.
 * */

class Register
@Inject constructor(private val repository: AuthRepository) :
    UseCase<Any, User>() {
    override suspend fun run(newUser: User): Either<Failure, Any> = repository.register(newUser)
}