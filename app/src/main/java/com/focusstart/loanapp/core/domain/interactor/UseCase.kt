package com.focusstart.loanapp.core.domain.interactor

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import kotlinx.coroutines.*

/**
 * Абстракция Use Case (Interactor).
 * Данный класс выполняет работу в бэкграунде, а результат возвращает в главном потоке.
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, job: Job, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val backgroundJob = CoroutineScope(job + Dispatchers.IO).async(Dispatchers.IO) { run(params) }
        CoroutineScope(job + Dispatchers.Main).launch { onResult(backgroundJob.await()) }
    }

    //class None
}