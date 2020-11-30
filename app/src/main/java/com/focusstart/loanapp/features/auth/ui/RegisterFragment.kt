package com.focusstart.loanapp.features.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.focusstart.loanapp.R
import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.ui.BaseFragment
import com.focusstart.loanapp.features.auth.presentation.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*

@AndroidEntryPoint
class RegisterFragment: BaseFragment() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_register, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButton.setOnClickListener {
            viewModel.register(
                    nameInputView.editText?.text.toString(),
                    nameInputView.editText?.text.toString()
            )
        }

        viewModel.failure.observe(viewLifecycleOwner, { failure ->
            when (failure) {
                is Failure.RequestFailure -> makeToast(R.string.error_request)
                is Failure.NetworkConnection -> makeToast(R.string.error_network_connection)
                else -> makeToast(R.string.success_login)
            }
        })
    }
}