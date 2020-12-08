package com.focusstart.loanapp.features.loan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.focusstart.loanapp.R
import com.focusstart.loanapp.core.ui.BaseFragment
import com.focusstart.loanapp.features.loan.domain.entity.LoanCreated
import com.focusstart.loanapp.features.loan.presentation.LoanCreateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_loan_creation.*

@AndroidEntryPoint
class LoanCreationFragment : BaseFragment() {

    override val viewModel: LoanCreateViewModel by navGraphViewModels(R.id.loanCreationGraph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_loan_creation, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val barStep = 500 // Шаг ползунка выбора суммы займа (в рублях)

        // Установка условий займа
        viewModel.loanConditions.observe(viewLifecycleOwner, { conditions ->
            minAmountView.text = getString(R.string.loan_create_amount, barStep)
            maxAmountView.text = getString(R.string.loan_create_amount, conditions.maxAmount)
            setAmountBar.max = 1 + conditions.maxAmount / barStep
            setAmountBar.min = 1
            periodView.text = getString(R.string.loan_create_period, conditions.period)
            percentView.text = getString(R.string.loan_create_percent, conditions.percent)
        })

        setAmountBar.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                amountView.text = getString(R.string.loan_create_amount_number,
                        progress * barStep)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                val percent = viewModel.loanConditions.value?.percent ?: 1.0
                val amount = seek.progress * barStep
                val percentSum = percent * amount / 100
                percentSumView.text = getString(R.string.loan_create_percent_number, percentSum)
                amountResultView.text = getString(R.string.loan_create_total_number,
                        amount + percentSum)
            }
        })

        createLoanButton.setOnClickListener {
            viewModel.loanConditions.value?.let { conditions ->
                val loan = LoanCreated(
                    setAmountBar.progress,
                    conditions.period,
                    conditions.percent,
                    lastNameInputView.editText!!.text.toString(),
                    firstNameInputView.editText!!.text.toString(),
                    phoneInputView.editText!!.text.toString()
                )
                viewModel.createLoan(loan)
            }
        }

        // Когда заём создался, переходим на фргамент с деталями о созданном займе
        viewModel.createdLoan.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_loanCreationFragment_to_loanCreatedFragment)
        })

        // Пока что нет специфичных ошибок для фрагмента
        handleFailure({}, {})
    }
}