package com.acmpo6ou.stardict.data.model.detect

import kotlinx.serialization.Serializable

@Serializable
data class DetectionsData(
    val detections: List<List<Detection>>
)