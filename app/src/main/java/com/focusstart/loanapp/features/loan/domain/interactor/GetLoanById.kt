package com.focusstart.loanapp.features.loan.domain.interactor

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.core.domain.interactor.UseCase
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.repository.LoanRepository
import javax.inject.Inject

class GetLoanById
@Inject constructor(private val loanRepository: LoanRepository) : UseCase<Loan, Int>() {
    override suspend fun run(id: Int): Either<Failure, Loan> {
        return loanRepository.getLoanById(id)
    }
}