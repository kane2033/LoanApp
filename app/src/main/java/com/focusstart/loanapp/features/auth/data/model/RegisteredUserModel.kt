package com.focusstart.loanapp.features.auth.data.model

import com.squareup.moshi.Json

/**
 * Модель, получаемая при успешной регистрации.
 * В текущей логике, не используется.
* */
data class RegisteredUserModel(
        val name: String,
        @Json(name = "role")
        val userRole: String
)