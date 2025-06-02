package com.o9tech.prankcall.utils

import android.content.Context
import com.o9tech.prankcall.DataModel.Languagesis

class PreferenceHelper(context: Context) {

    private val prefs = context.getSharedPreferences("language_prefs", Context.MODE_PRIVATE)

    fun saveLanguage(language: Languagesis) {
        prefs.edit()
            .putString("name", language.name)
            .putString("code", language.localeCode)
            .putInt("flag", language.flag)
            .apply()
    }

    fun getLanguage(): Languagesis? {
        val name = prefs.getString("name", null)
        val code = prefs.getString("code", null)
        val flag = prefs.getInt("flag", -1)

        return if (name != null && code != null && flag != -1) {
            Languagesis(name,  flag, code)
        } else {
            null
        }
    }
}
