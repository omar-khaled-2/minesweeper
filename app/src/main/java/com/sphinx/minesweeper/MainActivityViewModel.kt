package com.sphinx.minesweeper

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class MainActivityViewModel(application: Application):AndroidViewModel(application) {
    private val settingPreference = SettingPreference(getApplication())

    var isDark by mutableStateOf(settingPreference.isDark)
        private set

    fun onIsDarkChange(isDark:Boolean){
        settingPreference.isDark = isDark
        this.isDark = isDark
    }
}