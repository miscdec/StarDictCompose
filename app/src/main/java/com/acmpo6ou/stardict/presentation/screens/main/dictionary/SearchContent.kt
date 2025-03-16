package com.acmpo6ou.stardict.presentation.screens.main.dictionary

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.navOptions
import org.yage.dict.star.WordParams
import com.acmpo6ou.stardict.presentation.navigation.Destination
import com.acmpo6ou.stardict.presentation.navigation.navigate
import com.acmpo6ou.stardict.presentation.screens.main.MainViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

@Composable
fun SearchContent(
    viewModel: MainViewModel,
    navController: NavController,
    contentPadding: Dp
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val searchText: String by viewModel.searchText.observeAsState("")
        Banner(display = searchText.isEmpty(), contentPadding = contentPadding)
        Spacer(Modifier.height(32.dp))
        val scope = rememberCoroutineScope()
        var currentJob by remember { mutableStateOf<Job?>(null) }
        val searchDelayTime = 300L
        SearchBox(
            modifier = Modifier
                .zIndex(2f)
                .padding(horizontal = contentPadding)
                .fillMaxWidth(),
            value = searchText,
            onValueChange = {
                viewModel.searchText.value = it
                currentJob?.cancel()
                currentJob = scope.async {
                    delay(searchDelayTime)
                    viewModel.search(it)
                }
            },
            onClearText = {
                viewModel.searchText.value = ""
                viewModel.completions.value = listOf()
            },
            onSearchClick = {
                if (viewModel.completions.value!!.isNotEmpty()) {
                    navController.navigate(
                        route = Destination.WordScreen,
                        args = bundleOf(
                            "pArg" to WordParams(
                                word = searchText,
                                transcription = viewModel.getTranscription(searchText),
                                articles = viewModel.getArticles(searchText),
                            )
                        ),
                        navOptions = navOptions {
                            launchSingleTop = true
                        }

                    )

                }
            },
            isSearching = false
        )
        val completions: List<String> by viewModel.completions.observeAsState(listOf())
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(completions.size) { i ->
                val word = completions[i]
                Row(
                    modifier = Modifier
                        .clickable {
                            Log.d(
                                "article",
                                viewModel
                                    .getArticles(word = word)
                                    .toString()
                            )
                            navController.navigate(
                                route = Destination.WordScreen,
                                args = bundleOf(
                                    "pArg" to WordParams(
                                        word = word,
                                        transcription = viewModel.getTranscription(word),
                                        articles = viewModel.getArticles(word),
                                    )
                                ),
                                navOptions = navOptions {
                                    launchSingleTop = true
                                }
                            )
//                                hideKeyboard()
                        }
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(word, fontSize = 26.sp)
                }
            }
        }

    }

}

@Composable
private fun Banner(display: Boolean, contentPadding: Dp) {
    AnimatedVisibility(
        display,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = contentPadding)
            ) {
                Title()
                Spacer(modifier = Modifier.height(16.dp))
                CollinsDivider()
            }
        }
    }
}

@Composable
private fun Title(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "Star Dictionary",
        fontSize = 60.sp,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
        lineHeight = 68.sp,
        softWrap = true
    )
}

@Composable
private fun CollinsDivider() {
    HorizontalDivider(
        Modifier.width(88.dp),
        thickness = 4.dp,
        color = MaterialTheme.colorScheme.primary
    )
}