package com.focusstart.loanapp.features.loan.domain.interactor

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.core.domain.interactor.UseCase
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.entity.LoanConditions
import com.focusstart.loanapp.features.loan.domain.repository.LoanRepository
import javax.inject.Inject

class CreateLoan
@Inject constructor(private val loanRepository: LoanRepository) : UseCase<Loan, LoanConditions>() {
    override suspend fun run(conditions: LoanConditions): Either<Failure, Loan> {
        return loanRepository.createLoan(conditions)
    }
}