package ru.learning.rpgcompanionapp.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import ru.learning.rpgcompanionapp.R
import androidx.core.os.LocaleListCompat

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireContext().getSharedPreferences("settings", 0)

        val backButton = view.findViewById<Button>(R.id.backButton)
        val themeRadioGroup = view.findViewById<RadioGroup>(R.id.themeRadioGroup)
        val languageRadioGroup = view.findViewById<RadioGroup>(R.id.languageRadioGroup)

        backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        when (prefs.getString("theme", "system")) {
            "light" -> themeRadioGroup.check(R.id.themeLight)
            "dark" -> themeRadioGroup.check(R.id.themeDark)
            else -> themeRadioGroup.check(R.id.themeSystem)
        }

        when (prefs.getString("language", "ru")) {
            "en" -> languageRadioGroup.check(R.id.languageEn)
            else -> languageRadioGroup.check(R.id.languageRu)
        }

        languageRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.languageRu -> {
                    prefs.edit().putString("language", "ru").apply()
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags("ru")
                    )
                }

                R.id.languageEn -> {
                    prefs.edit().putString("language", "en").apply()
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags("en")
                    )
                }
            }
        }

        themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.themeLight -> {
                    prefs.edit().putString("theme", "light").apply()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }

                R.id.themeDark -> {
                    prefs.edit().putString("theme", "dark").apply()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }

                R.id.themeSystem -> {
                    prefs.edit().putString("theme", "system").apply()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
        }
    }
}