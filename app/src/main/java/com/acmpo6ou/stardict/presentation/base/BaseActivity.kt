package com.acmpo6ou.stardict.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acmpo6ou.stardict.datastore.setting.UserData
import com.acmpo6ou.stardict.datastore.setting.UserDataRepository
import com.acmpo6ou.stardict.datastore.setting.isDarkMode
import com.acmpo6ou.stardict.presentation.screens.main.ProvideLocalScreenSize
import com.acmpo6ou.stardict.presentation.ui.theme.StarDictTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : ComponentActivity() {

    @Inject
    lateinit var userDataRepository: UserDataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    fun setActivityContent(
        content: @Composable (UserData) -> Unit
    ) = setContent {
        val userData by userDataRepository.userData.collectAsStateWithLifecycle(initialValue = UserData.default())

        ProvideLocalScreenSize{
            Surface {
                StarDictTheme(
                    isDarkTheme = userData.isDarkMode(),
                    themeColor = userData.themeColor
                ) {
                    content(userData)
                }
            }
        }
    }
}