package com.focusstart.loanapp.features.auth.domain.interactor

import com.focusstart.loanapp.features.auth.domain.entity.User
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.features.auth.domain.repository.AuthRepository
import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.interactor.UseCase
import com.focusstart.loanapp.core.domain.repository.TokenRepository
import javax.inject.Inject

/**
* [UseCase], использующийся для логина:
 * [AuthRepository] возвращает результат авторизации (токен),
 * [TokenRepository] сохраняет токен в память для дальнейшего использования
* */
class Login
    @Inject constructor(private val authRepository: AuthRepository,
                        private val tokenRepository: TokenRepository
                        ) : UseCase<String, User>() {
    override suspend fun run(user: User): Either<Failure, String> {
        val result = authRepository.login(user)
        // Сохраняем токен в репозиторий токенов, если не возникло ошибки
        result.either({}, { tokenRepository.saveToken(it) })
        return result
    }
}