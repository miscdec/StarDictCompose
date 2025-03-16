package com.acmpo6ou.stardict.presentation.ui.component.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorView(
    onClick: () -> Unit
) {
    Box(
        Modifier
            .size(50.dp)
            .clip(CircleShape)
            .border(1.dp, Color.White, CircleShape)
            .clickable(onClick = onClick),
    )
}