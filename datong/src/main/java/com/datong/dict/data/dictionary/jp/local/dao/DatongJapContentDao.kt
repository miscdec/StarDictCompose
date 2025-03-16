package com.datong.dict.data.dictionary.jp.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.datong.dict.data.dictionary.jp.local.entity.datong.Content

@Dao
/* loaded from: classes.dex */
interface DatongJapContentDao {

    @Query("SELECT * FROM Content WHERE wordId = :wordId")
    fun getContent(wordId: String): Content
}
