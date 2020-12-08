package com.focusstart.loanapp.core.data.network

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Безопасный вызов retrofit запроса с помощью [safeApiResult]
 * с возвратом тела запроса или [Failure].
 * @param call - сетевой запрос
 * */
@Singleton
class SafeApiCall
@Inject constructor(private val networkHandler: NetworkHandler) {

    // Получение результата запроса (парам. call)
    suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): Either<Failure, T> {
        if (!networkHandler.isNetworkAvailable()) return Either.Left(Failure.NetworkConnection)
        val response = call.invoke()
        return try {
            when (response.isSuccessful) {
                true -> Either.Right(response.body()!!)
                false -> Either.Left(Failure.RequestFailure(code = response.code()))
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.NetworkConnection)
        }
    }
}