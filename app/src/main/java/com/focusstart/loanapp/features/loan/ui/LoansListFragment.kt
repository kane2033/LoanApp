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
import com.focusstart.loanapp.features.loan.presentation.LoansDataAdapter
import com.focusstart.loanapp.features.loan.presentation.LoansViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_loans_list.*


@AndroidEntryPoint
class LoansListFragment : BaseFragment() {

    override val viewModel: LoansViewModel by navGraphViewModels(R.id.loanMasterDetailGraph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_loans_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем список займов с сервера
        viewModel.getLoansList()

        // Настройка навигации тулбаром на основе nav_graph
        toolBar.setupWithNavController(
            findNavController(),
            AppBarConfiguration(setOf(R.id.loansListFragment))
        )
        toolBar.inflateMenu(R.menu.toolbar)
        toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_update -> {
                    listRefreshLayout.isRefreshing = true
                    viewModel.getLoansList()
                    true
                }

                R.id.action_settings -> {
                    findNavController().navigate(R.id.action_loansListFragment_to_settingsGraph)
                    true
                }

                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }

        // Инициализация RecyclerView с адаптером
        val loansAdapter = LoansDataAdapter(clickListener = { position ->
            // Выбранный заём сохраняем в общей viewmodel для master-detail
            viewModel.setSelectedLoan(position)
            findNavController().navigate(R.id.action_loansListFragment_to_loanDetailsFragment)
        })
        loansRecyclerView.adapter = loansAdapter

        listRefreshLayout.setOnRefreshListener {
            viewModel.getLoansList()
        }

        viewModel.loans.observe(viewLifecycleOwner, {
            if (it.isEmpty()) guideView.visibility = View.VISIBLE else View.GONE
            loansAdapter.updateList(it)
            listRefreshLayout.isRefreshing = false
        })

        createLoanButton.setOnClickListener {
            findNavController().navigate(R.id.action_loansListFragment_to_loanCreationGraph)
        }

        handleFailure({ listRefreshLayout.isRefreshing = false }, {})
    }
}