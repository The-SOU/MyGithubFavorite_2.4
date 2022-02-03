package com.sou.mygithubfavorite.utillity

import android.content.Context
import android.preference.PreferenceManager
import androidx.preference.Preference

class AuthTokenProvider(private val context: Context) {

    companion object {
        private const val KEY_AUTH_TOKEN = "auth_token"
    }

    fun updateToken(token: String) {
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putString(KEY_AUTH_TOKEN, token)
            .apply()
    }

    val token: String?
        get() = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_AUTH_TOKEN, null)
}