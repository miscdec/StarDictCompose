package com.acmpo6ou.stardict.data.model.languages

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val language: String,
    val name: String
)