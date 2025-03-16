/*
 * StarDict implementation for Android.
 * Copyright (C) 2022  Bohdan Kolvakh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.acmpo6ou.stardict

import android.app.Application
import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.acmpo6ou.stardict.datastore.setting.userPreferences
import com.acmpo6ou.stardict.utils.CopyAssetsUtil
import com.acmpo6ou.stardict.utils.expansion.toFile
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.suspendCancellableCoroutine
import org.yage.dict.star.StarDictEngine
import splitties.init.appCtx
import splitties.init.injectAsAppCtx
import java.util.Locale
import kotlin.coroutines.resume

@HiltAndroidApp
open class DictApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        injectAsAppCtx()
        if(filesDir.list()?.contains(".DICT_PLACEHOLDER") == true){
            starDict.loadDictionaries(SRC_DIR)
        }else{
            //将assets里的dicts复制到files目录
            CopyAssetsUtil.copyAssets(this,"dict" ,SRC_DIR)
                "${appCtx.filesDir.path}/.DICT_PLACEHOLDER".toFile().createNewFile()
        }

        userPreferences { starDict.loadDictionaries(dictPath.pathList) }

    }


}


val AppContext = appCtx

val starDict = StarDictEngine.instance

val SRC_DIR: String get() = appCtx.filesDir.path+ "/dicts"


internal val LocalTTS: ProvidableCompositionLocal<TextToSpeech?> = compositionLocalOf { null }

var tts:TextToSpeech? = null

/**
 * A helper/wrapper for [TextToSpeech]
 */
class TTS(
    private val context: Context,
    private val locale: Locale = Locale.US,
) {
    private var ttsLang: Int = 0

    /**
     * This method wraps around classic [TextToSpeech.OnInitListener] callback method
     * and suspends it result with a [CancellableContinuation] coroutine
     */
    suspend fun getTts(): TextToSpeech? {
        return suspendCancellableCoroutine { continuation ->
            if (tts == null) {
                tts = TextToSpeech(context) {
                    if (it == TextToSpeech.SUCCESS) {
                        ttsLang = tts?.setLanguage(locale) ?: 0
                        if (ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                            continuation.resume(null)
                        } else continuation.resume(tts)
                    } else continuation.resume(null)
                }
            } else continuation.resume(tts)
        }
    }

    /**
     * Returns current [Locale] of the device
     */
    @Suppress("DEPRECATION")
    fun getCurrentLocale(
        context: Context
    ): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales.get(0)
        } else context.resources.configuration.locale
    }

    /**
     * Extension method for [TextToSpeech] that
     * calls [TextToSpeech.speak] with some predefined parameters
     */
    fun TextToSpeech.speak(
        text: String
    ) {
        speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}


