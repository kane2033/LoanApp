package com.focusstart.loanapp.features.loan.domain.interactor

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.core.domain.interactor.UseCase
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.entity.LoanCreated
import com.focusstart.loanapp.features.loan.domain.repository.LoanRepository
import javax.inject.Inject

class CreateLoan
@Inject constructor(private val loanRepository: LoanRepository) : UseCase<Loan, LoanCreated>() {
    override suspend fun run(loan: LoanCreated): Either<Failure, Loan> {
        return loanRepository.createLoan(loan)
    }
}