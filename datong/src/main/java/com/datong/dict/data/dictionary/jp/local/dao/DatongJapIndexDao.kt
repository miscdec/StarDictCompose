package com.datong.dict.data.dictionary.jp.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.datong.dict.data.dictionary.jp.local.entity.datong.Index

@Dao
/* loaded from: classes.dex */
interface DatongJapIndexDao {

    @Query("SELECT * FROM Index_ WHERE wordId = :wordId")
    fun getIndex(wordId: String): Index

    @Query("SELECT * FROM Index_ WHERE word LIKE :startWithKey OR kana LIKE :startWithKey OR romaji LIKE :startWithKey OR indexExpBase LIKE :containsKey OR indexExpJP LIKE :containsKey LIMIT 30")
    fun getIndex(startWithKey: String?, containsKey: String?): List<Index>

    @Query("SELECT * FROM Index_ WHERE indexExpBase LIKE :containsKey OR indexExpJP LIKE :containsKey LIMIT 30")
    fun getIndexByBlurry(containsKey: String): List<Index>

    @Query("SELECT * FROM Index_ WHERE word = :word OR kana = :word")
    fun getIndexByText(word: String): List<Index>

    @Query("SELECT * FROM Index_ WHERE word LIKE :key OR kana LIKE :key OR romaji LIKE :key LIMIT 30")
    fun getIndexByWildcard(key: String): List<Index>
}
