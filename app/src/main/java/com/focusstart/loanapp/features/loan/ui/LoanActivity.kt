package com.focusstart.loanapp.features.loan.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.focusstart.loanapp.R
import com.focusstart.loanapp.features.auth.ui.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoanActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, LoginFragment())
                    .commitNow()
        }
    }

}