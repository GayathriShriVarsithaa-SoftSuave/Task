package com.example.task_2

import android.content.Context

class SessionManager(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    fun login() {
        sharedPreferences.edit()
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .apply()
    }

    fun logout() {
        sharedPreferences.edit()
            .putBoolean(KEY_IS_LOGGED_IN, false)
            .apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }
}