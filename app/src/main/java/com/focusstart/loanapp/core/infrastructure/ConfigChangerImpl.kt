package com.focusstart.loanapp.core.infrastructure

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.LocaleList
import com.focusstart.loanapp.core.domain.infrastructure.ConfigChanger
import java.util.*
import javax.inject.Inject

class ConfigChangerImpl
@Inject constructor() : ConfigChanger {

    override fun updateLocale(context: Context, languageCode: String): ContextWrapper {
        val configuration: Configuration = context.resources.configuration
        val localeList = LocaleList(Locale(languageCode))
        LocaleList.setDefault(localeList)
        configuration.setLocales(localeList)
        return ContextWrapper(context.createConfigurationContext(configuration))
    }
}