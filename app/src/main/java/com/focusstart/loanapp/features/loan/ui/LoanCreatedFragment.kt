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
import com.focusstart.loanapp.features.loan.presentation.LoanCreateViewModel
import com.focusstart.loanapp.features.loan.presentation.LoanFieldsSetter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_loan_created.*

@AndroidEntryPoint
class LoanCreatedFragment : BaseFragment() {

    override val viewModel: LoanCreateViewModel by navGraphViewModels(R.id.loanCreationGraph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_loan_created, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolBar.setupWithNavController(
            findNavController(),
            AppBarConfiguration(findNavController().graph)
        )

        // Установка условий займа
        viewModel.createdLoan.observe(viewLifecycleOwner, { loan ->
            statusImageView.setImageResource(LoanFieldsSetter.setImageStatus(loan.state))
            statusTextView.setText(LoanFieldsSetter.setTextStatus(loan.state))
            dateView.text = getString(R.string.loan_date, LoanFieldsSetter.setDate(loan.date))
            nameView.text = getString(R.string.full_name, loan.lastName, loan.firstName)
            phoneView.text = loan.phoneNumber
            amountView.text = getString(R.string.loan_amount, loan.amount)
            periodView.text = getString(R.string.loan_period, loan.period)
            percentView.text = getString(R.string.loan_percent, loan.percent)
        })

        okButton.setOnClickListener {
            findNavController().navigate(R.id.action_loanCreatedFragment_to_loanMasterDetailGraph)
        }

        // Пока что нет специфичных ошибок для фрагмента
        handleFailure({}, {})
    }
}