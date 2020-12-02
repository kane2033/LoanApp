package com.focusstart.loanapp.features.loan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.focusstart.loanapp.R
import com.focusstart.loanapp.core.ui.BaseFragment
import com.focusstart.loanapp.features.loan.presentation.LoansDataAdapter
import com.focusstart.loanapp.features.loan.presentation.LoansListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_loans_list.*

@AndroidEntryPoint
class LoansListFragment : BaseFragment() {

    private val viewModel: LoansListViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_loans_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loansAdapter = LoansDataAdapter()
        // Инициализация RecyclerView
        loansRecyclerView.apply {
            //layoutManager = LinearLayoutManager(requireActivity())
            adapter = loansAdapter
            //clicksListener = ...
        }

        listRefreshLayout.setOnRefreshListener {
            viewModel.getLoansList()
            listRefreshLayout.isRefreshing = false
        }

        viewModel.loans.observe(viewLifecycleOwner, {
            loansAdapter.updateList(it)
        })
    }

}