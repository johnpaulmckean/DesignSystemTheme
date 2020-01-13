package com.jmckean.designsystem.theme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val themePreferences = ThemePreferences(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(getActualTheme())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        themeRadioBigGreen.isChecked = themePreferences.currentTheme == ThemePreferences.Themes.BIG_GREEN
        themeRadioZenith.isChecked = themePreferences.currentTheme == ThemePreferences.Themes.ZENITH
        themeRadioZenithDark.isChecked = themePreferences.currentTheme == ThemePreferences.Themes.ZENITH_DARK
        themeRadioPrettyInPink.isChecked = themePreferences.currentTheme == ThemePreferences.Themes.PRETTY_IN_PINK

        themeRadioGroup.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.themeRadioBigGreen -> changeTheme(ThemePreferences.Themes.BIG_GREEN)
                R.id.themeRadioZenith -> changeTheme(ThemePreferences.Themes.ZENITH)
                R.id.themeRadioZenithDark -> changeTheme(ThemePreferences.Themes.ZENITH_DARK)
                R.id.themeRadioPrettyInPink -> changeTheme(ThemePreferences.Themes.PRETTY_IN_PINK)
            }
        }

        //Everything else
        showDialogButton.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Shown")
                .setMessage("This will inform you!")
                .setPositiveButton("Okay", null)
                .setNegativeButton("Nevermind", null)
                .show()
        }

        showSnackbarButton.setOnClickListener {
            Snackbar.make(
                it,
                "Read this message!",
                Snackbar.LENGTH_SHORT
            ).setAction("Read") { }
                .show()
        }
    }

    private fun changeTheme(theme: ThemePreferences.Themes) {
        themePreferences.currentTheme = theme

        overridePendingTransition(0, 0)

        TaskStackBuilder.create(this)
            .addNextIntent(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            .startActivities()

        finish()

        overridePendingTransition(0, 0)
    }

    private fun getActualTheme(): Int {
        return when (themePreferences.currentTheme) {
            ThemePreferences.Themes.BIG_GREEN -> R.style.Component_Theme_LightDark_BigGreen
            ThemePreferences.Themes.ZENITH -> R.style.Component_Theme_Light_Zenith
            ThemePreferences.Themes.ZENITH_DARK -> R.style.Component_Theme_Dark_Zenith
            ThemePreferences.Themes.PRETTY_IN_PINK -> R.style.Component_Theme_Light_PrettyInPink
        }
    }
}
