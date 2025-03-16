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

package com.acmpo6ou.stardict.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.acmpo6ou.stardict.presentation.navigation.Destination
import com.acmpo6ou.stardict.presentation.navigation.MyAppNavHost
import com.acmpo6ou.stardict.presentation.screens.main.MainScreen
import com.acmpo6ou.stardict.presentation.screens.main.MainViewModel
import com.acmpo6ou.stardict.presentation.base.BaseActivity
import com.acmpo6ou.stardict.presentation.ui.theme.StarDictTheme
import com.acmpo6ou.stardict.utils.app.utils.MediaStoreUtils
import com.acmpo6ou.stardict.utils.app.utils.NotificationUtils
import com.acmpo6ou.stardict.utils.app.utils.OsUtils
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        var isReady by mutableStateOf(false)
        splashScreen.setKeepOnScreenCondition { !isReady }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                userDataRepository.userData
                    .distinctUntilChanged()
                    .collect {
//                        if (it.isSetup) {
//                            setSetup()
//                        } else {
//                            setMain()
//                        }

                        isReady = true
                    }
            }
        }

        setActivityContent {
            PermissionsState()
            val navController = rememberNavController()
            val text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)
            } else {
                null
            }
            MyAppNavHost(
                navController = navController,
                shortcutText = text,
                startDestination = if (!text.isNullOrEmpty()) Destination.ShortcutRouteScreen else Destination.MainScreen,
                enableAnimation = it.enableNavigationAnimation
            )
        }


    }

//    private fun setSetup() = setActivityContent {
//        PermissionsState()
//        SetupScreen(setMode = userDataRepository::setWorkingMode)
//    }
//
//    private fun setMain() = setActivityContent {
//        PermissionsState()
//        MainScreen(it)
//    }

    @Composable
    private fun  PermissionsState() {
        if (OsUtils.atLeastT) {
            NotificationUtils.PermissionState()
        }

        MediaStoreUtils.PermissionState()
    }
}




@Composable
@Preview
fun MainScreenPreview() {
    val model = MainViewModel()
    model.completions.value = listOf("apple", "apple pie", "apple jack")
    StarDictTheme(themeColor = 1) {
        MainScreen(
            navController = rememberNavController(),
            viewModel = MainViewModel()
        )
    }
}

