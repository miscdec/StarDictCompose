package com.acmpo6ou.stardict.presentation.screens.translation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.util.network.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.SocketException

class TranslationViewModel(

) : ViewModel() {
    private val _screenState = MutableStateFlow<TranslationScreenState>(TranslationScreenState.Translating)
    val screenState = _screenState.asStateFlow()

    private val originalText = MutableStateFlow("")
    private val translatedText = originalText
        .filter { it.isNotEmpty() }
        .map {

        }
        .flowOn(Dispatchers.IO)
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = null)


    fun toggleSave() {
        viewModelScope.launch {
            translatedText.first()?.let {

            }
        }
    }

    fun requestTranslation(toString: String) {

    }
}