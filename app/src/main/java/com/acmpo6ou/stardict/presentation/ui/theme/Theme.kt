/*
 * StarDict implementation for Android.
 * Copyright (C) 2022  Bohdan Kolvakh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.acmpo6ou.stardict.presentation.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.acmpo6ou.stardict.utils.app.utils.OsUtils
import com.google.accompanist.systemuicontroller.rememberSystemUiController



@Composable
fun getColor(id: Int): Colors {
    return if (OsUtils.atLeastS && id == Dynamic.id) {
        Dynamic
    } else {
        mColors[id]
    }
}

@Composable
fun StarDictTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
//    usingDynamicColor: Boolean = true,
    themeColor: Int = 0,
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkTheme,
            isNavigationBarContrastEnforced = false
        )
    }


    val color = getColor(id = themeColor)
    val myColorScheme: ColorScheme = if (isDarkTheme) color.darkColorScheme else color.lightColorScheme

    val view = LocalView.current
    val window = (view.context as Activity).window
    if (!view.isInEditMode) {
        SideEffect {
            window.statusBarColor = myColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window,view).isAppearanceLightStatusBars = isDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = myColorScheme,
        typography = Typography,
        content = content
    )
}

