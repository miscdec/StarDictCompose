package com.acmpo6ou.stardict.presentation.screens.translation

sealed class TranslationScreenState {
    object Translating : TranslationScreenState()
    data class Translated(val originalText: String, val translatedText: String) : TranslationScreenState()
    object DisconnectedNetwork : TranslationScreenState()
    object FailedTranslate : TranslationScreenState()
}