package com.focusstart.loanapp.features.auth.data.repository

import com.focusstart.loanapp.features.auth.data.datasource.AuthDataSource
import com.focusstart.loanapp.features.auth.domain.entity.User
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.features.auth.domain.repository.AuthRepository
import com.focusstart.loanapp.core.domain.exception.Failure
import javax.inject.Inject

/**
 * Реализация репозитория [AuthRepository],
 * возвращающая результат логина/регистрации пользователя
 * в UseCase.
* */
class AuthRepositoryImpl
    @Inject constructor(private val authDataSource: AuthDataSource): AuthRepository {

    // вот тут кидать Failure.UserNotFound????
    override suspend fun login(user: User): Either<Failure, String> = authDataSource.login(user)

    override suspend fun register(newUser: User): Either<Failure, Unit> {
        authDataSource.register(newUser)
        // Результат регистрации не нужен, ничего не возвращаем
        return Either.Right(Unit)
    }
}