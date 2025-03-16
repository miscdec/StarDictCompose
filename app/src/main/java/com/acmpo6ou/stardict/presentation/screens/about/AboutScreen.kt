package com.acmpo6ou.stardict.presentation.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.acmpo6ou.stardict.BuildConfig
import com.acmpo6ou.stardict.R
import com.acmpo6ou.stardict.presentation.screens.preference.component.NavigateUpTopBar
import com.acmpo6ou.stardict.presentation.ui.component.common.AppBar
import com.acmpo6ou.stardict.presentation.ui.utils.none
import com.acmpo6ou.stardict.presentation.ui.utils.rememberDrawablePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { AppBar(title = stringResource(R.string.settings_about), navController = navController) },
        contentWindowInsets = WindowInsets.none
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            appIcon()
            Spacer(modifier = Modifier.height(20.dp))
            appName()
            appVersion()
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun appVersion() {
    Text(
        text = stringResource(
            id = R.string.about_app_version,
            BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE
        ),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(top = 5.dp)
    )
}

@Composable
private fun appName() {
    Text(
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun appIcon() {
    Image(
        painter = rememberDrawablePainter(id = R.mipmap.ic_launcher),
        contentDescription = null,
        modifier = Modifier.size(65.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController
) = NavigateUpTopBar(
    title = R.string.settings_about,
    scrollBehavior = scrollBehavior,
    navController = navController
)