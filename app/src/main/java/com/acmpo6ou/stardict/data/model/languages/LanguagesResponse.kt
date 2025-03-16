package com.acmpo6ou.stardict.data.model.languages

import kotlinx.serialization.Serializable

@Serializable
data class LanguagesResponse(
    val data: LanguagesData
)