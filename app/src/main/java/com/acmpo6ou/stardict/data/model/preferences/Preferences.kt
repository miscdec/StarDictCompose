package com.acmpo6ou.stardict.data.model.preferences

import com.acmpo6ou.stardict.data.model.languages.Language

data class Preferences(
    val sourceLanguage: Language,
    val targetLanguage: Language
)