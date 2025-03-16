

package com.acmpo6ou.stardict.presentation.screens.translation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acmpo6ou.stardict.presentation.screens.translation.section.TranslateFailureSection
import com.acmpo6ou.stardict.presentation.screens.translation.section.TranslateSuccessSection
import com.acmpo6ou.stardict.presentation.ui.component.common.RainbowCircularProgressIndicator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslationScreen(
    onRequestOpen: () -> Unit,
    onRequestFinish: () -> Unit,
    viewModel: TranslationViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onRequestFinish
            ),
        contentAlignment = Alignment.Center
    ) {
        val screenState by viewModel.screenState.collectAsState()
//        val isExistsSavedTranslate by viewModel.isExistsSavedTranslate.collectAsState()
        val isExistsSavedTranslate  = false

        val bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded,
            skipHiddenState = false
        )

        val clipboardManager = LocalClipboardManager.current

        ModalBottomSheet(
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 14.dp, bottom = 24.dp)
                ) {
//                    Divider(
//                        modifier = Modifier
//                            .fillMaxWidth(.16f)
//                            .clip(CircleShape)
//                            .align(Alignment.CenterHorizontally),
//                        thickness = 4.dp
//                    )


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 18.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        when (screenState) {
                            TranslationScreenState.Translating -> {
                                AnimatedVisibility(screenState == TranslationScreenState.Translating) {
                                    TranslatingProgressIndicator()
                                }
                            }

                            is TranslationScreenState.Translated -> {
                                val screenState = (screenState as TranslationScreenState.Translated)

                                TranslateSuccessSection(
                                    screenState.originalText,
                                    screenState.translatedText,
                                    isExistsSavedTranslate = isExistsSavedTranslate,
                                    onRequestOpen = onRequestOpen,
                                    onRequestCopy = {
                                        clipboardManager.setText(it)
                                    },
                                    onRequestToggleSave = viewModel::toggleSave
                                )
                            }

                            TranslationScreenState.DisconnectedNetwork -> {
                                TranslateFailureSection(
                                    title = "Disconnected Network",
                                    description = "Please check your network connection.\n"
                                )
                            }

                            TranslationScreenState.FailedTranslate -> {
                                TranslateFailureSection(
                                    title = "Failed Translate",
                                    description = "Please change the text or try again later."
                                )
                            }
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxSize(),
            sheetState = bottomSheetState,
            onDismissRequest = {

            }
        )

        LaunchedEffect(Unit) {
            snapshotFlow { bottomSheetState.currentValue }
                .filter { it == SheetValue.Hidden }
                .collectLatest {
                    onRequestFinish()
                }
        }
    }

}

@Composable
private fun TranslatingProgressIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.46f),
        contentAlignment = Alignment.Center
    ) {
        RainbowCircularProgressIndicator(
            modifier = Modifier.size(96.dp),
            strokeWidth = 6.dp
        )
    }
}