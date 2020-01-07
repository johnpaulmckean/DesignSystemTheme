package com.jmckean.designsystem.theme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val themePreferences = ThemePreferences(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(getActualTheme())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        themeRadioBigGreen.isChecked = themePreferences.currentTheme == ThemesEnum.BIG_GREEN
        themeRadioZenith.isChecked = themePreferences.currentTheme == ThemesEnum.ZENITH
        themeRadioZenithDark.isChecked = themePreferences.currentTheme == ThemesEnum.ZENITH_DARK

        themeRadioGroup.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.themeRadioBigGreen -> changeTheme(ThemesEnum.BIG_GREEN)
                R.id.themeRadioZenith -> changeTheme(ThemesEnum.ZENITH)
                R.id.themeRadioZenithDark -> changeTheme(ThemesEnum.ZENITH_DARK)
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
    }

    private fun changeTheme(theme: ThemesEnum) {
        themePreferences.currentTheme = theme

        overridePendingTransition(0, 0)

        TaskStackBuilder.create(this)
            .addNextIntent(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            .startActivities()

        finish()
    }

    private fun getActualTheme(): Int {
        return when(themePreferences.currentTheme) {
            ThemesEnum.BIG_GREEN -> R.style.Component_Theme_LightDark_BigGreen
            ThemesEnum.ZENITH -> R.style.Component_Theme_Light_Zenith
            ThemesEnum.ZENITH_DARK -> R.style.Component_Theme_Dark_Zenith
        }
    }
}
