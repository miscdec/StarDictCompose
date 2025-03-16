package com.acmpo6ou.stardict.datastore.setting

import com.acmpo6ou.stardict.datastore.setting.DarkMode
import com.acmpo6ou.stardict.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource,
    @ApplicationScope private val applicationScope: CoroutineScope
) {
    val userData get() = userPreferencesDataSource.userData

    private var _value = UserData.default()
    val value get() = _value

    init {
        userPreferencesDataSource.userData
            .distinctUntilChanged()
            .onEach {

                _value = it
            }.launchIn(applicationScope)
    }


    fun setDarkTheme(value: DarkMode) = applicationScope.launch {
        userPreferencesDataSource.setDarkTheme(value)
    }

    fun setFontSize(value: Float) = applicationScope.launch {
        userPreferencesDataSource.setFontSize(value)
    }

    fun setDictPath(value: List<String>) = applicationScope.launch {
        userPreferencesDataSource.setDictPath(value)
    }

//    fun setUsingDynamicColor(value: Boolean) = applicationScope.launch {
//        userPreferencesDataSource.setUsingDynamicColor(value)
//    }

    fun setThemeColor(value: Int) = applicationScope.launch {
        userPreferencesDataSource.setThemeColor(value)
    }

    fun setEnableNavigationAnimation(value: Boolean) = applicationScope.launch {
        userPreferencesDataSource.setEnableNavigationAnimation(value)
    }
}