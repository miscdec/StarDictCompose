package com.acmpo6ou.stardict.utils

import android.graphics.Typeface
import java.io.File

object FontManager {
    // This function enumerates all fonts on Android system and returns the HashMap with the font
    // absolute file name as key, and the font literal name (embedded into the font) as value.
    fun enumerateFonts(): HashMap<String, String>? {
        val fontDirs = arrayOf("/system/fonts", "/system/font", "/data/fonts")
        val fonts = HashMap<String, String>()
        for (fontDir in fontDirs) {
            val dir = File(fontDir)
            if (!dir.exists()) continue
            val files = dir.listFiles() ?: continue
            for (file in files) {
                val fontname = Typeface.createFromFile(file).toString()
                fonts[file.absolutePath] = fontname
            }
        }
        return if (fonts.isEmpty()) null else fonts
    }
}
