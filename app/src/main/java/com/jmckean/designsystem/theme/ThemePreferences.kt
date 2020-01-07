package com.jmckean.designsystem.theme

import android.content.Context
import android.content.SharedPreferences

class ThemePreferences(private val context: Context) {

    private val sharedPreferences: SharedPreferences
        get() = context.getSharedPreferences(DEFAULT_PREFERENCES, Context.MODE_PRIVATE)

    var currentTheme: ThemesEnum = ThemesEnum.valueOf(PREFERENCE_SELECTED_THEME_DEF_VAL)
        set(theme) {
            sharedPreferences.edit().putString(PREFERENCE_SELECTED_THEME, theme.name).apply()
            field = theme
        }
        get() = ThemesEnum.valueOf(sharedPreferences.getString(PREFERENCE_SELECTED_THEME, PREFERENCE_SELECTED_THEME_DEF_VAL).toString())

    companion object {
        private const val DEFAULT_PREFERENCES = "default_preferences"
        private const val PREFERENCE_SELECTED_THEME = "PREFERENCE_SELECTED_THEME"
        private val PREFERENCE_SELECTED_THEME_DEF_VAL = ThemesEnum.BIG_GREEN.name
    }
}