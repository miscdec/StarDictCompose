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

package com.acmpo6ou.stardict.presentation.screens.dicts

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.acmpo6ou.stardict.BuildConfig
import com.acmpo6ou.stardict.R
import com.acmpo6ou.stardict.presentation.ui.component.common.AppBar
import com.acmpo6ou.stardict.presentation.ui.theme.StarDictTheme
import kotlinx.coroutines.launch

@Composable
fun DictsScreen(
    viewModel: DictViewModel = hiltViewModel(),
    navController: NavController
) {
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    val viewState by viewModel.searchUiState.collectAsStateWithLifecycle()
    val mDictUIIntent = viewModel.mIntent
    LaunchedEffect(key1 = Unit, block = {
        lifecycleScope.launch {
            mDictUIIntent.send(DictUIIntent.GetLoadedDictionaries)
        }
    })
    var debuggingDialogVisibility by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { AppBar(
            title = stringResource(id = R.string.screen_dict_title),
            navController = navController,
            actions = {
                if (BuildConfig.DEBUG) {
                    IconButton(onClick = {
                        debuggingDialogVisibility = true
                    }) {
                        Icon(imageVector = Icons.Default.DeveloperMode, contentDescription = "develop info")
                    }

                }
            })
                 },
        floatingActionButton = {
            val importLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.OpenDocument(),
                onResult = { uri: Uri? ->
                    if (uri != null) {
                        lifecycleScope.launch {
                            mDictUIIntent.send(DictUIIntent.LoadDictionariesFromUris(uri))
                        }
                    }
                }
            )
            FloatingActionButton(
                onClick = {
                    importLauncher.launch(arrayOf("*/*"))//启动
                },
//                containerColor = MaterialTheme.colorScheme.primary,
//                contentColor = Color.White,
            ) {
                Icon(Icons.Default.Add, "Import dict")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->

        DictContent(
            modifier = Modifier.padding(paddingValues = paddingValues),
            viewState = viewState,
            onRemoveDict = {
                lifecycleScope.launch {
                    mDictUIIntent.send(DictUIIntent.RemoveLoadedDictionaries(it))
                }
            }
        )
        if (debuggingDialogVisibility) {
            AlertDialog(
                modifier = Modifier,
                onDismissRequest = { debuggingDialogVisibility = false },
                confirmButton = {
                    Button(onClick = {
                        debuggingDialogVisibility = false
                    }) {
                        Text(text = stringResource(id = android.R.string.ok))
                    }
                },
                text = {
                    Column {
                        Text(
                            text = "Version: ${BuildConfig.VERSION_NAME}\n" +
                                    "Build: ${BuildConfig.VERSION_CODE}\n" +
                                    "Debug: ${BuildConfig.DEBUG}\n" +
                                    "App: ${BuildConfig.APPLICATION_ID}\n" +
                                    "Package: ${BuildConfig.APPLICATION_ID}\n")
                        Text(text = viewModel.getDictPath().joinToString("\n"))
                    }

                })
        }

    }
}

@Composable
fun DictContent(
    modifier: Modifier,
    viewState: DictStatusUiState,
    onRemoveDict: (StarDictInfo) -> Unit = { }
) {

    when (viewState) {
        is DictStatusUiState.Error -> {
            viewState.exception.localizedMessage?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(horizontal = 60.dp)
                        .fillMaxHeight()
                        .wrapContentHeight()
                )
            }
        }

        is DictStatusUiState.Loading -> {
            Text(
                text = "Loading: ${viewState.process}",
                modifier = Modifier
                    .padding(horizontal = 60.dp)
                    .fillMaxHeight()
                    .wrapContentHeight()
            )
        }

        is DictStatusUiState.Success -> {
            if (viewState.result.isNotEmpty()) {
                LazyColumn(
                    modifier,
                    content = {
                        items(viewState.result) { item ->
                            DictItem(
                                dict = item,
                                onRemoveDict = { onRemoveDict(item) })
                        }
                    })
            } else Text(
                stringResource(R.string.no_dicts),
                modifier = Modifier
                    .padding(horizontal = 60.dp)
                    .fillMaxHeight()
                    .wrapContentHeight()
            )
        }

        DictStatusUiState.Idle -> {
            Text(
                stringResource(R.string.no_dicts),
                modifier = Modifier
                    .padding(horizontal = 60.dp)
                    .fillMaxHeight()
                    .wrapContentHeight()
            )
        }
    }
}

@Composable
@Preview
fun DictScreenEmptyPreview() {
    StarDictTheme {
        DictsScreen(hiltViewModel(), navController = rememberNavController())
    }
}


data class StarDictInfo(
    val name: String,
    val filePath: String,
)

@Composable
fun DictItem(dict: StarDictInfo, onRemoveDict: (StarDictInfo) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
    ) {
        val (icon, dictName, deleteIcon) = createRefs()

        Icon(
            painter = painterResource(R.drawable.ic_book),
            tint = Color.White,
            contentDescription = "",
            modifier = Modifier
                .constrainAs(icon) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                }
                .padding(8.dp)
                .size(50.dp)
        )

        Text(
            dict.name, overflow = TextOverflow.Ellipsis,
            softWrap = false,
            modifier = Modifier.constrainAs(dictName) {
                start.linkTo(icon.end)
                end.linkTo(deleteIcon.start)
                centerVerticallyTo(parent)
                width = Dimension.fillToConstraints
            }
        )

        IconButton(
            onClick = { onRemoveDict(dict) },
            modifier = Modifier.constrainAs(deleteIcon) {
                end.linkTo(parent.end)
                centerVerticallyTo(parent)
            }
        ) {
            Icon(
                Icons.Default.Delete,
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = "remove dict",
            )
        }
    }
}
