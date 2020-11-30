package com.focusstart.loanapp.core.ui

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
* Базовый класс [Fragment],
 * имеющий общие для других фрагментов методы.
* */
abstract class BaseFragment: Fragment() {

    // Отображение Toast уведомления со строкой из ресурсов
    internal fun makeToast(@StringRes message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    // Отображение Toast уведомления с любой строкой
    internal fun makeToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}