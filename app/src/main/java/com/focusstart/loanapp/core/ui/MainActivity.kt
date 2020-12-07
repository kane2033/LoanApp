package com.focusstart.loanapp.core.ui

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.focusstart.loanapp.R
import com.focusstart.loanapp.core.di.entrypoint.ConfigChangerEntryPoint
import com.focusstart.loanapp.core.di.entrypoint.LanguageRepEntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun attachBaseContext(newBase: Context) {
        // Смена языка в зависимости от локали, выбранной пользователем
        val languageRepository = EntryPointAccessors.fromApplication(newBase,
                LanguageRepEntryPoint::class.java).repository
        val languageCode = languageRepository.getAppLanguage()
        val configChanger = EntryPointAccessors.fromApplication(newBase,
                ConfigChangerEntryPoint::class.java).configChanger
        val localeUpdatedContext: ContextWrapper = configChanger.updateLocale(newBase, languageCode)
        super.attachBaseContext(localeUpdatedContext)
    }

}