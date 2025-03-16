package com.acmpo6ou.stardict.utils

/**
 * Makes article more readable by adding new lines where appropriate.
 */
fun formatArticle(text: String): String {
    // remove word and transcription from article
    var output = text.replace(Regex("<k>(.*?)</k>"), "")
    output = output.replace(Regex("<tr>(.*?)</tr>"), "")

    val regex3 = """(\d\d?\))""".toRegex()
    output = output.replace(regex3, "<br><br>$1")

    val regex4 = """(<ex>)""".toRegex()
    output = output.replace(regex4, "<br><br><ex>")

    val regex5 = """(- <kref>)""".toRegex()
    output = output.replace(regex5, "<br>- <kref>")

    val regex6 = """(\d\d?\.)""".toRegex()
    output = output.replace(regex6, "<br><br>$1")
    return output
}