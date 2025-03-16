package com.acmpo6ou.stardict.presentation.screens.translation

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import com.acmpo6ou.stardict.presentation.MainActivity


class TranslationActivity : ComponentActivity() {

    private val viewModel by viewModels<TranslationViewModel>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            statusBarColor = Color.TRANSPARENT
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)

        viewModel.requestTranslation(intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString())

        setContent {
            TranslationScreen(
                onRequestOpen = {
                    startActivity(
                        Intent(
                            this@TranslationActivity,
                            MainActivity::class.java
                        ).apply {
                            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        }
                    )
                },
                onRequestFinish = ::finish,
                viewModel = viewModel
            )
        }
    }

}