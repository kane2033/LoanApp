package com.focusstart.loanapp.features.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.focusstart.loanapp.R
import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.ui.BaseFragment
import com.focusstart.loanapp.features.auth.presentation.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment: BaseFragment() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton.setOnClickListener {
            viewModel.login(
                    nameInputView.editText?.text.toString(),
                    nameInputView.editText?.text.toString()
            )
        }

        registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        viewModel.token.observe(viewLifecycleOwner, {
            tokenView.text = it
            // *navigate to LoanActivity*
        })

        viewModel.failure.observe(viewLifecycleOwner, { failure ->
            failure.getContentIfNotHandled()?.let { // Создаем уведомление, только если его не было
                when (it) {
                    is Failure.RequestFailure -> handleRequestFailure(it.code)
                    is Failure.NetworkConnection -> makeToast(R.string.error_network_connection)
                    else -> {}
                }
            }

        })
    }

    private fun handleRequestFailure(code: Int) {
        when (code) {
            404 -> makeToast(R.string.error_user_not_found)
            403 -> makeToast("403 Forbidden")
        }
    }
}