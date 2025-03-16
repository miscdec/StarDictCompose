package com.haeyum.android.presentation.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import com.haeyum.android.presentation.main.preferences.AndroidPreferencesScreen
import com.haeyum.android.presentation.main.recent.RecentTranslateScreen
import com.haeyum.android.presentation.main.saved.SavedScreen
import com.haeyum.common.presentation.theme.White


@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onRequestFinish: () -> Unit
) {
    val screenState by viewModel.screenState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        when (screenState) {
            MainScreenState.Recent -> RecentTranslateScreen(modifier = Modifier.weight(1f))
            MainScreenState.Saved -> SavedScreen(modifier = Modifier.weight(1f))
            MainScreenState.Preferences -> Box(modifier = Modifier.weight(1f)) {
                AndroidPreferencesScreen()
            }
        }


        NavigationBar {
            BottomNavigationItem(
                selected = screenState == MainScreenState.Recent,
                onClick = viewModel::navigateToRecent,
                imageVector = Icons.Outlined.History,
                text = "Recent"
            )
            BottomNavigationItem(
                selected = screenState == MainScreenState.Saved,
                onClick = viewModel::navigateToSaved,
                imageVector = Icons.Outlined.FavoriteBorder,
                text = "Saved"
            )
            BottomNavigationItem(
                selected = screenState == MainScreenState.Preferences,
                onClick = viewModel::navigateToPreferences,
                imageVector = Icons.Outlined.Settings,
                text = "Preferences"
            )
        }
    }

    BackHandler(true) {
        onRequestFinish()
    }
}


@Composable
private fun RowScope.BottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    imageVector: ImageVector,
    text: String
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = imageVector,
                contentDescription = null,

            )
        },
        label = {
            Text(text = text)
        }
    )
}