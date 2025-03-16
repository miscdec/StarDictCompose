package com.acmpo6ou.stardict.presentation.ui.component.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
//import com.vanpra.composematerialdialogs.MaterialDialogScope

//@Composable
//fun MaterialDialogScope.iconTitle(
//    titleText: String,
//    icon: @Composable () -> Unit = {},
//) {
//    Row(
//        modifier = Modifier
//            .padding(start = 24.dp, end = 24.dp)
//            .height(64.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        icon()
//        Spacer(Modifier.width(14.dp))
//        Text(
//            text = titleText,
//            color = Color.White,
//            style = MaterialTheme.typography.headlineMedium,
//        )
//    }
//}
//
//@Composable
//fun MaterialDialogScope.message(text: String? = null, @StringRes res: Int? = null) {
//    val messageText = text ?: stringResource(res!!)
//
//    Text(
//        text = messageText,
//        color = Color.White,
//        style = MaterialTheme.typography.bodyLarge,
//        modifier = Modifier
//            .padding(bottom = 28.dp, start = 24.dp, end = 24.dp)
//    )
//}