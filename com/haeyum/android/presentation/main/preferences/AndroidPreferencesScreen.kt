package com.haeyum.android.presentation.main.preferences

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.haeyum.common.presentation.component.Header
import com.haeyum.common.presentation.preferences.PreferencesScreen
import com.haeyum.common.presentation.preferences.PreferencesViewModel
import com.haeyum.common.presentation.theme.White

@Composable
fun AndroidPreferencesScreen() {
    val viewModel: PreferencesViewModel = hiltViewModel()
    val context = LocalContext.current

    var visibleSelect by remember { mutableStateOf(false) }

    PreferencesScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White),
        header = {
            Header(title = "Preferences")
        },
        supportedLanguages = viewModel.supportedLanguages.collectAsState().value,
        selectedSourceLanguage = viewModel.selectedSourceLanguage.collectAsState().value?.name ?: "-",
        selectedTargetLanguage = viewModel.selectedTargetLanguage.collectAsState().value?.name ?: "-",
        onSelectedSourceLanguage = viewModel::setSelectedSourceLanguage,
        onSelectedTargetLanguage = viewModel::setSelectedTargetLanguage,
        onClickClearData = viewModel::clearData,
        onClickContact = {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("mailto:vnycall74@naver.com")))
        },
        onNotifyVisibleSelect = {

        }
    )

    DisposableEffect(Unit) {
        onDispose {
            viewModel.onDestroy()
        }
    }

    BackHandler(enabled = visibleSelect) {
        /* no-op */
    }
}