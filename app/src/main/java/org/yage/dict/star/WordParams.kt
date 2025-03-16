package org.yage.dict.star

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class WordParams(
    val word: String = "N/A",
    val transcription: String = "N/A",
    val articles: Map<String, String> = mapOf(Pair("N/A","N/A")),
) : Parcelable