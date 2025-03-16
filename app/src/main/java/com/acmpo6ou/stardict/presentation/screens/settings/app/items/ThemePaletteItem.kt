package com.acmpo6ou.stardict.presentation.screens.settings.app.items

import android.os.Build
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.acmpo6ou.stardict.presentation.ui.theme.Colors
import com.acmpo6ou.stardict.presentation.ui.theme.Dynamic
import com.acmpo6ou.stardict.presentation.ui.theme.getColor
import com.acmpo6ou.stardict.presentation.ui.theme.getColorIds
import com.acmpo6ou.stardict.presentation.ui.theme.mColors
import com.acmpo6ou.stardict.utils.app.utils.OsUtils

@Composable
fun ThemePaletteItem(
    themeColor: Int,
    isDarkMode: Boolean,
    onChange: (Int) -> Unit,
) = LazyRow(
    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 16.dp),
    horizontalArrangement = Arrangement.spacedBy(15.dp)
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        item(Dynamic.id) {
            ThemeColorItem(
                id = Dynamic.id,
                themeColor = themeColor,
                isDarkMode = isDarkMode
            ) {
                onChange(it)
            }
        }
        item {
            HorizontalDivider(modifier = Modifier.background(Color.DarkGray))
        }
    }



    items(
        items = getColorIds(),
        key = { it }
    ) {currentId ->
        Log.d("colorsItemAdd", "id:$currentId;themeColor:$themeColor")
        ThemeColorItem(
            id = currentId,
            themeColor = themeColor,
            isDarkMode = isDarkMode
        ) { value ->
            onChange(value)
        }
    }
}

@Composable
private fun ThemeColorItem(
    id: Int,
    themeColor: Int,
    isDarkMode: Boolean,
    onClick: (Int) -> Unit
) {
    val color = getColor(id)
    val colorScheme = if (isDarkMode) color.darkColorScheme else color.lightColorScheme
    val selected = id == themeColor

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .clickable(
                onClick = { onClick(id) },
            )
            .background(
                color = colorScheme.surfaceVariant.copy(0.45f)
            )
            .size(60.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize(0.75f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(color = colorScheme.primaryContainer)
                )

                Spacer(modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorScheme.tertiaryContainer)
                )
            }

            Spacer(modifier = Modifier
                .fillMaxSize(0.5f)
                .clip(CircleShape)
                .background(
                    color = if (selected) {
                        colorScheme.onPrimary
                    } else {
                        colorScheme.primary
                    }
                )
            )

            if (selected) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = Icons.Outlined.CheckCircleOutline,
                    contentDescription = null,
                    tint = colorScheme.primary
                )
            }
        }
    }
}