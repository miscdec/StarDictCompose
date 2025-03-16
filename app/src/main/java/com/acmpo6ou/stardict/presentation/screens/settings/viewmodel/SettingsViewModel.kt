package com.acmpo6ou.stardict.presentation.screens.settings.viewmodel

import androidx.lifecycle.ViewModel
import com.acmpo6ou.stardict.datastore.setting.DarkMode
import com.acmpo6ou.stardict.datastore.setting.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,

    ) : ViewModel() {
    val userData get() = userDataRepository.userData


    init {
//        Timber.d("SettingsViewModel init")
    }

    fun setFontSize(value:Float) = userDataRepository.setFontSize(value)
    fun setDarkTheme(value: DarkMode) = userDataRepository.setDarkTheme(value)
    fun setThemeColor(value: Int) = userDataRepository.setThemeColor(value)
    fun setDictPath(value: List<String>) = userDataRepository.setDictPath(value)
    fun setEnableNavigationAnimation(value: Boolean) = userDataRepository.setEnableNavigationAnimation(value)
}