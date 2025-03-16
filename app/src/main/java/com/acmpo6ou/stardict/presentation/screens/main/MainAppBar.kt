package com.acmpo6ou.stardict.presentation.screens.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.acmpo6ou.stardict.presentation.navigation.HomeMenu
import com.acmpo6ou.stardict.presentation.navigation.menus
import com.androidpoet.dropdown.Dropdown
import com.androidpoet.dropdown.dropDownMenu
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun MainAppBar(
    navController: NavController,
    tabs: Array<HomeTabsItems>,
    pagerState: PagerState
) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            HomeTabRow(tabs, pagerState)
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "",
                )
            }


            Dropdown(
                modifier = Modifier,
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
                menu = dropDownMenu<String> {
                    menus.forEach {
                        item(
                            id = it.title,
                            title = it.title
                        )
                    }
                },
                onItemSelected = {
                    showMenu = false
                    navController.navigate(route = HomeMenu.valueOf(it).route )
                }
            )
        },
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTabRow(
    tabs: Array<HomeTabsItems>,
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()
    TabRow(
        modifier = Modifier.clip(CircleShape),
        selectedTabIndex = pagerState.currentPage,//展示的页码，和Pager的保持一致
        divider = {},
        /* Disable the built-in divider */
//            edgePadding = Keyline1,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    .fillMaxSize()
                    .padding(1.dp)
                    .clip(CircleShape),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            )
        },

    ) {
        tabs.forEachIndexed { index, data ->

            val hasSelected = pagerState.currentPage == index

            LeadingIconTab(
                modifier = Modifier
                    .zIndex(2f),
                selected = hasSelected,
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurface,
                onClick = {
                    coroutineScope.launch {
                        pagerState.scrollToPage(index, 0f)//Tab被点击后让Pager中内容动画形式滑动到目标页
                    }
                },
                text = {
                    Text(text = data.title)
                },
                icon = { Icon(data.icon, contentDescription = data.title) },
                interactionSource = NoRippleInteractionSource()
            )
        }
    }
}

