package com.focusstart.loanapp.features.settings.domain.interactor

import android.content.Context
import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.core.domain.infrastructure.ConfigChanger
import com.focusstart.loanapp.core.domain.interactor.UseCase
import com.focusstart.loanapp.core.domain.repository.LanguageRepository
import javax.inject.Inject

class SetLanguage
@Inject constructor(private val configChanger: ConfigChanger,
                    private val languageRepository: LanguageRepository) : UseCase<Any, Pair<Context, String>>() {

    override suspend fun run(params: Pair<Context, String>): Either<Failure, Any> {
        languageRepository.setAppLanguage(params.second)
        return Either.Right(configChanger.updateLocale(params.first, params.second))
    }
}