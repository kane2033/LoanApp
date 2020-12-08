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
import kotlinx.android.synthetic.main.fragment_register.*

@AndroidEntryPoint
class RegisterFragment: BaseFragment() {

    override val viewModel: AuthViewModel by navGraphViewModels(R.id.authGraph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_register, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerButton.setOnClickListener {
            viewModel.register(
                    nameInputView.editText?.text.toString(),
                    passwordInputView.editText?.text.toString()
            )
        }

        viewModel.isRegistered.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { isRegistered ->
                if (isRegistered) {
                    makeToast(R.string.success_register)
                    findNavController().navigateUp()
                }
            }
        })

        // Пока что нет специфичных ошибок для фрагмента
        handleFailure({}, {})
    }
}