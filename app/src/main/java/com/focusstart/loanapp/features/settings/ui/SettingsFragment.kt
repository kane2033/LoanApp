package com.focusstart.loanapp.features.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.focusstart.loanapp.R
import com.focusstart.loanapp.core.ui.BaseFragment
import com.focusstart.loanapp.features.settings.presentation.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*

@AndroidEntryPoint
class SettingsFragment : BaseFragment() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val languages = resources.getStringArray(R.array.languages_keys)

        changeLanguageButton.setOnClickListener {
            viewModel.setLanguage(requireContext(), languages[languageSpinner.selectedItemPosition])
            requireActivity().recreate()
        }

        logoutButton.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_settingsFragment_to_authGraph)
        }
    }
}