package com.focusstart.loanapp.features.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.focusstart.loanapp.R
import com.focusstart.loanapp.core.ui.BaseFragment
import com.focusstart.loanapp.features.auth.presentation.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    override val viewModel: AuthViewModel by navGraphViewModels(R.id.authGraph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton.setOnClickListener {
            viewModel.login(
                    nameInputView.editText?.text.toString(),
                    passwordInputView.editText?.text.toString()
            )
        }

        viewModel.isLoggedIn.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { loggedIn ->
                if (loggedIn) {
                    findNavController().navigate(R.id.action_loginFragment_to_loanMasterDetailGraph)
                }
            }
        })

        registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        // Feature ошибок нет, лямбда пустая;
        // Обрабатываем только http ошибки
        handleFailure({}, { code ->
            when (code) {
                404 -> makeToast(R.string.error_user_not_found)
                403 -> makeToast("403 Forbidden")
            }
        })
    }
}