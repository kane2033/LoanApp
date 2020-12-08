package com.focusstart.loanapp.core.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.focusstart.loanapp.R
import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.presentation.BaseViewModel

/**
* Базовый класс [Fragment],
 * имеющий общие для других фрагментов методы.
* */
abstract class BaseFragment: Fragment() {

    protected abstract val viewModel: BaseViewModel

    // Отображение Toast уведомления со строкой из ресурсов
    internal fun makeToast(@StringRes message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    // Отображение Toast уведомления с любой строкой
    internal fun makeToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Стандартный метод обработки ошибок, который хранит ошибки,
     * присующие каждому фрагменту, а также стандартное сообщение ошибки.
     * @param [handleFailure] - обработка дополнительных ошибок, присущих какому-то
     * конкретному фрагменту.
     * @param [handleRequestFailure] - обработка HTTP кодов ошибок в зависимости от кода.
     * */
    protected fun handleFailure(
        handleFailure: (failure: Failure) -> Unit,
        handleRequestFailure: (code: Int) -> Unit
    ) {
        viewModel.failure.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { failure ->
                handleFailure.invoke(failure)
                when (failure) {
                    is Failure.NetworkConnection -> makeToast(R.string.error_network_connection)
                    is Failure.RequestFailure -> handleRequestFailure.invoke(failure.code)
                    else -> makeToast(R.string.error_base)
                }
            }
        })
    }


    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}