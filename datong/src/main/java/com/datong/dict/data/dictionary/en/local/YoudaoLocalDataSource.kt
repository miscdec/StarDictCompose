package com.datong.dict.data.dictionary.en.local

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.datong.dict.data.dictionary.en.local.database.YoudaoDatabase
import com.datong.dict.data.dictionary.en.local.entity.YoudaoWord
import com.datong.dict.data.dictionary.en.source.YoudaoDataSource
import com.datong.dict.utils.ConvertUtil
import com.datong.dict.utils.PathUtil
import com.datong.dict.utils.SecretUtil
import java.io.File

/* loaded from: classes.dex */
class YoudaoLocalDataSource private constructor(context: Context) : YoudaoDataSource {
    private val youdaoDao = YoudaoDatabase.getInstance(context)!!.youdaoDao()

    // com.datong.dict.data.dictionary.en.source.YoudaoDataSource
    override fun getWord(keywords: String, callback: YoudaoDataSource.GetWordCallback) {
        if (File("${PathUtil.PATH_DOWNLOAD}/DICT_EN_YOUDAO").exists()) {
            getWordFromOfflineDict(keywords, callback)
        } else {
            getWordFromLocalCache(keywords, callback)
        }
    }

    private fun getWordFromLocalCache(
        keywords: String,
        callback: YoudaoDataSource.GetWordCallback
    ) {
        callback.onError()
    }

    private fun getWordFromOfflineDict(
        keywords: String,
        callback: YoudaoDataSource.GetWordCallback
    ) {
//        User user = (User) User.getCurrentUser();
//        if (user == null || !user.isProLearner()) {
//            callback.onError();
//        } else {
        // from class: com.datong.dict.data.dictionary.en.local.YoudaoLocalDataSource$$ExternalSyntheticLambda0
// java.lang.Runnable
        Thread {
            val word = youdaoDao.getWord(keywords)
            decrypt(word)
            Handler(Looper.getMainLooper()).postDelayed({
                if (word == null || word.word == "") {
                    callback.onError()
                } else {
                    callback.onLoad(word)
                }
            }, 300L)

        }
            .start()
        //        }
    }


    private fun decrypt(word: YoudaoWord) {
        try {
            if (!TextUtils.isEmpty(ConvertUtil.MAP_YOUDAO)) {
                word.phoneticUk = ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.phoneticUk)
                word.phoneticUs = ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.phoneticUs)
                word.expCN = ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.expCN)
                word.expEN = ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.expEN)
                word.shape = ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.shape)
                Thread {
                    word.collins = ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.collins)
                    word.the21stDict =
                        ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.the21stDict)
                    word.wordGroup =
                        ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.wordGroup)
                    word.synonyms =
                        ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.synonyms)
                    word.relatWord =
                        ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.relatWord)
                    word.discriminate =
                        ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.discriminate)
                    word.bilingual =
                        ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.bilingual)
                    word.originalSound =
                        ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.originalSound)
                    word.authority =
                        ConvertUtil.decrypt(ConvertUtil.MAP_YOUDAO, word.authority)
                }.start()
            } else {
                word.phoneticUk = SecretUtil.decrypt(word.phoneticUk, word.word)
                word.phoneticUs = SecretUtil.decrypt(word.phoneticUs, word.word)
                word.expEN = SecretUtil.decrypt(word.expEN, word.word)
                Thread {
                    word.collins = SecretUtil.decrypt(word.collins, word.word)
                    word.the21stDict = SecretUtil.decrypt(word.the21stDict, word.word)
                }.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private var INSTANCE: YoudaoLocalDataSource? = null
        fun getInstance(context: Context): YoudaoLocalDataSource? {
            if (INSTANCE == null) {
                INSTANCE = YoudaoLocalDataSource(context)
            }
            return INSTANCE
        }
    }
}
