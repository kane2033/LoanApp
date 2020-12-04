package com.focusstart.loanapp.features.loan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.focusstart.loanapp.R
import com.focusstart.loanapp.core.ui.BaseFragment
import com.focusstart.loanapp.features.loan.presentation.LoansDataAdapter
import com.focusstart.loanapp.features.loan.presentation.LoansViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_loans_list.*

@AndroidEntryPoint
class LoansListFragment : BaseFragment() {

    private val viewModel: LoansViewModel by navGraphViewModels(R.id.loanMasterDetailGraph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_loans_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация RecyclerView с адаптером
        val loansAdapter = LoansDataAdapter(clickListener = { position ->
            // Выбранный заём сохраняем в общей viewmodel для master-detail
            viewModel.setSelectedLoan(position)
            findNavController().navigate(R.id.action_loansListFragment_to_loanDetailsFragment)
        })
        loansRecyclerView.adapter = loansAdapter

        listRefreshLayout.setOnRefreshListener {
            viewModel.getLoansList()
            listRefreshLayout.isRefreshing = false
        }

        viewModel.loans.observe(viewLifecycleOwner, {
            loansAdapter.updateList(it)
        })

        createLoanButton.setOnClickListener {
            findNavController().navigate(R.id.action_loansListFragment_to_loanCreationGraph)
        }

        viewModel.failure.observe(viewLifecycleOwner, {
            makeToast(R.string.error_base)
        })
    }

}