package com.focusstart.loanapp.features.settings.domain.interactor

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.core.domain.interactor.None
import com.focusstart.loanapp.core.domain.interactor.UseCase
import com.focusstart.loanapp.core.domain.repository.TokenRepository
import javax.inject.Inject

class Logout
@Inject constructor(private val tokenRepository: TokenRepository) : UseCase<Unit, None>() {
    override suspend fun run(params: None): Either<Failure, Unit> =
            Either.Right(tokenRepository.deleteToken())
}