package com.sphinx.minesweeper

import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES

fun Context.isDarkTheme(): Boolean {
    return resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
}

class SettingPreference(private val context:Context) {

    private val preference = context.getSharedPreferences("settings",Context.MODE_PRIVATE)


    private val editor by lazy { preference.edit() }




    var isDark:Boolean
        get() = preference.getBoolean("is_dark",context.isDarkTheme())
        set(value){
            editor.putBoolean("is_dark",value).commit()
        }





}