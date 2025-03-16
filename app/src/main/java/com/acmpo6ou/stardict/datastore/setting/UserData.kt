package com.acmpo6ou.stardict.datastore.setting

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.acmpo6ou.stardict.SRC_DIR
import com.acmpo6ou.stardict.datastore.setting.DarkMode
import com.acmpo6ou.stardict.datastore.setting.UserPreferences
import com.acmpo6ou.stardict.datastore.setting.dictPath
import com.acmpo6ou.stardict.presentation.screens.dicts.DictPath
import com.acmpo6ou.stardict.presentation.ui.theme.Dynamic
import com.acmpo6ou.stardict.presentation.ui.theme.Sakura
import com.acmpo6ou.stardict.utils.app.utils.OsUtils

data class UserData(
    val darkMode: DarkMode,
//    val usingDynamicColor:Boolean,
    val themeColor: Int,
    val enableNavigationAnimation: Boolean,
    val fontSize: Float,
    val dictPath:List<String>
) {
    companion object {
        fun default() = UserData(
            darkMode = DarkMode.FOLLOW_SYSTEM,
//            usingDynamicColor = true,
            themeColor = if (OsUtils.atLeastS) Dynamic.id else Sakura.id,
            enableNavigationAnimation = false,
            fontSize = 30f,
            dictPath = listOf<String>()
        )
    }
}

@Composable
fun UserData.isDarkMode() = when (darkMode) {
    DarkMode.ALWAYS_OFF -> false
    DarkMode.ALWAYS_ON -> true
    else -> isSystemInDarkTheme()
}


fun UserData.toPreferences(): UserPreferences = UserPreferences.newBuilder()
    .setDarkMode(darkMode)
    .setThemeColor(themeColor)
//    .setUsingDynamicColor(usingDynamicColor)
    .setEnableNavigationAnimation(enableNavigationAnimation)
    .setFontSize(fontSize)
    .setDictPath(dictPath{path})
    .build()

fun UserPreferences.toUserData() = UserData(
    darkMode = darkMode,
//    usingDynamicColor = usingDynamicColor,
    themeColor = themeColor,
    enableNavigationAnimation = enableNavigationAnimation,
    fontSize = fontSize,
    dictPath = dictPath.pathList
)