package com.haeyum.android.presentation.translation

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import com.haeyum.android.presentation.main.MainActivity

class TranslationActivity : ComponentActivity() {

    private val viewModel by viewModels<TranslationViewModel>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setBackgroundDrawable(ColorDrawable(0))
        window.statusBarColor = 0x00000000
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