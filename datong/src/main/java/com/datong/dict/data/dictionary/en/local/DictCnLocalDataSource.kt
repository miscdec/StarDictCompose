package com.datong.dict.data.dictionary.en.local

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.datong.dict.base.callback.GetWordCallback
import com.datong.dict.data.dictionary.en.local.database.DictCnDatabase
import com.datong.dict.data.dictionary.en.local.entity.DictCnWord
import com.datong.dict.data.dictionary.en.source.DictCnDataSource
import com.datong.dict.utils.SecretUtil

/* loaded from: classes.dex */
class DictCnLocalDataSource private constructor(context: Context) : DictCnDataSource {
    private val dictCnDao = DictCnDatabase.getInstance(context)!!.dictCnDao()

    // com.datong.dict.data.dictionary.en.source.DictCnDataSource
    override fun getWord(keywords: String, callback: GetWordCallback<DictCnWord>) {
        Thread {
            val word = dictCnDao.getWord(keywords)
            decrypt(word)
            Handler(Looper.getMainLooper()).post {
                if (word == null) {
                    callback.onError()
                } else {
                    callback.onLoad(word)
                }
            }
        }
            .start()
    }

    private fun decrypt(word: DictCnWord?) {
        if (word == null) {
            return
        }
        try {
            word.phoneticUs = SecretUtil.decrypt(word.phoneticUs, word.word)
            word.phoneticUk = SecretUtil.decrypt(word.phoneticUk, word.word)
            word.ukSoundUrlMan = SecretUtil.decrypt(word.ukSoundUrlMan, word.word)
            word.ukSoundUrlWoman = SecretUtil.decrypt(word.ukSoundUrlWoman, word.word)
            word.usSoundUrlMan = SecretUtil.decrypt(word.usSoundUrlMan, word.word)
            word.usSoundUrlWoman = SecretUtil.decrypt(word.usSoundUrlWoman, word.word)
            word.expDetail = SecretUtil.decrypt(word.expDetail, word.word)
            word.expDouble = SecretUtil.decrypt(word.expDouble, word.word)
            word.oftenSentence = SecretUtil.decrypt(word.oftenSentence, word.word)
            word.oftenPhrase = SecretUtil.decrypt(word.oftenPhrase, word.word)
            word.collocation = SecretUtil.decrypt(word.collocation, word.word)
            word.usage = SecretUtil.decrypt(word.usage, word.word)
            word.mistakes = SecretUtil.decrypt(word.mistakes, word.word)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private var INSTANCE: DictCnLocalDataSource? = null
        fun getInstance(context: Context): DictCnLocalDataSource? {
            if (INSTANCE == null) {
                INSTANCE = DictCnLocalDataSource(context)
            }
            return INSTANCE
        }
    }
}
