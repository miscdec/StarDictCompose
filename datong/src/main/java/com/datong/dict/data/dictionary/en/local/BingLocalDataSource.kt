package com.datong.dict.data.dictionary.en.local

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import androidx.room.Room.databaseBuilder
import com.datong.dict.data.dictionary.en.local.database.BingDatabase
import com.datong.dict.data.dictionary.en.local.entity.BingWord
import com.datong.dict.data.dictionary.en.source.BingDataSource
import com.datong.dict.utils.ConvertUtil
import com.datong.dict.utils.PathUtil
import com.datong.dict.utils.SecretUtil
import java.io.File

/* loaded from: classes.dex */
class BingLocalDataSource private constructor(context: Context) : BingDataSource {
    private val bingDao = databaseBuilder(context, BingDatabase::class.java, "bingdao")
        .build().bingDao()

    // com.datong.dict.data.dictionary.en.source.BingDataSource
    override fun getWord(keywords: String, callback: BingDataSource.GetWordCallback) {
        if (File(PathUtil.PATH_DOWNLOAD + "/DICT_EN_BING").exists()) {
            getWordFromOfflineDict(keywords, callback)
        } else {
            getWordFromLocalCache(keywords, callback)
        }
    }

    private fun getWordFromLocalCache(keywords: String, callback: BingDataSource.GetWordCallback) {
        callback.onError()
    }

    private fun getWordFromOfflineDict(keywords: String, callback: BingDataSource.GetWordCallback) {
//        User user = User.getUser();
//        if (user == null || !user.isProLearner()) {
//            callback.onError();
//        } else {
        // from class: com.datong.dict.data.dictionary.en.local.BingLocalDataSource$$ExternalSyntheticLambda0
// java.lang.Runnable
        Thread {
            val word = bingDao.getWord(keywords)
            decrypt(word)
            // from class: com.datong.dict.data.dictionary.en.local.BingLocalDataSource$$ExternalSyntheticLambda3
// java.lang.Runnable
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    if (word == null || word.word!!.isEmpty()) {
                        callback.onError()
                    } else {
                        callback.onLoad(word)
                    }
                }, 200L
            )
        }.start()
        //        }
    }

    /* synthetic */ /* synthetic */
    private fun decrypt(word: BingWord?) {
        try {
            if (!TextUtils.isEmpty(ConvertUtil.MAP_BING)) {
                word!!.phoneticUk = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.phoneticUk)
                word.phoneticUs = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.phoneticUs)
                word.soundUkUrl = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.soundUkUrl)
                word.soundUsUrl = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.soundUsUrl)
                word.baseExpCN = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.baseExpCN)
                word.shape = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.shape)
                word.expEN = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.expEN)
                // from class: com.datong.dict.data.dictionary.en.local.BingLocalDataSource$$ExternalSyntheticLambda1
// java.lang.Runnable
                Thread {
                    word.oxford = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.oxford)
                    word.collocation = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.collocation)
                    word.synonym = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.synonym)
                    word.antonym = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.antonym)
                    word.expEN2CN = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.expEN2CN)
                    word.sentence = ConvertUtil.decrypt(ConvertUtil.MAP_BING, word.sentence)
                }.start()
            } else {
                word!!.phoneticUk = SecretUtil.decrypt(word.phoneticUk, word.word)
                word.phoneticUs = SecretUtil.decrypt(word.phoneticUs, word.word)
                word.soundUkUrl = SecretUtil.decrypt(word.soundUkUrl, word.word)
                word.soundUsUrl = SecretUtil.decrypt(word.soundUsUrl, word.word)
                word.shape = SecretUtil.decrypt(word.shape, word.word)
                word.expEN = SecretUtil.decrypt(word.expEN, word.word)
                // from class: com.datong.dict.data.dictionary.en.local.BingLocalDataSource$$ExternalSyntheticLambda2
// java.lang.Runnable
                Thread {
                    word.oxford = SecretUtil.decrypt(word.oxford, word.word)
                    word.collocation = SecretUtil.decrypt(word.collocation, word.word)
                    word.synonym = SecretUtil.decrypt(word.synonym, word.word)
                    word.antonym = SecretUtil.decrypt(word.antonym, word.word)
                    word.expEN2CN = SecretUtil.decrypt(word.expEN2CN, word.word)
                    word.sentence = SecretUtil.decrypt(word.sentence, word.word)
                }.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    } /* synthetic */ /* synthetic */

    companion object {
        private var INSTANCE: BingLocalDataSource? = null
        fun getInstance(context: Context): BingLocalDataSource? {
            if (INSTANCE == null) {
                INSTANCE = BingLocalDataSource(context)
            }
            return INSTANCE
        }
    }
}
