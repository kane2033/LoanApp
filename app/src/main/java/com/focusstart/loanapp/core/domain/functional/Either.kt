package com.focusstart.loanapp.core.domain.functional

/**
 * Функциональный тип, представляющий собой одно из двух значений, но никогда не оба.
 * Экземпляры [Either] это либо экземпляр [Left], либо [Right].
 * В данном случае, [Left] всегда представляет собой ошибку (Failure), а
 * [Right] хранит успешный результат..
 *
 * @see Left
 * @see Right
 */
sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>

    val isLeft get() = this is Left<L>

    /** Выполнение соответствующей функции в зависимости от наличия соответствующего типа*/
    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(a)
            is Right -> fnR(b)
        }

    fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
        when (this) {
            is Left -> Left(a)
            is Right -> fn(b)
        }


    //fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> = this.flatMap(fn.c(::right))
}