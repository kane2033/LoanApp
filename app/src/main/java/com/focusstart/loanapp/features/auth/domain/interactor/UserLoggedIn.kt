package com.focusstart.loanapp.features.auth.domain.interactor

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.core.domain.interactor.None
import com.focusstart.loanapp.core.domain.interactor.UseCase
import com.focusstart.loanapp.core.domain.repository.TokenRepository

import javax.inject.Inject

class UserLoggedIn
@Inject constructor(private val tokenRepository: TokenRepository) : UseCase<Boolean, None>() {
    override suspend fun run(params: None): Either<Failure, Boolean> {
        return Either.Right(tokenRepository.hasToken())
    }
}