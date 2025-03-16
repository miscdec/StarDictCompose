package com.datong.dict.data.dictionary.en.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.datong.dict.data.dictionary.en.local.entity.YoudaoWord

@Dao
interface YoudaoDao {

    @Query("select * from dict where word = :keywords")
    fun getWord(keywords: String): YoudaoWord
}
