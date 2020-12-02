package com.focusstart.loanapp.features.loan.domain.interactor

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.core.domain.interactor.None
import com.focusstart.loanapp.core.domain.interactor.UseCase
import com.focusstart.loanapp.features.loan.domain.entity.LoanConditions
import com.focusstart.loanapp.features.loan.domain.repository.LoanRepository
import javax.inject.Inject

class GetLoansConditions
@Inject constructor(private val loanRepository: LoanRepository) : UseCase<LoanConditions, None>() {
    override suspend fun run(params: None): Either<Failure, LoanConditions> {
        return loanRepository.getLoanConditions()
    }
}