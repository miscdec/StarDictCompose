package com.acmpo6ou.stardict.presentation.screens.settings.app

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Animation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.acmpo6ou.stardict.R
import com.acmpo6ou.stardict.datastore.setting.UserData
import com.acmpo6ou.stardict.datastore.setting.isDarkMode
import com.acmpo6ou.stardict.presentation.screens.preference.app.items.AppThemeItem
import com.acmpo6ou.stardict.presentation.screens.preference.component.NavigateUpTopBar
import com.acmpo6ou.stardict.presentation.screens.preference.component.SettingSwitchItem
import com.acmpo6ou.stardict.presentation.ui.utils.navigateBack
import com.acmpo6ou.stardict.presentation.ui.utils.none
import com.acmpo6ou.stardict.presentation.screens.settings.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val userData by viewModel.userData.collectAsStateWithLifecycle(UserData.default())

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    BackHandler { navController.navigateBack() }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                navController = navController
            )
        },
        contentWindowInsets = WindowInsets.none
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {
            AppThemeItem(
//                usingDynamicColor = userData.usingDynamicColor,
                themeColor = userData.themeColor,
                darkMode = userData.darkMode,
                isDarkMode = userData.isDarkMode(),
                onThemeColorChange = viewModel::setThemeColor,
                onDarkModeChange = viewModel::setDarkTheme
            )


//            DownloadPathItem(
//                downloadPath = userData.downloadPath,
//                onChange = viewModel::setDownloadPath
//            )
//
//            SettingSwitchItem(
//                iconRes = Icons.Outlined.Remove,
//                text = stringResource(id = R.string.settings_delete_zip),
//                subText = stringResource(id = R.string.settings_delete_zip_desc),
//                checked = userData.deleteZipFile,
//                onChange = viewModel::setDeleteZipFile
//            )


            SettingSwitchItem(
                imageVector = Icons.Outlined.Animation,
                text = stringResource(id = R.string.settings_navigation_animation),
                subText = stringResource(id = R.string.settings_navigation_animation_desc),
                checked = userData.enableNavigationAnimation,
                onChange = viewModel::setEnableNavigationAnimation
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController
) = NavigateUpTopBar(
    title = R.string.settings_app,
    scrollBehavior = scrollBehavior,
    navController = navController
)