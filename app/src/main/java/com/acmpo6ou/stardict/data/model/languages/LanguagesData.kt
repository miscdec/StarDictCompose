package com.acmpo6ou.stardict.data.model.languages

import kotlinx.serialization.Serializable

@Serializable
data class LanguagesData(
    val languages: List<Language>
)