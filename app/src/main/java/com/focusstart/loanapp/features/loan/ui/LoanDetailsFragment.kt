package com.focusstart.loanapp.features.loan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.focusstart.loanapp.R
import com.focusstart.loanapp.core.ui.BaseFragment
import com.focusstart.loanapp.features.loan.presentation.LoanFieldsSetter
import com.focusstart.loanapp.features.loan.presentation.LoansViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_loan_details.*

@AndroidEntryPoint
class LoanDetailsFragment : BaseFragment() {

    override val viewModel: LoansViewModel by navGraphViewModels(R.id.loanMasterDetailGraph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_loan_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolBar.setupWithNavController(
            findNavController(),
            AppBarConfiguration(findNavController().graph)
        )

        // Установка всех полей займа
        viewModel.selectedLoan.observe(viewLifecycleOwner, { loan ->
            statusImageView.setImageResource(LoanFieldsSetter.setImageStatus(loan.state))
            statusTextView.setText(LoanFieldsSetter.setTextStatus(loan.state))
            dateView.text = getString(R.string.loan_date, LoanFieldsSetter.setDate(loan.date))
            nameView.text = getString(R.string.full_name, loan.lastName, loan.firstName)
            phoneView.text = loan.phoneNumber
            amountView.text = getString(R.string.loan_amount, loan.amount)
            periodView.text = getString(R.string.loan_period, loan.period)
            percentView.text = getString(R.string.loan_percent, loan.percent)
            repaymentView.text =
                getString(R.string.loan_repayment, loan.repaymentAmount, loan.repaymentDate)
        })

        // Пока что нет специфичных ошибок для фрагмента
        handleFailure({}, {})
    }
}