package com.acmpo6ou.stardict.presentation.screens.main

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.acmpo6ou.stardict.presentation.screens.main.dictionary.SearchContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val tabs: Array<HomeTabsItems> = HomeTabsItems.values()
    val selectedIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(
        initialPage = selectedIndex,//初始页码
        initialPageOffsetFraction = 0f,
        pageCount = { tabs.size }
    )

    Scaffold(
        topBar = {
            MainAppBar(
                navController = navController,
                tabs = tabs,
                pagerState = pagerState
            )
        }
    ) { paddingValues ->

        val padding by animateDpAsState(
            when (LocalScreenSize.current) {
                ScreenSize.PHONE -> 16.dp
                ScreenSize.TABLET -> 24.dp
                ScreenSize.LAPTOP -> 48.dp
                ScreenSize.DESKTOP -> 48.dp
            }, label = "searchPadding"
        )
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .safeContentPadding()
        ) {
            HomeTabs(
                viewModel = viewModel,
                navController = navController,
                pagerState = pagerState,
                contentPadding = padding
            )

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTabs(
    modifier: Modifier = Modifier,
    navController: NavController,
    pagerState: PagerState,
    viewModel: MainViewModel,
    contentPadding: Dp
) {
    Column(
        modifier = modifier
            .background(
                color =  MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f),
                shape = RoundedCornerShape(45.dp)
            )
    ) {
        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
            pageSpacing = 0.dp,
            userScrollEnabled = true,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),
            beyondBoundsPageCount = 0,
            pageSize = PageSize.Fill,
            flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
            key = null,
            pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                state = pagerState,
                orientation = Orientation.Horizontal
            ),
            pageContent =  {
                Box(
                    modifier = modifier.fillMaxWidth()
                ) {
                    HomeTabsContent(
                        index = pagerState.currentPage,
                        navController = navController,
                        viewModel = viewModel,
                        contentPadding = contentPadding
                    )
                }
            }
        )
    }
}

@Composable
fun HomeTabsContent(
    index: Int,
    contentPadding: Dp,
    viewModel: MainViewModel,
    navController: NavController
) {
    when (index) {
        HomeTabsItems.Dictionary.ordinal -> {
            SearchContent(
                viewModel = viewModel,
                navController = navController,
                contentPadding = contentPadding
            )
        }
        HomeTabsItems.Translation.ordinal -> {
//            TranslationContent(
////                viewModel = viewModel,
//                navController = navController,
//                contentPadding = contentPadding
//            )
        }
    }
}

class NoRippleInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}

val LocalScreenSize = compositionLocalOf { ScreenSize.PHONE }

enum class ScreenSize {
    PHONE, TABLET, LAPTOP, DESKTOP
}

@Composable
fun ProvideLocalScreenSize(content: @Composable () -> Unit) {
    var screenSize by remember { mutableStateOf(ScreenSize.PHONE) }
    BoxWithConstraints {
        val width = with(LocalDensity.current) { constraints.maxWidth.toDp() }
        LaunchedEffect(width) {
            screenSize = when {
                width < 600.dp -> ScreenSize.PHONE
                width >= 600.dp && width < 1240.dp -> ScreenSize.TABLET
                width >= 1240.dp && width < 1440.dp -> ScreenSize.LAPTOP
                width >= 1440.dp -> ScreenSize.DESKTOP
                else -> error("error")
            }
        }
        CompositionLocalProvider(LocalScreenSize provides screenSize, content = content)
    }
}

