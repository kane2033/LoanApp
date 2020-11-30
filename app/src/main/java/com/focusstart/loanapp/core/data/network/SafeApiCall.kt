package com.focusstart.loanapp.core.data.network

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import retrofit2.Response

/**
 * Безопасный вызов retrofit запроса с помощью [safeApiResult]
 * с возвратом тела запроса или [Failure].
 * @param call - сетевой запрос
 * */
object SafeApiCall {

    // Получение результата запроса (парам. call)
    suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>) : Either<Failure, T> {
        val response = call.invoke()
        return try {
            when (response.isSuccessful) {
                true -> Either.Right(response.body()!!)
                false -> Either.Left(Failure.RequestFailure(code = response.code()))
            }
        }
        catch (exception: Throwable) {
            Either.Left(Failure.NetworkConnection)
        }
    }
}